package WeMatch.wematch.domain.member.controller;


import WeMatch.wematch.domain.group.dto.GetTeamResponseDto;
import WeMatch.wematch.domain.group.service.TeamService;
import WeMatch.wematch.domain.mail.service.MailService;
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

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;
    private final TeamService teamService;
    private final MailService mailService;

    @GetMapping("/index")
    public String index(){
        return "/dist/index.html";
    }

    @GetMapping("/teamlist")
    public Response getTeamList(@RequestParam Long memberId){
        List<TeamListResponseDto> result = memberService.getTeamList(memberId);
        return success(SUCCESS_GET_TEAMS, result);
    }

    @PostMapping("/create-team")
    public Response createTeam(@RequestBody TeamCreateRequestDto teamCreateRequestDto){
        teamService.createTeam(teamCreateRequestDto);
        Long teamId = teamService.getTeamId(teamCreateRequestDto.getTeamName());
        Long memberId = teamCreateRequestDto.getMemberId();
        teamService.saveTeam(teamId, memberId);
        return success(SUCCESS_TEAM_CREATE);
    }

    @PostMapping("/invite")
    public Response inviteTeam(@RequestBody TeamSaveRequestDto teamSaveRequestDto){
        // 팀 생성 이메일 보내기
        mailService.sendTeamInviteMail(teamSaveRequestDto);
        return success(SUCCESS_TEAM_INVITE);
    }

    @GetMapping("/acceptTeam")
    public String acceptTeam(@RequestParam Long teamId, @RequestParam Long memberId){
        // 팀 정보를 가져와서 수락 여부 처리
        GetTeamResponseDto team = teamService.getTeamInfo(teamId);
        if (team != null) {
            // 팀 멤버로 추가
            teamService.saveTeam(teamId, memberId);
            System.out.println("팀 멤버 초대 수락");
            return "성공적으로 모임에 초대되었습니다!";
        } else {
            // 팀이 존재하지 않는 경우 등 예외 처리
            return "존재하지 않는 모임입니다.";
        }
    }

}
