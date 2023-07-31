package WeMatch.wematch.domain.event.service;

import WeMatch.wematch.domain.event.dto.EventRequestDto;
import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.dto.EventSaveRequestDto;
import WeMatch.wematch.domain.event.entity.Event;
import WeMatch.wematch.domain.event.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public void saveEvent(EventSaveRequestDto eventSaveRequestDto){
        eventRepository.saveEvent(eventSaveRequestDto);
    }

    public void updateEvent(EventRequestDto eventRequestDto){
       eventRepository.updateEvent(eventRequestDto);
    }

    public void deleteEvent(Long eventId){
        eventRepository.deleteEvent(eventId);
    }

    public List<EventResponseDto> findEventByDay(@Param("memberId") Long memberId, @Param("date") LocalDate date) {
        return eventRepository.findEventByDay(memberId, date);
    }

    public List<EventResponseDto> findEventByWeek(Long memberId, LocalDateTime date) {
        return eventRepository.findEventByWeek(memberId, date);
    }

    public List<EventResponseDto> findEventByMonth(Long memberId, LocalDateTime date) {
        return eventRepository.findEventByMonth(memberId, date);
    }
}
