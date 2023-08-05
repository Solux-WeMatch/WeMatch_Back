package WeMatch.wematch.domain.group.service;

import WeMatch.wematch.domain.group.dto.*;
import WeMatch.wematch.domain.group.repository.TeamRepository;
import WeMatch.wematch.domain.member.dto.TeamCreateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamEventsResponseDto> getEvents(Long groupId) {
        List<TeamEventsResponseDto> results = teamRepository.getEvent(groupId);
        return results;
    }

    public List<TeamEventsResponseDto> getResult(List<TeamEventsResponseDto> events, int weekNumber, int year) {
        Iterator<TeamEventsResponseDto> iterator = events.iterator();
        while (iterator.hasNext()) {
            TeamEventsResponseDto event = iterator.next();
            if (event.getEventStartAt().toLocalDate().get(WeekFields.ISO.weekOfYear()) != weekNumber
                    || event.getEventStartAt().getYear() != year) {
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

    public void insertMinute(Long groupId, int minute) {
        teamRepository.insertMinute(groupId, minute);

    }

    public MinuteTimeResponseDto getMinute(Long groupId) {
        return teamRepository.getMinute(groupId);
    }

    public void insertFixedTime(InsertFixedTimeRequestDto insertFixedTimeRequestDto, Long candidateId) {
        List<Long> members = teamRepository.getMembers(candidateId);
        String teamName = teamRepository.getTeamNameByCandidateId(candidateId);
        //GetFixedTimeRequestDto getDate = teamRepository.getFixedDate(candidateId);
        teamRepository.insertFixedTime(members, teamName, insertFixedTimeRequestDto);
    }

    public GetCandidateFixDto getCandidate(Long candidateId) {
        InsertFixedTimeRequestDto candidate = teamRepository.getCandidate(candidateId);
        return GetCandidateFixDto.builder()
                .startDate(candidate.getStartAt().toLocalDate())
                .startAt(candidate.getStartAt().toLocalTime())
                .endAt(candidate.getEndAt().toLocalTime())
                .build();
    }

    public List<CandidateResponseDto> getCandidates(Long groupId,int weekNumber, int year) {
        List<CandidateResponseDto> returnDto = new ArrayList<>();
        List<TeamEventsResponseDto> results = teamRepository.getEvent(groupId);
        // weekNumber, year로 제한
        results = getResult(results,weekNumber,year);
        // no results -> return
        if(results.isEmpty()) {toResponseDto(returnDto, results);}

        List<TeamEventsResponseDto> results2 = teamRepository.getEvent(groupId);
        results2 = getResult(results2,weekNumber,year);
        //startAt으로 정렬
        Collections.sort(results2, Comparator.comparing(TeamEventsResponseDto::getEventStartAt));

        //delete
        teamRepository.deleteCandidates(groupId);
        //getCandidates
        results = getBlankTime(results);
        results = setFirstLast(results, results2, groupId);
        //수면시간 제거
        results = deleteSleepTime(results, groupId);
        // 재정렬
        Collections.sort(results, Comparator.comparing(TeamEventsResponseDto::getEventStartAt));
        // 분단위 시간 제거
        results = deleteMinuteTime(results,teamRepository.getMinute(groupId).getMinute());
        //insertCandidates
        List<Long> candidateIds = teamRepository.insertCandidates(groupId,results);
        addCandidateIds(results, candidateIds);
        return toResponseDto(returnDto, results);
    }

    private List<CandidateResponseDto> toResponseDto(List<CandidateResponseDto> returnDto, List<TeamEventsResponseDto> results) {
        for(TeamEventsResponseDto result : results) {
            returnDto.add(
                    CandidateResponseDto.builder()
                            .start(result.getEventStartAt())
                            .end(result.getEventEndAt())
                            .title(result.getCandidateId())
                            .build()
            );
        }
        return returnDto;
    }


    private void addCandidateIds(List<TeamEventsResponseDto> results, List<Long> candidateIds) {
        Iterator<TeamEventsResponseDto> iterator = results.iterator();
        int i=0;
        while (iterator.hasNext()) {
            TeamEventsResponseDto item = iterator.next();
            item.setCandidateId(candidateIds.get(i++));
        }
    }

    public List<TeamEventsResponseDto> getBlankTime(List<TeamEventsResponseDto> events) {
        List<TeamEventsResponseDto> missingTimeIntervals = new ArrayList<>();


        if (events.isEmpty()) {
            // 모든 시간대가 없는 경우
            missingTimeIntervals.add(
                    TeamEventsResponseDto.builder()
                            .eventStartAt(LocalDateTime.MIN)
                            .eventEndAt(LocalDateTime.MAX)
                            .build());
            return missingTimeIntervals;
        }

        events.sort((event1, event2) -> event1.getEventStartAt().compareTo(event2.getEventStartAt()));

        LocalDateTime startTime = events.get(0).getEventStartAt();
        LocalDateTime endTime = events.get(0).getEventEndAt();

        for (int i = 1; i < events.size(); i++) {
            LocalDateTime nextStartTime = events.get(i).getEventStartAt();
            LocalDateTime nextEndTime = events.get(i).getEventEndAt();

            if (endTime.isBefore(nextStartTime)) {
                // 이벤트 간의 빈 구간이 존재
                missingTimeIntervals.add(TeamEventsResponseDto.builder()
                        .eventStartAt(endTime)
                        .eventEndAt(nextStartTime)
                        .build());
            }

            if (nextEndTime.isAfter(endTime)) {
                // 이벤트 시간 갱신
                endTime = nextEndTime;
            }
        }

        return missingTimeIntervals;
    }

    public List<TeamEventsResponseDto> deleteSleepTime(List<TeamEventsResponseDto> results, Long groupId) {
        SleepTimeDto sleepTime = getSleep(groupId);
        LocalTime startSleep = sleepTime.getStartAt(); LocalTime endSleep = sleepTime.getEndAt();
        //각 날짜별로 가장 이른 후보시간의 index 불러옴
        List<Integer> earliestTimes = getEarliestTimes(results);
        //각 날짜별로 가장 늦은 후보시간의 index 불러옴
        List<Integer> lastTimes = getLastTimes(results);


        //그 후보시간의 startAt.isBefore sleepTime의 endAt
        for (int i = 0; i < results.size(); i++) {
            if(earliestTimes.contains(i)) {
                if (results.get(i).getEventStartAt().toLocalTime().isBefore(endSleep)) {
                    //-> 후보시간.startAt = sleepTime.endAt
                    results.get(i).setEventStartAt(LocalDateTime.of(results.get(i).getEventStartAt().toLocalDate(), endSleep));
                }
            }
        }
        // OR 후보시간의 endAt.isAfter sleepTime의 startAt
        int size = results.size();
        for (int i = 0; i < size; i++) {
            if(lastTimes.contains(i)) {
                TeamEventsResponseDto dto = TeamEventsResponseDto.builder()
                        .eventStartAt(LocalDateTime.of(results.get(i+1).getEventStartAt().toLocalDate(),endSleep))
                        .eventEndAt(LocalDateTime.of(results.get(i+1).getEventStartAt().toLocalDate(),results.get(i).getEventEndAt().toLocalTime()))
                        .build();
                results.add(dto);
            }
        }
        lastTimes = getLastTimes(results);
        size = results.size();
        for (int i = 0; i < size; i++) {
            if(lastTimes.contains(i)) {
                if (results.get(i).getEventEndAt().toLocalTime().isAfter(startSleep)) {
                    //-> 후보시간.endAt = sleepTime.startAt
                    results.get(i).setEventEndAt(LocalDateTime.of(results.get(i).getEventEndAt().toLocalDate(), startSleep));
                }
            }
        }
        return results;
    }

    private List<Integer> getLastTimes(List<TeamEventsResponseDto> results) {
        List<Integer> lastTimes = results.stream()
                .collect(Collectors.groupingBy(dto -> dto.getEventStartAt().toLocalDate(),
                        Collectors.maxBy(Comparator.comparing(TeamEventsResponseDto::getEventEndAt))))
                .values()
                .stream()
                .map(opt -> opt.orElse(null))
                .map(results::indexOf)
                .collect(Collectors.toList());
        return lastTimes;
    }

    private List<Integer> getEarliestTimes(List<TeamEventsResponseDto> results) {
        List<Integer> earliestTimes = results.stream()
                .collect(Collectors.groupingBy(dto -> dto.getEventStartAt().toLocalDate(),
                        Collectors.minBy(Comparator.comparing(TeamEventsResponseDto::getEventStartAt))))
                .values()
                .stream()
                .map(opt -> opt.orElse(null))
                .map(results::indexOf)
                .collect(Collectors.toList());
        return earliestTimes;
    }

    public List<TeamEventsResponseDto> setFirstLast (List < TeamEventsResponseDto > results, List < TeamEventsResponseDto > events, Long groupId){
        TeamEventsResponseDto dtoSetFirst = TeamEventsResponseDto.builder()
                .eventStartAt(LocalDateTime.of(events.get(0).getEventStartAt().toLocalDate(), LocalTime.of(0, 0)))
                .eventEndAt(events.get(0).getEventStartAt())
                .build();
        results.add(0, dtoSetFirst);

        int lastIdx = events.size() - 1;
        TeamEventsResponseDto dtoSetLast = TeamEventsResponseDto.builder()
                .eventStartAt(events.get(lastIdx).getEventEndAt())
                .eventEndAt(LocalDateTime.of(events.get(lastIdx).getEventStartAt().toLocalDate().plusDays(1), LocalTime.of(0, 0)))
                .build();
        results.add(dtoSetLast);
        return results;

    }
    public List<TeamEventsResponseDto> deleteMinuteTime(List<TeamEventsResponseDto> results, int minute) {

        Iterator<TeamEventsResponseDto> iterator = results.iterator();
        while (iterator.hasNext()) {
            TeamEventsResponseDto item = iterator.next();
            Duration duration = Duration.between(item.getEventStartAt(), item.getEventEndAt());
            if (minute>duration.toMinutes()) {
                iterator.remove(); // 요소 삭제
            }
        }
        return results;
    }

    public Long getTeamId(String teamName){
        return teamRepository.getTeamId(teamName);
    }

    // team 새로 생성
    public void createTeam(TeamCreateRequestDto teamCreateRequestDto){
        teamRepository.createTeam(teamCreateRequestDto);
    }

    // team 테이블에 memberId 입력
    public void saveTeam(Long groupId, Long memberId){
        teamRepository.saveTeam(groupId, memberId);
    }

    public void exitTeam(Long groupId, Long memberId){
        teamRepository.exitTeam(groupId, memberId);
    }

    public void deleteTeam(Long groupId){
        teamRepository.deleteTeam(groupId);
    }
}
