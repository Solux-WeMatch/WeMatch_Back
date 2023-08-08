package WeMatch.wematch.domain.event.repository;

import WeMatch.wematch.domain.event.dto.EventListRequestDto;
import WeMatch.wematch.domain.event.dto.EventRequestDto;
import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.dto.EventSaveRequestDto;
import WeMatch.wematch.domain.event.entity.Event;
import WeMatch.wematch.domain.group.dto.TeamEventsResponseDto;
import WeMatch.wematch.mapper.EventMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class EventRepository {
    public final EventMapper eventDAO;

    public void saveEvent(EventSaveRequestDto eventSaveRequestDto){
        eventDAO.saveEvent(eventSaveRequestDto);
    }

    public void updateEvent(EventRequestDto eventRequestDto){
        eventDAO.updateEvent(eventRequestDto);
    }

    public void deleteEvent(Long eventId){
        eventDAO.deleteEvent(eventId);
    }

    // eventId로 event 조회
    public Event findEventById(Long eventId){
        Event event = eventDAO.findEventById(eventId);
        return event;
    }

    // 날짜별 event 조회
    public List<EventResponseDto> findEventByDay(Long memberId, LocalDate date){
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
