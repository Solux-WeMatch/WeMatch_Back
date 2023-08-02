package WeMatch.wematch.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamCreateRequestDto {
    private Long memberId;
    private Long teamId;
    private String teamName;

    public TeamCreateRequestDto(Long memberId, Long teamId, String teamName) {
        this.memberId = memberId;
        this.teamId = teamId;
        this.teamName = teamName;
    }

}
