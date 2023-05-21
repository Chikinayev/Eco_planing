package kz.arb.ecoplaning.controllers;

import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.repositories.ImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private final ImageRepository imageRepository;

    public FileController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        Image image = new Image();
        imageRepository.save(image);


        return ResponseEntity.ok("Файл успешно сохранен");
    }
}
