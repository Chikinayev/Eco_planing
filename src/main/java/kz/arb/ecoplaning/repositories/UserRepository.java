package kz.arb.ecoplaning.repositories;

import kz.arb.ecoplaning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

//    Optional<User> getUserById(String id);

}
