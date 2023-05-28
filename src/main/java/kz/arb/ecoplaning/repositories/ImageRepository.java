package kz.arb.ecoplaning.repositories;

import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageById(Long id);

}
