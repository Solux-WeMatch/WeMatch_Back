package WeMatch.wematch.domain.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventRequestDto {
    private Long eventId;
    private Long memberId;
    private String eventName;
    private LocalDateTime start_schedule;
    private LocalDateTime end_schedule;

    @Builder
    public EventRequestDto(Long eventId, Long memberId, String eventName, LocalDateTime start_schedule, LocalDateTime end_schedule) {
        this.eventId = eventId;
        this.memberId = memberId;
        this.eventName = eventName;
        this.start_schedule = start_schedule;
        this.end_schedule = end_schedule;
    }
}
