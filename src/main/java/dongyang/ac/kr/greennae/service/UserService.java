package dongyang.ac.kr.greennae.service;

import dongyang.ac.kr.greennae.domain.Users;
import dongyang.ac.kr.greennae.dto.UsersDto;
import dongyang.ac.kr.greennae.principal.AccountContext;
import dongyang.ac.kr.greennae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public Long createUser  (UsersDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        if(dto.getUsername().equals("dongyang1234")){
            dto.setRole("ROLE_ADMIN");
        }else {
            dto.setRole("ROLE_USER");
        }
        return userRepository.save(dto.toEntity()).getId();
    }
    @Transactional
    public int findById(Long id){
        Users findUser = userRepository.findById(id).get();
        if(findUser == null){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users= userRepository.findByUsername(username).get();

        return new AccountContext(users);
    }
}
