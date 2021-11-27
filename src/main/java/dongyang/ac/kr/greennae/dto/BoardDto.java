package dongyang.ac.kr.greennae.dto;

import dongyang.ac.kr.greennae.domain.Board;
import dongyang.ac.kr.greennae.domain.User;
import lombok.*;

@Data
@NoArgsConstructor
public class BoardDto {


    private Long id;
    private String title;
    private String writer;
    private String content;
    private Long galleryId;
    private User user;

    @Builder
    public BoardDto(Long id, String title, String writer, String content, Long galleryId, User user){
        this.id=id;
        this.title=title;
        this.writer=writer;
        this.content=content;
        this.user=user;
        this.galleryId=galleryId;
    }



    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .user(user)
                .build();
    }


}
