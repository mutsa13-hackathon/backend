package mutsa.hackathon.match.service;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.match.dto.MatchRequestDto;
import mutsa.hackathon.match.entity.Matching;
import mutsa.hackathon.match.entity.MatchRequest;
import mutsa.hackathon.match.repository.MatchRepository;
import mutsa.hackathon.match.repository.MatchRequestRepository;
import mutsa.hackathon.users.entity.Users;
import mutsa.hackathon.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final UsersRepository usersRepository;
    private final MatchRequestRepository matchRequestRepository;

    public List<Matching> findMatches(MatchRequestDto request, Long requesterId) {
        findMyRequest(request, requesterId);
        List<MatchRequest> candidates = matchRequestRepository
                .findByDepartureAndCreatedAtAfter(
                        request.getDeparture(), LocalDateTime.now().minusMinutes(5)
                );
        Users requester = findUser(requesterId);
        List<Matching> matchedList = new ArrayList<>();

        for (MatchRequest candidate : candidates) {
            if (candidate.getUser().getUserId().equals(requester.getUserId())) continue;

            int matchCount = 0;
            if (candidate.getGender() == request.getGender()) matchCount++; //나이
            if (candidate.getAge() == request.getAge()) matchCount++; //성별
            if (candidate.getWithNum().equals(request.getWithNum())) matchCount++; //동승자 수

            if (matchCount >= 2) {
                Matching match = Matching.builder()
                        .user(requester)
                        .departure(request.getDeparture())
                        .createdAt(LocalDateTime.now())
                        .driver("미정")
                        .carNumber("미정")
                        .cost(0)
                        .distance(0.0)
                        .build();
                matchedList.add(matchRepository.save(match));
            }
        }
        return matchedList;
    }

    private MatchRequest findMyRequest(MatchRequestDto dto, Long requesterId){
        Users requester = findUser(requesterId);
        MatchRequest myRequest = matchRequestRepository.save(
                MatchRequest.builder()
                        .user(requester)
                        .gender(dto.getGender())
                        .age(dto.getAge())
                        .destination(dto.getDestination())
                        .departure(dto.getDeparture())
                        .withNum(dto.getWithNum())
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return myRequest;
    }
    private Users findUser(Long requesterId){
        Users requester = usersRepository.findById(requesterId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        return requester;
    }



}
