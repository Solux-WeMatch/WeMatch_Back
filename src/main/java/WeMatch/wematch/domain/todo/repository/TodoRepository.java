package WeMatch.wematch.domain.todo.repository;

import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoListRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.entity.Todo;
import WeMatch.wematch.mapper.TodoMapper;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class TodoRepository {

    public TodoMapper todoDAO;

    public void saveTodo(TodoSaveRequestDto todoSaveRequestDto){
        todoDAO.saveTodo(todoSaveRequestDto);
    }

    public void updateTodo(TodoRequestDto todo){
        todoDAO.updateTodo(todo);
    }

    public void checkTodo(TodoRequestDto todo){
        todoDAO.checkTodo(todo);
    }

    public void deleteTodo(TodoRequestDto todo){
        todoDAO.deleteTodo(todo);
    }

    public List<TodoResponseDto> findTodoByIdDate(Long memberId, LocalDate date){
        return todoDAO.findTodoByIdDate(memberId, date);
    }

    public List<TodoResponseDto> findMonthTodo(Long memberId,int year, int month){
        return todoDAO.findMonthTodo(memberId, year, month);
    }

    public TodoResponseDto findTodoById(Long todoId){
        TodoResponseDto todo = todoDAO.findTodoById(todoId);
        return todo;
    }

    public void deleteAll(){

    }
}