package WeMatch.wematch.domain.event.dto;

import WeMatch.wematch.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventResponseDto {
    private Long eventId;
    private Long memberId;
    private String eventName;
    private LocalDateTime start_schedule;
    private LocalDateTime end_schedule;

    @Builder
    public EventResponseDto(Event entity) {
        this.eventId = entity.getEventId();
        this.memberId = entity.getMemberId();
        this.eventName = entity.getEventName();
        this.start_schedule = entity.getStart_schedule();
        this.end_schedule = entity.getEnd_schedule();
    }
}
