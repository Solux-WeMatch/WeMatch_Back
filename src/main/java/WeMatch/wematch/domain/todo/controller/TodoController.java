package WeMatch.wematch.domain.todo.controller;

import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.service.TodoService;
import WeMatch.wematch.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static WeMatch.wematch.response.ResponseMessage.*;

@RequestMapping("/todo")
@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/save")
    public Response saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        TodoResponseDto result = todoService.saveTodo(todoSaveRequestDto);
        return Response.success(SUCCESS_TODO_SAVE, result);
    }

    @PutMapping("/update")
    public Response updateTodo(@RequestBody TodoRequestDto todoRequestDto){
        TodoResponseDto result = todoService.updateTodo(todoRequestDto);
        return Response.success(SUCCESS_TODO_UPDATE, result);
    }

    @PutMapping("/check")
    public Response checkTodo(@RequestBody TodoRequestDto todoRequestDto){
        TodoResponseDto result = todoService.checkTodo(todoRequestDto);
        return Response.success(SUCCESS_TODO_CHECK, result);
    }

    @DeleteMapping("/delete")
    public Response deleteTodo(@RequestBody TodoRequestDto todoRequestDto){
        TodoResponseDto result = todoService.deleteTodo(todoRequestDto);
        return Response.success(SUCCESS_TODO_DELETE, result);
    }

    // memberId가 id인 사용자의 date 날짜의 todo 조회
    @GetMapping
    public Response findByIdDate(@RequestParam Long memberId, @RequestParam LocalDate todo_schedule){
        List<TodoResponseDto> result = todoService.findTodoByIdDate(memberId, todo_schedule); // memberService의 memberId로 수정
        return Response.success(SUCCESS, result);
    }
}
