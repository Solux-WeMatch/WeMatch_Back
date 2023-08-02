package WeMatch.wematch.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TeamListResponseDto {
    private Long groupId;
    private String groupName;

    @Builder
    public TeamListResponseDto(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
}
