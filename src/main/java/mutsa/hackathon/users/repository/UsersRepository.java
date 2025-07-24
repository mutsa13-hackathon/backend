package mutsa.hackathon.users.repository;
import java.util.Optional;
import mutsa.hackathon.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email); // 로그인이나 인증용으로도 자주 사용됨
}