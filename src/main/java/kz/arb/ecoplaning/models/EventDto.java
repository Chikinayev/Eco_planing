package kz.arb.ecoplaning.models;

import java.time.LocalDateTime;
import java.util.List;

public class EventDto {
    public Long id;
    public String title;
    public String description;
    public String city;
    public UserDto createUser;
    public List<UserDto> subscribers;
    public int subscribersCount;
    public LocalDateTime eventCreatedDate;
    public LocalDateTime eventDay;
}
