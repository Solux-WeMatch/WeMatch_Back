package WeMatch.wematch.domain.todo.service;

import WeMatch.wematch.domain.todo.dto.TodoListRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.entity.Todo;
import WeMatch.wematch.domain.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public void saveTodo(TodoSaveRequestDto todoSaveRequestDto){
        todoRepository.saveTodo(todoSaveRequestDto);
    }

    public void updateTodo(TodoRequestDto todoRequestDto){
        //TodoResponseDto todo = todoRepository.findTodoById(todoRequestDto.getTodoId());
        todoRepository.updateTodo(todoRequestDto);
    }

    public void checkTodo(TodoRequestDto todoRequestDto){
        // TodoResponseDto todo = todoRepository.findTodoById(todoRequestDto.getTodoId());
        todoRepository.checkTodo(todoRequestDto);
    }

    public void deleteTodo(TodoRequestDto todoRequestDto){
        // TodoResponseDto todo = todoRepository.findTodoById(todoRequestDto.getTodoId());
        todoRepository.deleteTodo(todoRequestDto);
    }

    public TodoResponseDto findById(TodoSaveRequestDto todoRequestDto) {
        TodoResponseDto entity = todoRepository.findTodoById(todoRequestDto.getTodoId());
        return entity;
    }

    public List<TodoResponseDto> findTodoByIdDate(Long memberId, LocalDate date) {
        return todoRepository.findTodoByIdDate(memberId, date);
    }

    public List<TodoResponseDto> findMonthTodo(Long memberId, int year, int month) {
        return todoRepository.findMonthTodo(memberId, year, month);
    }
}
