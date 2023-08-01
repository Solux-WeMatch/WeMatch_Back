package WeMatch.wematch.domain.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Group {
    private long groupId;
    private long memberId;
    private long groupName;

    public Group(long groupId, long memberId) {
        this.groupId = groupId;
        this.memberId = memberId;
    }
}
