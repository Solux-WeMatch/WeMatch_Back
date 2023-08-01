package WeMatch.wematch.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamEventsResponseDto {
    private LocalDateTime eventStartAt;
    private LocalDateTime eventEndAt;
    private Long eventId;
    private Long memberId;
}
