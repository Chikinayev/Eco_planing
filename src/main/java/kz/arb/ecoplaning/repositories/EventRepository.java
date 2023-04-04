package kz.arb.ecoplaning.repositories;


import kz.arb.ecoplaning.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventByTitle(String title);

}
