package kz.arb.ecoplaning.repositories;

import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.models.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

//    Page<User> findAllBy(Pageable pageable);
    Page<User> findAllByActiveIsTrue(Pageable pageable);
//    Page<User> findAllByActiveIsTrueAndRoles(Set<Role> roles, Pageable pageable);

    @Query(value = "SELECT * FROM user u where u.id in (SELECT user_id FROM user_role s WHERE s.roles = :role) and u.active is true", nativeQuery = true)
    Page<User> findAllBy(@Param("role") String role, Pageable pageable);
//    Optional<User> getUserById(String id);

}
