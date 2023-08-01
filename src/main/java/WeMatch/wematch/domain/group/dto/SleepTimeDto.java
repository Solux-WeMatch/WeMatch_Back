package WeMatch.wematch.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class SleepTimeDto {
    private LocalTime startAt;
    private LocalTime endAt;
}
