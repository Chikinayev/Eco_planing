package kz.arb.ecoplaning.controllers;


import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService  eventService;

    @GetMapping("/")
    public String events(@RequestParam(name="title", required = false) String title, Model model){
        model.addAttribute("events", eventService.listEvents(title));
        return "events";
    }

    @PostMapping("/event/create")
    public String createEvent(Event event){
        eventService.saveEvent(event);
        return "redirect:/";
    }

    @GetMapping("/event/{id}")
    public String eventInfo(@PathVariable Long id, Model model){
        model.addAttribute("event", eventService.getEventById(id));
        return "event-info";
    }
    @PostMapping("/event/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "redirect:/";
    }
}
