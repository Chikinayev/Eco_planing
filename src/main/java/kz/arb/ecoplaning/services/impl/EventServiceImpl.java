package kz.arb.ecoplaning.services.impl;

import kz.arb.ecoplaning.models.*;
import kz.arb.ecoplaning.repositories.EventRepository;
import kz.arb.ecoplaning.repositories.UserRepository;
import kz.arb.ecoplaning.security.jwt.JwtTokenProvider;
import kz.arb.ecoplaning.services.EventService;
import kz.arb.ecoplaning.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public List<EventList> getEventListByUser(Long userId) {
        User user = userService.findUserById(userId);
        List<EventList> eventLists = new ArrayList<>();
        List<Event> events = eventRepository.getEventByUser(user);
        if (events.isEmpty()) {
            return Collections.emptyList();
        }
        for (Event event: events) {
            eventLists.add(event.getEventList());
        }
        return eventLists;
    }

    @Override
    public EventDto getEventDtoByID(Long id) {
        Event event = eventRepository.getById(id);

        EventDto eventDto = event.getEventDto();
        eventDto.subscribers = new ArrayList<>();
        for (User user: event.getSubscribers()) {
            eventDto.subscribers.add(user.getDto());
        }
        return eventDto;
    }

    @Override
    public void saveEvent(EventDto eventDto) {
        if (eventDto == null) {
            return;
        }
        Event event = Event.of(eventDto);
        User createdUser = userService.findUserById(eventDto.createUser.id);
        event.setUser(createdUser);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> getAllEventByEventDayAfter() {
        List<Event> all = eventRepository.findByEventDayAfter(LocalDateTime.now());
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event event: all) {
            eventDtos.add(event.getEventDto());
        }
        return eventDtos;
    }

    @Override
    public void subscribe(Long userId, Long eventId) {
        List<Long> subscribersByEvent = eventRepository.getSubscribersByEvent(eventId);
        System.out.println("aaaa" + subscribersByEvent);
        if (!subscribersByEvent.contains(userId)){
            eventRepository.addSubscribers(eventId, userId);
        }
    }

    @Override
    public void deleteEvent(Long userId, Long eventId) {
        System.out.println("aaaaaaaaaa");
        eventRepository.deleteEventById(eventId, userId);

    }
}
