package WeMatch.wematch.domain.todo.service;

import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.entity.Todo;
import WeMatch.wematch.domain.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public void saveTodo(TodoSaveRequestDto todoSaveRequestDto){
        todoRepository.saveTodo(todoSaveRequestDto.toEntity());
    }

    public void updateTodo(Long todoId, TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findTodoById(todoRequestDto.getMemberId(), todoId);
        todo.updateTodo(todoRequestDto.getTodoName());
        todoRepository.updateTodo(todo);
    }

    public void deleteTodo(Long todoId, TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findTodoById(todoRequestDto.getMemberId(), todoId);
        todoRepository.deleteTodo(todo);
    }

    public TodoResponseDto findById(TodoRequestDto todoRequestDto) {
        Todo entity = todoRepository.findTodoById(todoRequestDto.getMemberId(), todoRequestDto.getTodoId());
        return new TodoResponseDto(entity);
    }

//    public List<TodoResponseDto> findTodoByIdDate(Long memberId, Date todoSchedule) {
//        return todoRepository.findTodoByIdDate(memberId, todoSchedule);
//    }
}
