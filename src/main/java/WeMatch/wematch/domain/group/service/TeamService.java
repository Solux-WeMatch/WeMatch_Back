package WeMatch.wematch.domain.group.service;

import WeMatch.wematch.domain.group.dto.*;
import WeMatch.wematch.domain.group.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.WeekFields;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamEventsResponseDto> getEvents(Long groupId) {
        List<TeamEventsResponseDto> results = teamRepository.getEvent(groupId);
        return results;
    }

    public List<TeamEventsResponseDto> getResult(List<TeamEventsResponseDto> events,int weekNumber,int year) {
        Iterator<TeamEventsResponseDto> iterator = events.iterator();
        while(iterator.hasNext()) {
            TeamEventsResponseDto event = iterator.next();
            if(event.getEventStartAt().toLocalDate().get(WeekFields.ISO.weekOfYear())!=weekNumber
                    || event.getEventStartAt().getYear()!=year) {
                iterator.remove();
            }
        }
        return events;
    }

    public GetTeamResponseDto getTeamInfo(Long groupId) {
        return teamRepository.getTeamInfo(groupId);
    }

    public void updateSleep(Long groupId, SleepTimeDto sleepTimeDto) {
        teamRepository.updateSleep(groupId, sleepTimeDto);
    }

    public SleepTimeDto getSleep(Long groupId) {
        return teamRepository.getSleep(groupId);
    }

    public void insertMinute(Long groupId,int minute) {
        teamRepository.insertMinute(groupId,minute);

    }
    public MinuteTimeResponseDto getMinute(Long groupId){
        return teamRepository.getMinute(groupId);
    }

    public void insertFixedTime(InsertFixedTimeRequestDto insertFixedTimeRequestDto,Long candidateId) {
        List<Long> members = teamRepository.getMembers(candidateId);
        String teamName = teamRepository.getTeamNameByCandidateId(candidateId);
        //GetFixedTimeRequestDto getDate = teamRepository.getFixedDate(candidateId);
        teamRepository.insertFixedTime(members,teamName,insertFixedTimeRequestDto);
    }

    public CandidateResponseDto getCandidate(Long candidateId) {
        InsertFixedTimeRequestDto candidate =  teamRepository.getCandidate(candidateId);
        return CandidateResponseDto.builder()
                .startDate(candidate.getStartAt().toLocalDate())
                .startAt(candidate.getStartAt().toLocalTime())
                .endAt(candidate.getEndAt().toLocalTime())
                .build();
    }
}

