package WeMatch.wematch.domain.event.service;

import WeMatch.wematch.domain.event.dto.EventRequestDto;
import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.dto.EventSaveRequestDto;
import WeMatch.wematch.domain.event.entity.Event;
import WeMatch.wematch.domain.event.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventResponseDto saveEvent(EventSaveRequestDto eventSaveRequestDto){
        EventResponseDto result = eventRepository.saveEvent(eventSaveRequestDto.toEntity());
        return result;
    }

    public EventResponseDto updateEvent(EventRequestDto eventRequestDto){
        Event event = eventRepository.findEventById(eventRequestDto.getEventId());
        event.updateEvent(eventRequestDto.getEventName());
        EventResponseDto result = eventRepository.updateTodo(event);
        return result;
    }

    public EventResponseDto deleteEvent(EventRequestDto eventRequestDto){
        Event event = eventRepository.findEventById(eventRequestDto.getEventId());
        EventResponseDto result = eventRepository.deleteTodo(event);
        return result;
    }

    public List<EventResponseDto> findEventByDay(Long memberId, LocalDateTime date) {
        return eventRepository.findEventByDay(memberId, date);
    }

    public List<EventResponseDto> findEventByWeek(Long memberId, LocalDateTime date) {
        return eventRepository.findEventByWeek(memberId, date);
    }

    public List<EventResponseDto> findEventByMonth(Long memberId, LocalDateTime date) {
        return eventRepository.findEventByMonth(memberId, date);
    }
}
