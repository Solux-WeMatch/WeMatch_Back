package WeMatch.wematch.domain.event.dto;

import WeMatch.wematch.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventSaveRequestDto {
    private Long eventId;
    private Long memberId;
    private String eventName;
    private LocalDateTime start_schedule;
    private LocalDateTime end_schedule;

    @Builder
    public EventSaveRequestDto(Long memberId, String eventName, LocalDateTime start_schedule, LocalDateTime end_schedule) {
        this.memberId = memberId;
        this.eventName = eventName;
        this.start_schedule = start_schedule;
        this.end_schedule = end_schedule;
    }

    public Event toEntity(){
        return Event.builder()
                .memberId(memberId)
                .eventName(eventName)
                .start_schedule(start_schedule)
                .end_schedule(end_schedule)
                .build();
    }
}
