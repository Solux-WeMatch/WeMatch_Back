package WeMatch.wematch.domain.todo.dto;

import WeMatch.wematch.domain.todo.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
public class TodoSaveRequestDto {
    private Long todoId;
    private Long memberId;
    private String todoName;
    private Date todo_schedule;
    private boolean completed;

    @Builder
    public TodoSaveRequestDto(Long memberId, String todoName, Date todo_schedule) {
        // this.todoId = todoId;
        this.memberId = memberId;
        this.todoName = todoName;
        this.todo_schedule = todo_schedule;
        // this.completed = completed; // false
    }

    public Todo toEntity(){
        return Todo.builder()
                .memberId(memberId)
                .todoName(todoName)
                .todo_schedule(todo_schedule)
                .build();
    }
}
