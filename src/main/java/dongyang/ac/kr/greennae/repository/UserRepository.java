package dongyang.ac.kr.greennae.repository;

import dongyang.ac.kr.greennae.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername (String username) throws UsernameNotFoundException;

    @Query("select u from User u")
    List<User> findAll();
}
