package WeMatch.wematch.mapper;

import WeMatch.wematch.domain.group.dto.*;
import WeMatch.wematch.domain.member.entity.Member;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    String test(String name);

    //Email로 회원 중복여부 판단
    boolean ifExists(String email);

    //회원가입 : Member table에 저장
    void save(Member member);

    // email로 Member 찿기
    Member findByEmail(String email);

    //groupId -> memberId -> event : TeamEventResponseDto
    List<TeamEventsResponseDto> getEvent(Long teamId);

    List<String> getTeamMembers(Long groupId);
    String getTeamName(Long groupId);

    void updateSleep(Long groupId, SleepTimeDto sleepTimeDto);
    SleepTimeDto getSleep(Long groupId);

    void insertMinute(Long groupId,int minute);
    MinuteTimeResponseDto getMinute(Long groupId);

    void insertFixedTime(Long memberId,String teamName, InsertFixedTimeRequestDto insertFixedTimeRequestDto);
    CandidateResponseDto getFixedDate(Long candidateId);
    List<Long> getTeamMembersByCandidate(Long candidateId);
    String getTeamNameByCandidateId(Long candidateId);

    InsertFixedTimeRequestDto getCandidate(Long candidateId);

    void deleteCandidates(Long groupId);
    void insertCandidates(Long groupId, List<TeamEventsResponseDto> results);
}
