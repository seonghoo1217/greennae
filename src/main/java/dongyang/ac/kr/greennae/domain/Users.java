package dongyang.ac.kr.greennae.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_realname")
    private String realname;

    @Column(name = "user_pw")
    private String password;

    @Column(name = "user_age")
    private int age;

    @Column(name = "user_street")
    private String street;

    @Column(name = "user_role")
    private String role;

    @Builder
    public Users(Long id,String username,String realname,String password,int age,String street, String role) {
        this.id=id;
        this.username=username;
        this.realname=realname;
        this.password=password;
        this.age=age;
        this.street=street;
        this.role=role;
    }
}
