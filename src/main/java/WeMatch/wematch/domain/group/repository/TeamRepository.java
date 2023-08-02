package WeMatch.wematch.domain.group.repository;

import WeMatch.wematch.domain.group.dto.*;
import WeMatch.wematch.domain.member.dto.TeamCreateRequestDto;
import WeMatch.wematch.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamRepository {
    private final Mapper teamDAO;

    public List<TeamEventsResponseDto> getEvent(Long groupId) {
        return teamDAO.getEvent(groupId);
    }

    public GetTeamResponseDto getTeamInfo(Long groupId) {
        List<String> memberList= teamDAO.getTeamMembers(groupId);
        String teamName = teamDAO.getTeamName(groupId);
        return GetTeamResponseDto.builder()
                .groupName(teamName)
                .members(memberList)
                .build();
    }


    public void updateSleep(Long groupId, SleepTimeDto sleepTimeDto) {
        teamDAO.updateSleep(groupId, sleepTimeDto);
    }
    public SleepTimeDto getSleep(Long groupId) {
        return teamDAO.getSleep(groupId);
    }

    public void insertMinute(Long groupId, int minute) {
        teamDAO.insertMinute(groupId,minute);
    }
    public MinuteTimeResponseDto getMinute(Long groupId) {
        return teamDAO.getMinute(groupId);
    }

    public List<Long> getMembers(Long candidateId) {
        return teamDAO.getTeamMembersByCandidate(candidateId);
    }
    public String getTeamNameByCandidateId(Long candidateId) {return teamDAO.getTeamNameByCandidateId(candidateId);}
    public CandidateResponseDto getFixedDate(Long candidateId) {return teamDAO.getFixedDate(candidateId);}
    public void insertFixedTime(List<Long> members,String teamName,InsertFixedTimeRequestDto insertFixedTimeRequestDto) {
        for(Long memberId:members) {
            teamDAO.insertFixedTime(memberId, teamName,insertFixedTimeRequestDto);
        }
    }


    public InsertFixedTimeRequestDto getCandidate(Long candidateId) {return teamDAO.getCandidate(candidateId);}

    public void deleteCandidates(Long groupId) {teamDAO.deleteCandidates(groupId);}
    public void insertCandidates(Long groupId,List<TeamEventsResponseDto> results) {teamDAO.insertCandidates(groupId,results);}


    public void createTeam(TeamCreateRequestDto teamCreateRequestDto){
        teamDAO.createTeam(teamCreateRequestDto);
    }

    public void saveTeam(Long groupId, Long memberId){
        teamDAO.saveTeam(groupId, memberId);
    }

    public void exitTeam(Long groupId, Long memberId){
        teamDAO.exitTeam(groupId, memberId);
    }

    public void deleteTeam(Long groupId){
        teamDAO.deleteTeam(groupId);
    }

}

