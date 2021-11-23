package dongyang.ac.kr.greennae.repository;

import dongyang.ac.kr.greennae.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
