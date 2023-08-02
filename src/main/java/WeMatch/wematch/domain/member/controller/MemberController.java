package WeMatch.wematch.domain.member.controller;


import WeMatch.wematch.domain.group.dto.GetTeamResponseDto;
import WeMatch.wematch.domain.group.service.TeamService;
import WeMatch.wematch.domain.member.dto.TeamCreateRequestDto;
import WeMatch.wematch.domain.member.dto.TeamSaveRequestDto;
import WeMatch.wematch.domain.member.dto.TeamListResponseDto;
import WeMatch.wematch.domain.member.service.MemberService;
import WeMatch.wematch.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static WeMatch.wematch.response.Response.failure;
import static WeMatch.wematch.response.Response.success;
import static WeMatch.wematch.response.ResponseMessage.*;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;
    private final TeamService teamService;

    @GetMapping("/teamlist")
    public Response getTeamList(@RequestParam Long memberId){
        List<TeamListResponseDto> result = memberService.getTeamList(memberId);
        return success(SUCCESS_GET_TEAMS, result);
    }

    @PostMapping("/create-team")
    public Response createTeam(@RequestBody TeamCreateRequestDto teamCreateRequestDto){
        teamService.createTeam(teamCreateRequestDto);
        teamService.saveTeam(teamCreateRequestDto.getTeamId(), teamCreateRequestDto.getMemberId());
        return success(SUCCESS_TEAM_CREATE, teamCreateRequestDto);
    }

    @PostMapping("/invite")
    public Response inviteTeam(@RequestBody TeamSaveRequestDto teamSaveRequestDto){
        // 팀 생성 이메일 보내기


        return success(SUCCESS_TEAM_INVITE);
    }

    @GetMapping("/acceptTeam")
    public Response acceptTeam(@RequestParam Long teamId, @RequestParam Long memberId){
        // 팀 정보를 가져와서 수락 여부 처리
        GetTeamResponseDto team = teamService.getTeamInfo(teamId);
        if (team != null) {
            // 팀 멤버로 추가
            teamService.saveTeam(teamId, memberId);
            return success(SUCCESS_TEAM_SAVE);
        } else {
            // 팀이 존재하지 않는 경우 등 예외 처리
            return failure(FAILURE_GET_TEAM_INFO);
        }
    }

}
