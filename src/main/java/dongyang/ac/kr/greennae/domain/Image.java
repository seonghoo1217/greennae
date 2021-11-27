package dongyang.ac.kr.greennae.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_oriname")
    private String originalFileName;

    @Column(name = "file_name")
    private String fileName;


    @Column(name = "file_folder")
    private String folderPath;

    @Column(name = "file_fullname")
    private String thumbnailSaveName;


    @ManyToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;

        return Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return 548781654;
    }

    @Builder
    public Image (Long id, String fileName , String originalFileName, String folderPath, User user, String thumbnailSaveName){
        this.id=id;
        this.fileName=fileName;
        this.originalFileName=originalFileName;
        this.folderPath=folderPath;
        this.thumbnailSaveName=thumbnailSaveName;
        this.user = user;
    }
}
