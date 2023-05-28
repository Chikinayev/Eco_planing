package kz.arb.ecoplaning.controllers;


import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.models.EventList;
import kz.arb.ecoplaning.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/eventListByID")
    public ResponseEntity<List<EventList>> getEvents(@RequestBody Long userID) {

        List<EventList> events = eventService.getEventListByUser(userID);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }



}
