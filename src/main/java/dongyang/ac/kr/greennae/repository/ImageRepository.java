package dongyang.ac.kr.greennae.repository;

import dongyang.ac.kr.greennae.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByUserId(Long userId);
}
