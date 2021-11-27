package dongyang.ac.kr.greennae.controller;

import dongyang.ac.kr.greennae.dto.BoardDto;
import dongyang.ac.kr.greennae.principal.AccountContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @GetMapping("/boardCreate")
    public String boardCreateForm(Model model, BoardDto dto,
                                  @AuthenticationPrincipal AccountContext accountContext){

        log.info("boardCreate 호출");
        model.addAttribute("dto",dto);
        model.addAttribute("accountContext",accountContext);
        log.info("accountContext={}", accountContext);

        return "/board/boardCreate";
    }
    @PostMapping("/boardCreate")
    public String boardCreate(Model model,BoardDto dto,@AuthenticationPrincipal AccountContext accountContext) throws UnsupportedEncodingException {


        log.info("boardCreate");
        boardService.boardSave(dto);
        String title = URLEncoder.encode(dto.getTitle(), "UTF-8");

        return "redirect:read/"+title;
    }
}
