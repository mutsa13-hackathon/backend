package mutsa.hackathon.match.repository;

import mutsa.hackathon.match.entity.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {
    List<MatchRequest> findByDepartureAndCreatedAtAfter(String departure, LocalDateTime after);
}
