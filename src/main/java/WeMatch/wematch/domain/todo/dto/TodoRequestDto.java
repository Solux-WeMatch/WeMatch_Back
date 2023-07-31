package WeMatch.wematch.domain.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class TodoRequestDto {
    private Long todoId;
    private Long memberId;
    private String todoName;
    private LocalDate todo_schedule;
    private boolean if_done;

    @Builder
    public TodoRequestDto(Long todoId, Long memberId, String todoName, LocalDate todo_schedule, boolean if_done) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.todoName = todoName;
        this.todo_schedule = todo_schedule;
        this.if_done = if_done;
    }
}