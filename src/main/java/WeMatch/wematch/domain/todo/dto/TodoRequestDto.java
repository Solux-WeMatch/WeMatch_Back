package WeMatch.wematch.domain.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class TodoRequestDto {
    private Long todoId;
    private Long memberId;
    private String todoName;

    @Builder
    public TodoRequestDto(Long todoId, Long memberId, String todoName) {
        this.todoId = todoId;
        this.memberId = memberId;
        this.todoName = todoName;
    }
}
