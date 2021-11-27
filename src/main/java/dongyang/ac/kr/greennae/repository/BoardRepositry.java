package dongyang.ac.kr.greennae.repository;

import dongyang.ac.kr.greennae.domain.Board;
import me.project.namuwikiPro.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface BoardRepositry extends JpaRepository<Board,Long> {


    Optional<Board>findByTitle(String title) throws UsernameNotFoundException;
}
