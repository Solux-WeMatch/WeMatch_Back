package WeMatch.wematch.domain.group.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamEventsResponseDto {

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mmZ")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mmZ")
    private LocalDateTime end;
    private Long eventId;
    private Long memberId;
    private Long title;
}
