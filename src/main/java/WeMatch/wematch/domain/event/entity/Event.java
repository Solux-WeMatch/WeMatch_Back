package WeMatch.wematch.domain.event.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private Long eventId;
    private Long memberId;
    //private Long scheduleId;
    private String eventName;
    private LocalDateTime start_schedule;
    private LocalDateTime end_schedule;

    @Builder
    public Event(Long eventId, Long memberId, String eventName, LocalDateTime start_schedule, LocalDateTime end_schedule) {
        this.eventId = eventId;
        this.memberId = memberId;
        this.eventName = eventName;
        this.start_schedule = start_schedule;
        this.end_schedule = end_schedule;
    }

    public void updateEvent(String eventName){
        this.eventName = eventName;
    }
}
