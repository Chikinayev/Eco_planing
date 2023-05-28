package kz.arb.ecoplaning.services.impl;

import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.models.User;
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
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public List<Event> getEventByUser(Long userId) {
        User user = userService.findUserById(userId);
        return eventRepository.getEventByUser(user);
    }

}
