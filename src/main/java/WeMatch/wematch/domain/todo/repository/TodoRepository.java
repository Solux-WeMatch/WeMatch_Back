package WeMatch.wematch.domain.todo.repository;

import WeMatch.wematch.domain.todo.entity.Todo;

import java.util.*;

public class TodoRepository {
    private static Map<Long, Todo> todolist = new HashMap<>();
    private static long sequence = 0L;

    private static final TodoRepository instance = new TodoRepository();

    public static TodoRepository getInstance() {
        return instance;
    }

    private TodoRepository(){

    }

    public Todo save(Todo todo){
        todo.setTodoId(++sequence);
        todolist.put(todo.getTodoId(), todo);
        return todo;
    }

    public Todo delete(Todo todo){
        todolist.remove(todo.getTodoId());
        return todo;
    }

//    public Todo findById(Long id){
//        return todolist.get(id);
//    }

    public Todo findById(Long TodoId){
        return todolist.get(TodoId);
    }

    public List<Todo> findAll() {
        return new ArrayList<>(todolist.values());
    }

    public void clearTodolist(){
        todolist.clear();
    }
}
