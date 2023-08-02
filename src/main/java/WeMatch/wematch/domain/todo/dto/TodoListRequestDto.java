package WeMatch.wematch.domain.todo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Data
@NoArgsConstructor
public class TodoListRequestDto {
    private Long memberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate todo_schedule;

    @Builder
    public TodoListRequestDto(Long memberId, LocalDate todo_schedule) {
        this.memberId = memberId;
        this.todo_schedule = todo_schedule;
    }
}
