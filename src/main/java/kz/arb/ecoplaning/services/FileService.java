package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void uploadFile(MultipartFile file, String token);

    Image loadFile(Long id);

    void deleteFile(Long userId);
}
