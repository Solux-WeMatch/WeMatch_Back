package WeMatch.wematch.mapper;

import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.entity.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface EventMapper {
    EventResponseDto saveEvent(Event event);

    EventResponseDto deleteEvent(Event event);

    EventResponseDto updateEvent(Event event);

    // eventId로 event 조회
    Event findEventById(Long eventId);

    // date 날짜의 event 조회
    List<EventResponseDto> findEventByDay(Long memberId, LocalDateTime date);

    // date 날짜가 속한 week의 event 조회
    List<EventResponseDto> findEventByWeek(Long memberId, LocalDateTime date);

    // date 날짜가 속한 month의 event 조회
    List<EventResponseDto> findEventByMonth(Long memberId, LocalDateTime date);
}
