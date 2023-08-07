package WeMatch.wematch.domain.group.controller;

import WeMatch.wematch.domain.group.dto.InsertFixedTimeRequestDto;
import WeMatch.wematch.domain.group.service.TeamService;
import WeMatch.wematch.domain.member.service.MemberService;
import WeMatch.wematch.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static WeMatch.wematch.response.Response.success;
import static WeMatch.wematch.response.ResponseMessage.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/fix")
public class TimeFixController {
    TeamService teamService;
    MemberService memberService;

    @GetMapping()
    public Response getCandidate(@RequestParam Long candidateId) {
        return success(SUCCESS_TO_GET_CANDIDATE,teamService.getCandidate(candidateId));
    }

    @PostMapping()
    public Response insertFixedTime(@RequestParam Long candidateId,
                                    @RequestBody InsertFixedTimeRequestDto insertFixedTimeRequestDto) {
        teamService.insertFixedTime(insertFixedTimeRequestDto, candidateId);
        return success(SUCCESS_TO_INSERT_FIXED_TIME);
    }
}
