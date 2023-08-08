package WeMatch.wematch.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamCreateRequestDto {
    private Long memberId;
    private String teamName;

    public TeamCreateRequestDto(Long memberId, String teamName) {
        this.memberId = memberId;
        this.teamName = teamName;
    }
}
