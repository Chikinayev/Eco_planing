package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    public final EventRepository eventRepository;
    public List<Event> listEvents(String title){
        if(title!=null) return eventRepository.findEventByTitle(title);
        return eventRepository.findAll();
    }

    public void saveEvent(Event event){
        log.info("Saving new {}", event);
        eventRepository.save(event);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
