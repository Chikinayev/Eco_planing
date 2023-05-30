package kz.arb.ecoplaning.repositories;


import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventByTitle(String title);

    List<Event> getEventByUser(User user);

    List<Event> findByEventDayAfter(LocalDateTime currentDateTime);
    @Modifying
    @Query("DELETE FROM Event e WHERE e.id = :id and e.user.id = :userId")
    void deleteEventById(@Param("id") Long id , @Param("userId") Long userId);

    @Modifying
    @Query(value = "INSERT INTO events_subscribers (event_id, user_id) VALUES (:id, :userId)", nativeQuery = true)
    void addSubscribers(@Param("id") Long id, @Param("userId") Long userId);
    @Query(value = "SELECT user_id FROM events_subscribers s WHERE s.event_id = :id", nativeQuery = true)
    List<Long> getSubscribersByEvent(@Param("id") Long id);

    List<Event> findByTitleContaining(String title);




}
