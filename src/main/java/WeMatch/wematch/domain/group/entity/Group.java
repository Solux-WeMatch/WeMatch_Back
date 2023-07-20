package WeMatch.wematch.domain.group.entity;

import lombok.Data;

@Data
public class Group {
    private long groupId;
    private long memberId;

    public Group(long groupId, long memberId) {
        this.groupId = groupId;
        this.memberId = memberId;
    }
}
