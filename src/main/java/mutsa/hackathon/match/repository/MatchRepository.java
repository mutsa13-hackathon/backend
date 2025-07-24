package mutsa.hackathon.match.repository;

import mutsa.hackathon.match.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Matching, Long> {
    List<Matching> findAllByMatchId(Long matchId);
}