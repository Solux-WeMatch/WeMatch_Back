package WeMatch.wematch.domain.group.controller;

import WeMatch.wematch.domain.group.service.TeamService;
import WeMatch.wematch.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/fix")
public class TimeFixController {
    TeamService teamService;
    MemberService memberService;

    @PostMapping()
    public void insertFixedTime(@RequestParam Long id) {
        teamService.insertFixedTime(id);
    }
}
