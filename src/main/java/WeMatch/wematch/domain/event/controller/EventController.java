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
    public void saveEvent(@RequestBody EventSaveRequestDto eventSaveRequestDto){
        eventService.saveEvent(eventSaveRequestDto);
    }

    @PutMapping("/update/{eventId}")
    public void updateEvent(@PathVariable Long eventId, @RequestBody EventRequestDto eventRequestDto){
        eventService.updateEvent(eventId, eventRequestDto);
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable Long eventId, @RequestBody EventRequestDto eventRequestDto){
        eventService.deleteEvent(eventId, eventRequestDto);
    }

    // memberId가 id인 사용자의 date 날짜의 event 조회
    @GetMapping("/day/{id}/{date}")
    public List<Event> findEventByDay(@PathVariable Long memberId, @PathVariable LocalDateTime date){
        return eventService.findEventByDay(memberId, date);
    }

    @GetMapping("/week/{id}/{date}")
    public List<Event> findEventByWeek(@PathVariable Long memberId, @PathVariable LocalDateTime date){
        return eventService.findEventByWeek(memberId, date);
    }

    @GetMapping("/month/{id}/{date}")
    public List<Event> findEventByMonth(@PathVariable Long memberId, @PathVariable LocalDateTime date){
        return eventService.findEventByMonth(memberId, date);
    }
}
