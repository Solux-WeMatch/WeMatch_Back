package WeMatch.wematch.mapper;

import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.entity.Todo;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface TodoMapper {

    // Todo 생성
    TodoResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto);

    // Todo 삭제
    TodoResponseDto deleteTodo(Todo todo);

    // Todo 완료 체크
    TodoResponseDto checkTodo(Todo todo);

    // Todo 수정
    TodoResponseDto updateTodo(Todo todo);

    // Todo (memberId, TodoId)로 조회
    Todo findTodoById(Long todoId);

    List<TodoResponseDto> findTodoByIdDate(Long memberId, LocalDate todo_schedule);

}
