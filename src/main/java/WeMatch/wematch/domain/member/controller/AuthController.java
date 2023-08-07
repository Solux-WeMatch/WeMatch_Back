package WeMatch.wematch.domain.member.controller;

import WeMatch.wematch.domain.group.dto.FindPasswordRequestDto;
import WeMatch.wematch.domain.member.dto.JwtRequestDto;
import WeMatch.wematch.domain.member.dto.MemberSignUpRequestDto;
import WeMatch.wematch.domain.member.entity.Member;
import WeMatch.wematch.domain.member.service.AuthService;
import WeMatch.wematch.domain.member.service.MemberService;
import WeMatch.wematch.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static WeMatch.wematch.response.Response.success;
import static WeMatch.wematch.response.ResponseMessage.*;

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

    //비밀번호 찾기
    @PatchMapping("/password")
    public Response findPassword(@RequestBody FindPasswordRequestDto findPasswordRequestDto) {
        Member member = memberService.getCurrentMember();
        authService.findPassword(findPasswordRequestDto,member);
        return success(SUCCESS_TO_FIND_PW);
    }

}
