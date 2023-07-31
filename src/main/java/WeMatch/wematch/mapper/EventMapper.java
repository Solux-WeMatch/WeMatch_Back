package WeMatch.wematch.mapper;

import WeMatch.wematch.domain.event.dto.EventRequestDto;
import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.event.dto.EventSaveRequestDto;
import WeMatch.wematch.domain.event.entity.Event;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface EventMapper {
    void saveEvent(EventSaveRequestDto eventSaveRequestDto);

    void deleteEvent(Long eventId);

    void updateEvent(EventRequestDto eventRequestDto);

    // eventId로 event 조회
    Event findEventById(Long eventId);

    // date 날짜의 event 조회
    List<EventResponseDto> findEventByDay(@Param("memberId") Long memberId, @Param("date") LocalDate date);

    // date 날짜가 속한 week의 event 조회
    List<EventResponseDto> findEventByWeek(Long memberId, LocalDateTime date);

    // date 날짜가 속한 month의 event 조회
    List<EventResponseDto> findEventByMonth(Long memberId, LocalDateTime date);
}
