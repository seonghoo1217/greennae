package dongyang.ac.kr.greennae.controller;

import dongyang.ac.kr.greennae.domain.Image;
import dongyang.ac.kr.greennae.domain.User;
import dongyang.ac.kr.greennae.principal.AccountContext;
import dongyang.ac.kr.greennae.repository.ImageRepository;
import dongyang.ac.kr.greennae.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

//메인 컨트롤러 컨트롤러란 URL과 뷰(html파일)를 매핑 시켜주는것
@Controller
@RequestMapping("/")//RequestMapping은 공통된 URl넣을떄
@RequiredArgsConstructor
@Log4j2
public class MainController {

    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @GetMapping("/")
    public String index(Model model, Long id, @AuthenticationPrincipal AccountContext accountContext, User user){
            if (accountContext.getImageName()==null){
                return "redirect:/imageRegi";
            }
            else {
                model.addAttribute("accountContext", accountContext);
                return "/index";
            }
    }
    @GetMapping("/imageRegi")
    public String imageRegiSterForm(){

        return "/imageRegi";
    }
    @PostMapping("/imageRegi")
    public String imageRegiSter(){

        return "redirect:/login/signin";
    }

    @GetMapping("/myprofile")
    public String profile(Model model,Image image, @AuthenticationPrincipal AccountContext accountContext){
        User user = accountContext.returnUser();

        List<Image> imageList = imageService.getImageList(user.getId());
        log.info("========imageList={}",imageList);
        URLEncoder.encode(imageList.get(1).getThumbnailSaveName());
        model.addAttribute("image",image);
        model.addAttribute("imageList",imageList);
        model.addAttribute("accountContext",accountContext);

        return "/myprofile";
    }


}
