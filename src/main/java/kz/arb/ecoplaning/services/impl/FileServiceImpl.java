package kz.arb.ecoplaning.services.impl;

import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.models.User;
import kz.arb.ecoplaning.repositories.ImageRepository;
import kz.arb.ecoplaning.security.jwt.JwtTokenProvider;
import kz.arb.ecoplaning.services.FileService;
import kz.arb.ecoplaning.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final ImageRepository imageRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public void uploadFile(MultipartFile file, String token){
        if (file != null) {
            String email = jwtTokenProvider.getUserEmail(token);
            User user = userService.findUserByEmail(email);
            try {
                Image image = Image.of(file, user);
                this.imageRepository.save(image);
            } catch (Exception e) {
                throw new RuntimeException("Произашло ошибка при загрузке :: " + e.getMessage());
            }
        }
    }

    @Override
    public Image loadFile(Long id) {
        return this.imageRepository.getImageById(id);
    }

    @Override
    public void deleteFile(Long userId) {
        this.imageRepository.deleteImageByUserId(userId);
    }
}
