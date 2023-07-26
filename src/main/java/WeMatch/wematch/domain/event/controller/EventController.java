package WeMatch.wematch.domain.event.controller;

import WeMatch.wematch.domain.event.dto.EventRequestDto;
import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.dto.EventSaveRequestDto;
import WeMatch.wematch.domain.event.entity.Event;
import WeMatch.wematch.domain.event.service.EventService;
import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.service.TodoService;
import WeMatch.wematch.response.Response;
import static WeMatch.wematch.response.ResponseMessage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/event")
@RequiredArgsConstructor
@RestController
public class EventController {
    private final EventService eventService;

    @PostMapping("/save")
    public Response saveEvent(@RequestBody EventSaveRequestDto eventSaveRequestDto){
        EventResponseDto result = eventService.saveEvent(eventSaveRequestDto);
        return Response.success(SUCCESS, result);
    }

    @PutMapping("/update")
    public Response updateEvent(@RequestBody EventRequestDto eventRequestDto){
        EventResponseDto result = eventService.updateEvent(eventRequestDto);
        return Response.success(SUCCESS, result);
    }

    @DeleteMapping("/delete")
    public Response deleteEvent(@RequestBody EventRequestDto eventRequestDto){
        EventResponseDto result = eventService.deleteEvent(eventRequestDto);
        return Response.success(SUCCESS, result);
    }

    // memberId가 id인 사용자의 date 날짜의 event 조회
    @GetMapping("/day")
    public Response findEventByDay(@RequestParam Long memberId, @RequestParam LocalDateTime date){
        List<EventResponseDto> result = eventService.findEventByDay(memberId, date); // memberService 이용 코드 수정
        return Response.success(SUCCESS, result);
    }

    @GetMapping("/week")
    public Response findEventByWeek(@RequestParam Long memberId, @RequestParam LocalDateTime date){
        List<EventResponseDto> result = eventService.findEventByWeek(memberId, date); // memberService 이용 코드 수정
        return Response.success(SUCCESS, result);
    }

    @GetMapping("/month")
    public Response findEventByMonth(@RequestParam Long memberId, @RequestParam LocalDateTime date){
        List<EventResponseDto> result = eventService.findEventByMonth(memberId, date); // memberService 이용 코드 수정
        return Response.success(SUCCESS, result);
    }
}
