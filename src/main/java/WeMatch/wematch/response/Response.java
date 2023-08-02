package WeMatch.wematch.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@Getter
public class Response {

    private boolean isSuccess;
    private int code;
    private String message;
    private Object result;

    public static Response success(String message) {
        return new Response(true,OK.value(),message,null);
    }

    public static Response success(String message, Object result) {
        return new Response(true, OK.value(),message,result);
    }

    public static Response failure(String message) {
        return new Response(false, 400,message,null);
    }

    public static Response failure(HttpStatus status,String message) {
        return new Response(false,status.value(),message,null);
    }


}