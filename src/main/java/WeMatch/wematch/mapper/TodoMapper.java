package WeMatch.wematch.mapper;

import WeMatch.wematch.domain.todo.dto.TodoListRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
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
    void saveTodo(TodoSaveRequestDto todoSaveRequestDto);

    // Todo 삭제
    void deleteTodo(TodoRequestDto todo);

    // Todo 완료 체크
    void checkTodo(TodoRequestDto todo);

    // Todo 수정
    void updateTodo(TodoRequestDto todo);

    // Todo TodoId로 조회
    TodoResponseDto findTodoById(Long todoId);

    List<TodoResponseDto> findTodoByIdDate(Long memberId, LocalDate date);
    List<TodoResponseDto> findMonthTodo(Long memberId, int year, int month);

}
