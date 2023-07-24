package WeMatch.wematch.domain.todo.entity;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class Todo {
    private Long todoId;
    private Long memberId;
    private String todoName;
    private LocalDateTime todo_schedule;
    private boolean completed;

    @Builder
    public Todo(Long todoId, Long memberId, String todoName, LocalDateTime todo_schedule, boolean completed) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.todoName = todoName;
        this.todo_schedule = todo_schedule;
        this.completed = completed;
    }

    public void updateTodo(String todoName){
        this.todoName = todoName;
    }
}
