package WeMatch.wematch.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //header에서 token 꺼냄
        String token = jwtProvider.resolveToken(request);
        //유효성 검사
        System.out.println("resolveToken 성공");
        if (token != null && jwtProvider.validateToken(token)) {
            // check access token
            token = token.split(" ")[1].trim();
            //token -> Authentication 가져와서 Security context에 저장
            System.out.println("getAuthentication call");
            Authentication auth = jwtProvider.getAuthentication(token);
            System.out.println("getAuthentication success");
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("getSecurityContext success");
        }

        System.out.println("try filetrChain");
        filterChain.doFilter(request, response);
        System.out.println("success filerChain");
    }
}
