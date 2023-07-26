package WeMatch.wematch.domain.todo.repository;

import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.entity.Todo;
import WeMatch.wematch.mapper.TodoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class TodoRepository {

    public TodoMapper todoDAO;

    public TodoResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto){
        TodoResponseDto result = todoDAO.saveTodo(todoSaveRequestDto);
        return result;
    }

    public TodoResponseDto updateTodo(Todo todo){
        TodoResponseDto result = todoDAO.updateTodo(todo);
        return result;
    }

    public TodoResponseDto checkTodo(Todo todo){
        TodoResponseDto result = todoDAO.checkTodo(todo);
        return result;
    }

    public TodoResponseDto deleteTodo(Todo todo){
        TodoResponseDto result = todoDAO.deleteTodo(todo);
        return result;
    }

    public List<TodoResponseDto> findTodoByIdDate(Long memberId, LocalDate todo_schedule){
        List<TodoResponseDto> todo = todoDAO.findTodoByIdDate(memberId, todo_schedule);
        return todo;
    }

    public Todo findTodoById(Long todoId){
        Todo todo = todoDAO.findTodoById(todoId);
        return todo;
    }

    public void deleteAll(){

    }
}