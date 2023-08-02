package WeMatch.wematch.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamSaveRequestDto {
    private String email;
    private Long teamId;
    private Long memberId;

    public TeamSaveRequestDto(String email, Long teamId, Long memberId) {
        this.email = email;
        this.teamId = teamId;
        this.memberId = memberId;
    }
}
