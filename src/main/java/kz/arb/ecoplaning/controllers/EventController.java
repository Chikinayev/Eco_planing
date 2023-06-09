package kz.arb.ecoplaning.controllers;


import kz.arb.ecoplaning.models.EventDto;
import kz.arb.ecoplaning.models.EventList;
import kz.arb.ecoplaning.models.EventFilterPage;
import kz.arb.ecoplaning.models.ReturnFilter;
import kz.arb.ecoplaning.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

     @PostMapping("/getEventSubscribeByUser")
    public ResponseEntity<List<EventList>> getEventSubscribeByUser(@RequestBody Long userID) {

        List<EventList> events = eventService.getEventSubscribeByUser(userID);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/eventDtoByID")
    public ResponseEntity<EventDto> getEventDto(@RequestBody Long userID) {

        EventDto eventDtoByID = eventService.getEventDtoByID(userID);

        return new ResponseEntity<>(eventDtoByID, HttpStatus.OK);
    }

    @PostMapping("/getEventByName")
    public ResponseEntity<List<EventDto>> getEventByName(@RequestBody String find) {
        System.out.println("aaaaa"+ find);
        if (find.isEmpty() || find == null){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        List<EventDto> eventByName = eventService.getEventByName(find.toLowerCase());

        return new ResponseEntity<>(eventByName, HttpStatus.OK);
    }


    @PostMapping("/getEventByFilter")
    public ResponseEntity<ReturnFilter> getEventByFilter(@RequestBody EventFilterPage filterPage) {
        if (filterPage == null){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        System.out.println("qwe" + filterPage);

        ReturnFilter eventByFilter = eventService.getEventByFilter(filterPage);

        return new ResponseEntity<>(eventByFilter, HttpStatus.OK);
    }

    @PostMapping("/getEventReverseByFilter")
    public ResponseEntity<ReturnFilter> getEventReverseByFilter(@RequestBody EventFilterPage filterPage) {
        if (filterPage == null){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        System.out.println("qwe" + filterPage);

        ReturnFilter eventByFilter = eventService.getEventReverseByFilter(filterPage);

        return new ResponseEntity<>(eventByFilter, HttpStatus.OK);
    }



    @PostMapping("/saveEvent")
    public ResponseEntity<Long> saveEvent(@RequestBody EventDto eventDto) {

        EventDto eventDto1 = eventService.saveEvent(eventDto);

        return new ResponseEntity<>(eventDto1.id, HttpStatus.OK);
    }

    @PostMapping("/getEvents")
    public ResponseEntity<List<EventDto>> getEvents() {
        List<EventDto> allEvent = eventService.getAllEventByEventDayAfter();
        return new ResponseEntity<>(allEvent, HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@RequestParam Long userId, @RequestParam Long eventId) {
        eventService.subscribe(userId, eventId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/deleteEvent")
    public ResponseEntity<Void> deleteEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        eventService.deleteEvent(userId, eventId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/uploadEvent")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {

        System.out.println("qqq" + id);
        this.eventService.uploadFile(file, id);

//        return ResponseEntity.ok("Файл успешно сохранен");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }



}
