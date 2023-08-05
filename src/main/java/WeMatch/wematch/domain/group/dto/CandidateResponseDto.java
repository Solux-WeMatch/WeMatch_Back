package WeMatch.wematch.domain.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResponseDto {
    private Long title;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mmZ")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mmZ")
    private LocalDateTime end;
}
