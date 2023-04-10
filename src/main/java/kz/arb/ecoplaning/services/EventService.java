package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.repositories.EventRepository;
import kz.arb.ecoplaning.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    public List<Event> listEvents(String title){
        if(title!=null) return eventRepository.findEventByTitle(title);
        return eventRepository.findAll();
    }

    public void saveEvent(Principal principal, Event event, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        event.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize()!=0){
            image1 = toImageEntity(file1);
            image1.setMainImage(true);
            event.addImageToEvent(image1);
        }
        if(file2.getSize()!=0){
            image2 = toImageEntity(file2);
            event.addImageToEvent(image2);
        }
        if(file3.getSize()!=0){
            image3 = toImageEntity(file3);
            event.addImageToEvent(image3);
        }

        log.info("Saving new Event. Title: {}; Organizer email: {}", event.getTitle(), event.getUser().getEmail());
        Event eventDb = eventRepository.save(event);
        eventDb.setMainImageId((eventDb.getImages().get(0).getId()));
        eventRepository.save(event);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal ==null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalImageName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

}
