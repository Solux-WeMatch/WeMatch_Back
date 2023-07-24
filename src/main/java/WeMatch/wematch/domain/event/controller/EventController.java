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

    @PutMapping("/update")
    public void updateEvent(@RequestParam Long eventId, @RequestBody EventRequestDto eventRequestDto){
        eventService.updateEvent(eventId, eventRequestDto);
    }

    @DeleteMapping("/delete")
    public void deleteEvent(@RequestParam Long eventId, @RequestBody EventRequestDto eventRequestDto){
        eventService.deleteEvent(eventId, eventRequestDto);
    }

    // memberId가 id인 사용자의 date 날짜의 event 조회
    @GetMapping("/day")
    public List<Event> findEventByDay(@RequestParam Long memberId, @RequestParam LocalDateTime date){
        return eventService.findEventByDay(memberId, date);
    }

    @GetMapping("/week")
    public List<Event> findEventByWeek(@RequestParam Long memberId, @RequestParam LocalDateTime date){
        return eventService.findEventByWeek(memberId, date);
    }

    @GetMapping("/month")
    public List<Event> findEventByMonth(@RequestParam Long memberId, @RequestParam LocalDateTime date){
        return eventService.findEventByMonth(memberId, date);
    }
}
