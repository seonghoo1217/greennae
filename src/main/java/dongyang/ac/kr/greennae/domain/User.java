package dongyang.ac.kr.greennae.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name",unique = true)
    private String username;

    @Column(name = "user_realname")
    private String realname;

    @Column(name = "user_pw")
    private String password;

    @Column(name = "user_age")
    private String age;

    @Column(name = "user_street")
    private String street;

    @Column(name = "user_sex")
    private String sex;

    @Column(name = "user_role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Image> fileImage=new ArrayList<>();

    @Column(name = "user_img")
    private String imageName;

    @Builder
    public User(Long id, String username, String realname, String password, String age, String street, String sex, String role, String imageName) {
        this.id=id;
        this.username=username;
        this.realname=realname;
        this.password=password;
        this.age=age;
        this.sex=sex;
        this.street=street;
        this.role=role;
        this.imageName=imageName;
    }

}
