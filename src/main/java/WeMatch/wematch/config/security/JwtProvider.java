package WeMatch.wematch.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String key;

    private final long EXPIRE_TIME=1000L*60*60;
    private Key secretKey;
    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }


    public String createToken(Authentication authentication) {

        String authorities=authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date expire = new Date((new Date()).getTime()+EXPIRE_TIME);
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put("roles",authorities);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expire)
                .setSubject(authentication.getName())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }

    // 권한정보 획득
    // Spring Security 인증과정에서 권한확인을 위한 기능
    // UserDetail 통해서 -> Authentication 반환함
    public Authentication getAuthentication(String token) {
        System.out.println("call getAuthentication");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAccount(token));
        System.out.println("getAuthorities call");
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 유저 account 획득
    public String getAccount(String token) {
        System.out.println("getAccount call");
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Authorization Header를 통해 인증을 한다.
    public String resolveToken(HttpServletRequest request) {
        System.out.println("resolveToken call");
        return request.getHeader("Authorization");
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        System.out.println("validateToken call");
        try {
            // Bearer 검증
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            System.out.println("parsing called");

            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            // 만료되었을 시 false
            System.out.println("success parsing claims");
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
