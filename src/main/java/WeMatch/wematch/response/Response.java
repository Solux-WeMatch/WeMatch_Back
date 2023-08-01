package WeMatch.wematch.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@Getter
@JsonPropertyOrder({"status","success","message","data"})
public class Response {

    private int status;
    private boolean success;
    private String message;
    private Object data;

    public static Response success(String message) {
        return new Response(OK.value(),true,message,null);
    }

    public static Response success(String message, Object result) {
        return new Response(OK.value(), true,message,result);
    }

    public static Response failure(HttpStatus status,String message) {
        return new Response(status.value(),false,message,null);
    }


}