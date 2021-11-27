package dongyang.ac.kr.greennae.domain;

import dongyang.ac.kr.greennae.dto.BoardDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity @Getter @Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Lob
    @Column(name = "board_title",unique = true)
    private String title;

    @Lob
    @Column(name = "board_content")
    private String content;

    @Column(name = "board_writer")
    private String writer;

    @Column(name = "galleryId")
    private Long galleryId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedBy
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Board(Long id, String title,User user, String content, String writer, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id=id;
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.user=user;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;

    }

    public Board() {

    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }

    public BoardDto toDto(){
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .user(user)
                .build();
    }

}

