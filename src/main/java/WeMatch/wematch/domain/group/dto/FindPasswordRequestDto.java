package WeMatch.wematch.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindPasswordRequestDto {
    private String email;
    private String name;
}
