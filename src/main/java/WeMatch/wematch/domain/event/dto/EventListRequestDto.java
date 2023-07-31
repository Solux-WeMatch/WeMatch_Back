package WeMatch.wematch.domain.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventListRequestDto {
    private Long memberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Builder
    public EventListRequestDto(Long memberId, LocalDate date) {
        this.memberId = memberId;
        this.date = date;
    }
}
