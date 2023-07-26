package WeMatch.wematch.domain.todo.dto;

import WeMatch.wematch.domain.todo.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class TodoSaveRequestDto {
    private Long todoId;
    private Long memberId;
    private String todoName;
    private LocalDate todo_schedule;
    private boolean completed;

    @Builder
    public TodoSaveRequestDto(Long todoId, Long memberId, String todoName, LocalDate todo_schedule) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.todoName = todoName;
        this.todo_schedule = todo_schedule;
        this.completed = false;
    }

    public Todo toEntity(){
        return Todo.builder()
                .todoId(todoId)
                .memberId(memberId)
                .todoName(todoName)
                .todo_schedule(todo_schedule)
                .completed(completed)
                .build();
    }
}