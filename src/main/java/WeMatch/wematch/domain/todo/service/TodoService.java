package WeMatch.wematch.domain.todo.service;

import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.entity.Todo;
import WeMatch.wematch.domain.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto){
        TodoResponseDto result = todoRepository.saveTodo(todoSaveRequestDto);
        return result;
    }

    public TodoResponseDto updateTodo(TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findTodoById(todoRequestDto.getTodoId());
        todo.updateTodo(todoRequestDto.getTodoName());
        TodoResponseDto result = todoRepository.updateTodo(todo);
        return result;
    }

    public TodoResponseDto checkTodo(TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findTodoById(todoRequestDto.getTodoId());
        TodoResponseDto result = todoRepository.checkTodo(todo);
        return result;
    }

    public TodoResponseDto deleteTodo(TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findTodoById(todoRequestDto.getTodoId());
        TodoResponseDto result = todoRepository.deleteTodo(todo);
        return result;
    }

    public TodoResponseDto findById(TodoRequestDto todoRequestDto) {
        Todo entity = todoRepository.findTodoById(todoRequestDto.getTodoId());
        return new TodoResponseDto(entity);
    }

    public List<TodoResponseDto> findTodoByIdDate(Long memberId, LocalDate todoSchedule) {
        return (List<TodoResponseDto>) todoRepository.findTodoByIdDate(memberId, todoSchedule);
    }
}
