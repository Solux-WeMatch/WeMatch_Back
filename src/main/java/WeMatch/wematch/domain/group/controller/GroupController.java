package WeMatch.wematch.domain.group.controller;

import WeMatch.wematch.domain.group.entity.Group;
import WeMatch.wematch.domain.group.repository.GroupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/group")
public class GroupController {
    private GroupRepository groupRepository = GroupRepository.getInstance();

    // 그룹 생성
    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    // 사용자별 그룹 조회
    @GetMapping("/{id}")
    public Group showGroup(@RequestBody Group group){
        // memberId별로 속한 그룹 보여주기
    }

    // 그룹 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            groupRepository.deleteById(groupId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
