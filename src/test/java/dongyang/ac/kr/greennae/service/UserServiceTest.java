package dongyang.ac.kr.greennae.service;

import dongyang.ac.kr.greennae.dto.UsersDto;
import dongyang.ac.kr.greennae.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersDto dto;
    @Test
    public void 아이디널(){
        userRepository.findById(2L);
    }
}