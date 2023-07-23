package WeMatch.wematch.domain.todo.controller;

import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.repository.TodoRepository;
import WeMatch.wematch.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@RequestMapping("/todo")
@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/save_todo")
    public void saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        todoService.saveTodo(todoSaveRequestDto);
    }

    @PutMapping("/update_todo/{todoId}")
    public void updateTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDto){
        todoService.updateTodo(todoId, todoRequestDto);
    }

    @DeleteMapping("/delete_todo/{todoId}")
    public void deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDto){
        todoService.deleteTodo(todoId, todoRequestDto);
    }

    // memberId가 id인 사용자의 date 날짜의 todo 조회
    @GetMapping("/{id}/{date}")
    public List<TodoResponseDto> findByIdDate(@PathVariable Long memberId, @PathVariable Date todo_schedule){
        return todoService.findTodoByIdDate(memberId, todo_schedule);
    }
}
