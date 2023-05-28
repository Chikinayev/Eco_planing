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
import java.util.List;

public interface EventService {

    List<Event> getEventByUser(Long userID);

}
