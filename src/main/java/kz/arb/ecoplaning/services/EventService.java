package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.EventDto;
import kz.arb.ecoplaning.models.EventList;

import java.util.List;

public interface EventService {

    List<EventList> getEventListByUser(Long userID);

    void saveEvent(EventDto event);

    List<EventDto> getAllEventByEventDayAfter();

    void subscribe(Long userId, Long eventId);

    void deleteEvent(Long userId, Long eventId);

}
