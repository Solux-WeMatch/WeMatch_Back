package WeMatch.wematch.mapper;

import WeMatch.wematch.domain.todo.entity.Todo;

import java.util.Date;

public interface TodoMapper {

    // Todo 생성
    void saveTodo(Todo todo);

    // Todo 삭제
    void deleteTodo(Todo todo);

    // Todo 완료 체크
    void checkTodo(Todo todo);

    // Todo 수정
    void updateTodo(Todo todo);

    // Todo (memberId, TodoId)로 조회
    Todo findTodoById(Long memberId, Long todoId);

    Todo findTodoByIdDate(Long memberId, Date todo_schedule);

}
