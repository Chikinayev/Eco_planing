package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.EventDto;
import kz.arb.ecoplaning.models.EventList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    List<EventList> getEventListByUser(Long userID);

    EventDto saveEvent(EventDto event);

    List<EventDto> getAllEventByEventDayAfter();

    void subscribe(Long userId, Long eventId);

    void deleteEvent(Long userId, Long eventId);

    EventDto getEventDtoByID(Long id);
    List<EventDto> getEventByName(String name);

    void uploadFile(MultipartFile file, String id);
}
