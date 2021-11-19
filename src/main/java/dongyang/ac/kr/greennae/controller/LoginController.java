package dongyang.ac.kr.greennae.controller;


import dongyang.ac.kr.greennae.dto.UsersDto;
import dongyang.ac.kr.greennae.repository.UserRepository;
import dongyang.ac.kr.greennae.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

//로그인,회원가입 컨트롤러
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@Log4j2
public class LoginController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/signin")
    public String loginForm(@RequestParam(value = "error",required = false)String error,
                            @RequestParam(value = "exception",required = false)String exception,
                            Model model,UsersDto dto){

        log.info("password ={}",dto.getPassword());

        model.addAttribute("error",error);
        model.addAttribute("exception",exception);

        return "login/signin";
    }



    @GetMapping("/signup")
    public String createUserForm(UsersDto dto, Model model){


        model.addAttribute("dto", dto);

        return "login/signup";
    }

   @PostMapping("/signup")
    public String createUser(@Validated UsersDto dto, BindingResult result,Model model) {
       if (result.hasErrors()) {
           Map<String, String> errorMap = new HashMap<>();

           for (FieldError error : result.getFieldErrors()) {
               errorMap.put(error.getField(), error.getDefaultMessage());
               log.error(error.getDefaultMessage());
               log.error(dto.toString());
           }

           throw new ValidationException("회원가입 에러!", (Throwable) errorMap);
       } else {
           try {
               userService.createUser(dto);
           } catch (Exception e) {
               log.error(e.getMessage());
               model.addAttribute("dto", dto);
               return "redirect:/login/signup";
           }
           return "/login/signin";
       }

   }
}
