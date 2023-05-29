package kz.arb.ecoplaning.repositories;

import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageById(Long id);

}
