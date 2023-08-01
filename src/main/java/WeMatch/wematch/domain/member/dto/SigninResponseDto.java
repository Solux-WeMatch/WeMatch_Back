package WeMatch.wematch.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SigninResponseDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String token;
}
