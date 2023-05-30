package kz.arb.ecoplaning.repositories;

import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageById(Long id);
    @Modifying
    @Query("DELETE FROM Image e WHERE e.user.id = :userId")
    void deleteImageByUserId(@Param("userId") Long userId);
    @Modifying
    @Query("DELETE FROM Image e WHERE e.event.id = :eventId")
    void deleteImageByEventId(@Param("eventId") Long eventId);

}
