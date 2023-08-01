package WeMatch.wematch.domain.group.controller;

import WeMatch.wematch.domain.group.dto.SleepTimeDto;
import WeMatch.wematch.domain.group.dto.TeamEventsResponseDto;
import WeMatch.wematch.domain.group.service.TeamService;
import WeMatch.wematch.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static WeMatch.wematch.response.Response.success;
import static WeMatch.wematch.response.ResponseMessage.*;

@RestController
@AllArgsConstructor
@RequestMapping("/team")
public class TeamPageController {
    private final TeamService teamService;

    @GetMapping()
    public Response getEvents(@RequestParam long groupId,
                              @RequestParam int weekNumber,
                              @RequestParam int year) {
        List<TeamEventsResponseDto> events = teamService.getEvents(groupId);
        List<TeamEventsResponseDto> result = teamService.getResult(events,weekNumber,year);
        return success(SUCCESS_GET_TEAM_EVENTS,result);
    }

    @GetMapping("/info")
    public Response getTeam(@RequestParam long groupId) {
        return success(SUCCESS_GET_TEAM_INFO,teamService.getTeamInfo(groupId));
    }

    @PatchMapping("/sleep")
    public Response updateSleep(@RequestParam Long groupId,
                                @RequestBody SleepTimeDto sleepTimeDto) {
        teamService.updateSleep(groupId, sleepTimeDto);
        return success(SUCCESS_INSERT_SLEEP);
    }

    @GetMapping("/sleep")
    public Response getSleep(@RequestParam Long groupId) {
        return success(SUCCESS_GET_SLEEP,teamService.getSleep(groupId));
    }

    @PatchMapping("/minute")
    public Response insertMinute(@RequestParam Long groupId,
                                 @RequestParam int minute) {
        teamService.insertMinute(groupId,minute);
        return success(SUCCESS_TO_INSERT_MINUTE);
    }

    @GetMapping("/minute")
    public Response getMinute(@RequestParam Long groupId) {
        return success(SUCCESS_TO_GET_MINUTE,teamService.getMinute(groupId));
    }
}