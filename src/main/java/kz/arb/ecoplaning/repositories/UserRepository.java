package kz.arb.ecoplaning.repositories;

import kz.arb.ecoplaning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email) ;

}
