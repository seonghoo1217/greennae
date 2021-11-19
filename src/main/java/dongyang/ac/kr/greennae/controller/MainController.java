package dongyang.ac.kr.greennae.controller;

import dongyang.ac.kr.greennae.principal.AccountContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//메인 컨트롤러 컨트롤러란 URL과 뷰(html파일)를 매핑 시켜주는것
@Controller
@RequestMapping("/")//RequestMapping은 공통된 URl넣을떄
public class MainController {

    @GetMapping("/")
    public String index(Model model,@AuthenticationPrincipal AccountContext accountContext){

        model.addAttribute("accountContext",accountContext);

        return "index";
    }
}
