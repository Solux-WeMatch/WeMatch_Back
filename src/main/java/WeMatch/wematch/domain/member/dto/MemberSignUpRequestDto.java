package WeMatch.wematch.domain.member.dto;

import WeMatch.wematch.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.security.crypto.password.PasswordEncoder;

//회원가입 수행시 사용되는 DTO
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpRequestDto {
    private String email;
    private String password;
    private String name;

    //전달받은 DTO -> entity
    public Member toEntity() {
        return new Member(this.email,this.password,this.name);
    }
}
