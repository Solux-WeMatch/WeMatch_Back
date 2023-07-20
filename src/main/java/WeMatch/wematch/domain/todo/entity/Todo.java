package WeMatch.wematch.domain.todo.entity;

import lombok.Data;

@Data
public class Todo {
    private Long todoId;
    private Long memberId;
    private String todoName;
    private boolean completed;
    // 날짜 데이터는?


    public Todo(Long todoId, Long memberId, String todoName) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.todoName = todoName;
    }
}
