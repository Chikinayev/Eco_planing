package kz.arb.ecoplaning.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    public static Image of(MultipartFile file, User user) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setContentType(file.getContentType());
        image.setOriginalImageName(file.getOriginalFilename());
        image.setBytes(file.getBytes());
        image.setUser(user);
        return image;
    }


}
