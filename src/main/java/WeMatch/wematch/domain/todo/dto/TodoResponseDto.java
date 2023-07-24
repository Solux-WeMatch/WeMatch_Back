package WeMatch.wematch.domain.todo.dto;

import WeMatch.wematch.domain.todo.entity.Todo;
import lombok.Getter;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    // private Long todoId;
    private Long memberId;
    private String todoName;
    private LocalDateTime todo_schedule;
    private boolean completed;

    public TodoResponseDto(Todo entity) {
        this.memberId = entity.getMemberId();
        this.todoName = entity.getTodoName();
        this.todo_schedule = entity.getTodo_schedule();
        this.completed = entity.isCompleted();
    }
}