package kz.arb.ecoplaning.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="originalImageName")
    private String originalImageName;
    @Column(name="size")
    private Long size;
    @Column(name="contentType")
    private String contentType;
    @Column(name = "isMainImage")
    private boolean isMainImage;
    @Lob
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Event event;
}