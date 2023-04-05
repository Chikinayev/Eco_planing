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

    @Column(name = "organizer")
    private String organizer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")

    private List<Image> images = new ArrayList<>();

    private Long mainImageId;

    private LocalDateTime eventCreatedDate;

    @PrePersist
    private void init(){
        eventCreatedDate = LocalDateTime.now();
    }

    public void addImageToEvent(Image image) {
        image.setEvent(this);
        images.add(image);
    }
}
