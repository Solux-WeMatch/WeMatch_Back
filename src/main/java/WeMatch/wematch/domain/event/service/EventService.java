package WeMatch.wematch.domain.event.service;

import WeMatch.wematch.domain.event.dto.EventRequestDto;
import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.dto.EventSaveRequestDto;
import WeMatch.wematch.domain.event.entity.Event;
import WeMatch.wematch.domain.event.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public void saveEvent(EventSaveRequestDto eventSaveRequestDto){
        eventRepository.saveEvent(eventSaveRequestDto.toEntity());
    }

    public void updateEvent(Long eventId, EventRequestDto eventRequestDto){
        Event event = eventRepository.findEventById(eventRequestDto.getMemberId(), eventId);
        event.updateEvent(eventRequestDto.getEventName());
        eventRepository.updateTodo(event);
    }

    public void deleteEvent(Long eventId, EventRequestDto eventRequestDto){
        Event event = eventRepository.findEventById(eventRequestDto.getMemberId(), eventId);
        eventRepository.deleteTodo(event);
    }

    public List<Event> findEventByDay(Long memberId, LocalDateTime date) {
        return (List<Event>) eventRepository.findEventByDay(memberId, date);
    }

    public List<Event> findEventByWeek(Long memberId, LocalDateTime date) {
        return (List<Event>) eventRepository.findEventByWeek(memberId, date);
    }

    public List<Event> findEventByMonth(Long memberId, LocalDateTime date) {
        return (List<Event>) eventRepository.findEventByMonth(memberId, date);
    }
}
