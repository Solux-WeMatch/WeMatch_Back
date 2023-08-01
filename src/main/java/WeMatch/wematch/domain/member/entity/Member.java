package WeMatch.wematch.domain.member.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Member {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Role role;

    public Member(String email, String password, String name) {
        this.email=email; this.password=password; this.name=name;
        this.role=Role.ROLE_MEMBER;
    }

    public Member(String email, String password, String name,Role role) {
        this.email=email; this.password=password; this.name=name;
        this.role=Role.ROLE_MEMBER;
    }

    public void passwordEncoder(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

}
