package WeMatch.wematch.domain.event.repository;

import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.entity.Event;
import WeMatch.wematch.mapper.EventMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class EventRepository {
    public EventMapper eventDAO;

    public EventResponseDto saveEvent(Event event){
        EventResponseDto result = eventDAO.saveEvent(event);
        return result;
    }

    public EventResponseDto updateTodo(Event todo){
        EventResponseDto result = eventDAO.updateEvent(todo);
        return result;
    }

    public EventResponseDto deleteTodo(Event todo){
        EventResponseDto result = eventDAO.deleteEvent(todo);
        return result;
    }

    // eventId로 event 조회
    public Event findEventById(Long eventId){
        Event event = eventDAO.findEventById(eventId);
        return event;
    }

    // 날짜별 event 조회
    public List<EventResponseDto> findEventByDay(Long memberId, LocalDateTime date){
        List<EventResponseDto> event = eventDAO.findEventByDay(memberId, date);
        return event;
    }

    // 날짜 기준 주별 event 조회
    public List<EventResponseDto> findEventByWeek(Long memberId, LocalDateTime date){
        List<EventResponseDto> event = eventDAO.findEventByWeek(memberId, date);
        return event;
    }

    // 날짜 기준 월별 event 조회
    public List<EventResponseDto> findEventByMonth(Long memberId, LocalDateTime date){
        List<EventResponseDto> event = eventDAO.findEventByMonth(memberId, date);
        return event;
    }
}
