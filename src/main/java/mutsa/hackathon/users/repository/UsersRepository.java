package mutsa.hackathon.users.repository;

import mutsa.hackathon.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
