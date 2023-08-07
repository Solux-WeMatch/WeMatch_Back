package WeMatch.wematch.domain.todo.controller;

import WeMatch.wematch.domain.event.dto.EventResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoListRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoRequestDto;
import WeMatch.wematch.domain.todo.dto.TodoResponseDto;
import WeMatch.wematch.domain.todo.dto.TodoSaveRequestDto;
import WeMatch.wematch.domain.todo.service.TodoService;
import WeMatch.wematch.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static WeMatch.wematch.response.ResponseMessage.*;

@CrossOrigin
@RequestMapping("/todo")
@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/save")
    public Response saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        todoService.saveTodo(todoSaveRequestDto);
        // TodoResponseDto result = todoService.findById(todoSaveRequestDto);
        TodoSaveRequestDto result = todoSaveRequestDto;
        return Response.success(SUCCESS_TODO_SAVE, result);
    }

    @PutMapping("/update")
    public Response updateTodo(@RequestBody TodoRequestDto todoRequestDto){
        todoService.updateTodo(todoRequestDto);
        TodoRequestDto result = todoRequestDto;
        return Response.success(SUCCESS_TODO_UPDATE, result);
    }

    @PutMapping("/check")
    public Response checkTodo(@RequestBody TodoRequestDto todoRequestDto){
        todoService.checkTodo(todoRequestDto);
        TodoRequestDto result = todoRequestDto;
        return Response.success(SUCCESS_TODO_CHECK, result);
    }

    @DeleteMapping("/delete")
    public Response deleteTodo(@RequestBody TodoRequestDto todoRequestDto){
        todoService.deleteTodo(todoRequestDto);
        TodoRequestDto result = todoRequestDto;
        return Response.success(SUCCESS_TODO_DELETE, result);
    }

    // memberId가 id인 사용자의 date 날짜의 todo 조회
    @GetMapping
    public Response findByIdDate(@RequestParam Long memberId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        List<TodoResponseDto> result = todoService.findTodoByIdDate(memberId, date); // memberService의 memberId로 수정
        return Response.success(SUCCESS, result);
    }

    @GetMapping("/month")
    public Response findMonthTodo(@RequestParam Long memberId,@RequestParam int year, @RequestParam int month){
        List<TodoResponseDto> result = todoService.findMonthTodo(memberId, year, month); // memberService의 memberId로 수정
        return Response.success(SUCCESS, result);
    }
}
