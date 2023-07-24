package WeMatch.wematch.domain.event.repository;

import WeMatch.wematch.domain.event.entity.Event;
import WeMatch.wematch.mapper.EventMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class EventRepository {
    public EventMapper eventDAO;

    public void saveEvent(Event event){
        eventDAO.saveEvent(event);
    }

    public void updateTodo(Event todo){
        eventDAO.updateEvent(todo);
    }

    public void deleteTodo(Event todo){
        eventDAO.deleteEvent(todo);
    }

    // eventId로 event 조회
    public Event findEventById(Long memberId, Long eventId){
        Event event = eventDAO.findEventById(memberId, eventId);
        return event;
    }

    // 날짜별 event 조회
    public Event findEventByDay(Long memberId, LocalDateTime date){
        Event event = eventDAO.findEventByDay(memberId, date);
        return event; // List?
    }

    // 날짜 기준 주별 event 조회
    public Event findEventByWeek(Long memberId, LocalDateTime date){
        Event event = eventDAO.findEventByWeek(memberId, date);
        return event;
    }

    // 날짜 기준 월별 event 조회
    public Event findEventByMonth(Long memberId, LocalDateTime date){
        Event event = eventDAO.findEventByMonth(memberId, date);
        return event;
    }
}
