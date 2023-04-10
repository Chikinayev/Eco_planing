package kz.arb.ecoplaning.controllers;


import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.repositories.EventRepository;
import kz.arb.ecoplaning.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService  eventService;

    private final EventRepository eventRepository;

    @GetMapping("/")
    public String events(@RequestParam(name="title", required = false) String title,
                         Principal principal, Model model){
        model.addAttribute("events", eventService.listEvents(title));
        model.addAttribute("user", eventService.getUserByPrincipal(principal));
        return "events";
    }

    @PostMapping("/event/create")
    public String createEvent(@RequestParam("file1") MultipartFile file1,
                              @RequestParam("file2") MultipartFile file2,
                              @RequestParam("file3") MultipartFile file3, Event event, Principal principal)throws IOException {
        eventService.saveEvent(principal, event, file1, file2, file3);
        return "redirect:/";
    }


    @GetMapping("/event/{id}")
    public String eventInfo(@PathVariable Long id, Model model){
        Event event = eventService.getEventById(id);
        model.addAttribute("event", eventService.getEventById(id));
        model.addAttribute("images", event.getImages());
        return "event-info";
    }
    @PostMapping("/event/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "redirect:/";
    }
}
