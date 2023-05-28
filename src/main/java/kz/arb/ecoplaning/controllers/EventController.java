package kz.arb.ecoplaning.controllers;


import kz.arb.ecoplaning.models.EventDto;
import kz.arb.ecoplaning.models.EventList;
import kz.arb.ecoplaning.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/eventListByID")
    public ResponseEntity<List<EventList>> getEvents(@RequestBody Long userID) {

        List<EventList> events = eventService.getEventListByUser(userID);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/saveEvent")
    public ResponseEntity<Void> saveEvent(@RequestBody EventDto eventDto) {

        eventService.saveEvent(eventDto);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/getEvents")
    public ResponseEntity<List<EventDto>> getEvents() {
        List<EventDto> allEvent = eventService.getAllEventByEventDayAfter();
        return new ResponseEntity<>(allEvent, HttpStatus.OK);
    }



}
