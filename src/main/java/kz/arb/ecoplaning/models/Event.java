package kz.arb.ecoplaning.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "city")
    private String city;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "events_subscribers"
            , joinColumns = @JoinColumn(name = "event_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> subscribers;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
    private List<Image> images = new ArrayList<>();

    private Long mainImageId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column(name = "eventDay")
    private LocalDateTime eventDay;

    private LocalDateTime eventCreatedDate;

    @PrePersist
    private void init(){
        eventCreatedDate = LocalDateTime.now();
    }

    public void addImageToEvent(Image image) {
        image.setEvent(this);
        images.add(image);
    }

    public EventList getEventList() {
        EventList eventList = new EventList();
        eventList.id = this.getId();
        eventList.title = this.getTitle();
        eventList.eventDay = this.getEventDay();
        return eventList;
    }

    public EventDto getEventDto() {
        EventDto eventDto = new EventDto();
        eventDto.id = this.getId();
        eventDto.title = this.getTitle();
        eventDto.description = this.getDescription();
        eventDto.city = this.getCity();
        eventDto.eventCreatedDate = this.getEventCreatedDate();
        eventDto.eventDay = this.getEventDay();
        eventDto.subscribersCount = this.getSubscribers().size();
        return eventDto;
    }


    public static Event of(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.id);
        event.setTitle(eventDto.title);
        event.setDescription(eventDto.description);
        event.setCity(eventDto.city);
        event.setEventCreatedDate(eventDto.eventCreatedDate);
        event.setEventDay(eventDto.eventDay);
        return event;
    }


}
