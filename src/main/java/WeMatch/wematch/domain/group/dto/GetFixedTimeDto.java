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
public class GetFixedTimeDto {
    private Long candidateId;
    private String teamname;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

}
