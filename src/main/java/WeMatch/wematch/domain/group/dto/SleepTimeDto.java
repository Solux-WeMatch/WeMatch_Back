package WeMatch.wematch.domain.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class SleepTimeDto {
    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startAt;
    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endAt;
}
