package kz.arb.ecoplaning.controllers;


import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.repositories.EventRepository;
import kz.arb.ecoplaning.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/eventsByID")
    public ResponseEntity<List<Event>> getEvents(@RequestBody Long userID) {

        List<Event> events = eventService.getEventByUser(userID);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }



}
