package WeMatch.wematch.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetCandidatesResponseDto {
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
