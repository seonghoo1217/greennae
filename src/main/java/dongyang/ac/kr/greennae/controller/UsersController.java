package dongyang.ac.kr.greennae.controller;

import dongyang.ac.kr.greennae.domain.User;
import dongyang.ac.kr.greennae.dto.UsersDto;
import dongyang.ac.kr.greennae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UsersController {

    private final UserRepository userRepository;

    @GetMapping("/custom")
    public String userCustom(Model model, UsersDto usersDto){

        List<User> userList = userRepository.findAll();

        model.addAttribute("usersList", userList);

        return "users/custom";
    }
}
