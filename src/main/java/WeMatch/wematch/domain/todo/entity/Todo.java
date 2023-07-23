package WeMatch.wematch.domain.todo.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
public class Todo {
    private Long todoId;
    private Long memberId;
    private String todoName;
    private Date todo_schedule;
    private boolean completed;

    @Builder
    public Todo(Long todoId, Long memberId, String todoName, Date todo_schedule, boolean completed) {
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
