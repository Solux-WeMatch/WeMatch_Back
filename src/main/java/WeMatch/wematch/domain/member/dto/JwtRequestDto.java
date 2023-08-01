package WeMatch.wematch.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestDto {
    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken authenticate() {
        //Authentication의 구현체 반환
        return new UsernamePasswordAuthenticationToken(email,password);
    }

}
