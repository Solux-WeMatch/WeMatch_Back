package WeMatch.wematch.domain.todo.dto;

import WeMatch.wematch.domain.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDto {
    private Long todoId;
    private Long memberId;
    private String todoName;
    private LocalDate todo_schedule;
    private boolean completed;

    public TodoResponseDto(Todo entity) {
        this.todoId = entity.getTodoId();
        this.memberId = entity.getMemberId();
        this.todoName = entity.getTodoName();
        this.todo_schedule = entity.getTodo_schedule();
        this.completed = entity.isCompleted();
    }
}