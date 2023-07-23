package WeMatch.wematch.domain.todo.controller;

import WeMatch.wematch.domain.todo.entity.Todo;
import WeMatch.wematch.domain.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/todo")
public class TodoController {
    private TodoRepository todoRepository = TodoRepository.getInstance();

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long TodoId, @RequestBody Todo updatedTodo) {
        Optional<Todo> todo = todoRepository.findById(TodoId);
        if (todo.isPresent()) {
            Todo existingTodo = todo.get();
            existingTodo.setTodoName(updatedTodo.getTodoName());
            existingTodo.setCompleted(updatedTodo.isCompleted());
            todoRepository.save(existingTodo);
            return ResponseEntity.ok(existingTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long TodoId) {
        Optional<Todo> todo = todoRepository.findById(TodoId);
        if (todo.isPresent()) {
            todoRepository.deleteById(TodoId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
