package WeMatch.wematch.mapper;

import WeMatch.wematch.domain.event.entity.Event;

import java.time.LocalDateTime;

public interface EventMapper {
    void saveEvent(Event event);

    void deleteEvent(Event event);

    void updateEvent(Event event);

    // eventId로 event 조회
    Event findEventById(Long memberId, Long eventId);

    // date 날짜의 event 조회
    Event findEventByDay(Long memberId, LocalDateTime date);

    // date 날짜가 속한 week의 event 조회
    Event findEventByWeek(Long memberId, LocalDateTime date);

    // date 날짜가 속한 month의 event 조회
    Event findEventByMonth(Long memberId, LocalDateTime date);
}
