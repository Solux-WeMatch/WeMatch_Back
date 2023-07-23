package WeMatch.wematch.domain.todo.repository;

import WeMatch.wematch.domain.todo.entity.Todo;
import WeMatch.wematch.mapper.TodoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.Date;

@Repository
@AllArgsConstructor
public class TodoRepository {

    public TodoMapper todoDAO;

    public void saveTodo(Todo todo){
        todoDAO.saveTodo(todo);
    }

    public void updateTodo(Todo todo){
        todoDAO.updateTodo(todo);
    }

    public void deleteTodo(Todo todo){
        todoDAO.deleteTodo(todo);
    }

    public Todo findTodoByIdDate(Long memberId, Date todo_schedule){
        Todo todo = todoDAO.findTodoByIdDate(memberId, todo_schedule);
        return todo; // todo가 여려개일 경우 대비 코드 수정
    }

    public Todo findTodoById(Long memberId, Long todoId){
        Todo todo = todoDAO.findTodoById(memberId, todoId);
        return todo;
    }
}
