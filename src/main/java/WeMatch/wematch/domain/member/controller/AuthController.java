package WeMatch.wematch.domain.member.controller;

import WeMatch.wematch.domain.member.dto.JwtRequestDto;
import WeMatch.wematch.domain.member.dto.MemberSignUpRequestDto;
import WeMatch.wematch.domain.member.service.AuthService;
import WeMatch.wematch.domain.member.service.MemberService;
import WeMatch.wematch.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static WeMatch.wematch.response.Response.success;
import static WeMatch.wematch.response.ResponseMessage.SUCCESS_SIGN_IN;
import static WeMatch.wematch.response.ResponseMessage.SUCCESS_SIGN_UP;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/testMapping")
    public String testMapping(String name) {
        return authService.test(name);
    }

    //회원가입
    @PostMapping("/sign-up")
    public Response signUp(@RequestBody MemberSignUpRequestDto request) {
        authService.singUp(request);
        return success(SUCCESS_SIGN_UP);
    }

    //로그인
    @PostMapping("/login")
    public Response login(@RequestBody JwtRequestDto request) {
        return success(SUCCESS_SIGN_IN,authService.login(request));
    }
}
