package dongyang.ac.kr.greennae.controller.api;

import dongyang.ac.kr.greennae.domain.Users;
import dongyang.ac.kr.greennae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@Log4j2
public class CustomUsersController {

    private final UserRepository userRepository;

    @GetMapping("/user/{id}/upgrade")
    public ResponseEntity<?> userUpgrade(@PathVariable("id")Long id){
        Users findUser = userRepository.findById(id).get();

        findUser.setRole("ROLE_MEMBER");
        userRepository.save(findUser);
        log.info("findUser={}",findUser);

        return ResponseEntity.ok().body(1);
    }

    @GetMapping("/user/{id}/delete")
    public ResponseEntity<?> userDelete(@PathVariable("id")Long id){
        Users findUser = userRepository.findById(id).get();

        userRepository.delete(findUser);
        log.info("findUser={}",findUser);

        return ResponseEntity.ok().body(1);
    }
}
