package WeMatch.wematch.domain.todo.controller;

import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
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

    @PostMapping("/save")
    public void saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        todoService.saveTodo(todoSaveRequestDto);
    }

    @PutMapping("/update")
    public void updateTodo(@RequestParam Long todoId, @RequestBody TodoRequestDto todoRequestDto){
        todoService.updateTodo(todoId, todoRequestDto);
    }

    @PutMapping("/check")
    public void checkTodo(@RequestParam Long todoId, @RequestBody TodoRequestDto todoRequestDto){
        todoService.checkTodo(todoRequestDto.getMemberId(), todoId);
    }

    @DeleteMapping("/delete}")
    public void deleteTodo(@RequestParam Long todoId, @RequestBody TodoRequestDto todoRequestDto){
        todoService.deleteTodo(todoId, todoRequestDto);
    }

    // memberId가 id인 사용자의 date 날짜의 todo 조회
    @GetMapping
    public List<TodoResponseDto> findByIdDate(@RequestParam Long memberId, @RequestParam Date todo_schedule){
        return todoService.findTodoByIdDate(memberId, todo_schedule); // 코드 수정 필요
    }
}
