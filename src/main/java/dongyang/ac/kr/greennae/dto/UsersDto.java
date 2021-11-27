package dongyang.ac.kr.greennae.dto;

import dongyang.ac.kr.greennae.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UsersDto {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String sex;

    private String age;

    private String realname;

    private String street;

    private String role;

    private String imageName;

    @Builder
    public UsersDto(Long id,String username,String realname,String password,String sex,String street,String age,String role,String imageName){
        this.id=id;
        this.username=username;
        this.realname=realname;
        this.password=password;
        this.sex=sex;
        this.age=age;
        this.street=street;
        this.role=role;
        this.imageName=imageName;
    }

    public User toEntity(){
        return User.builder()
                .id(id)
                .username(username)
                .realname(realname)
                .password(password)
                .age(age)
                .sex(sex)
                .street(street)
                .role(role)
                .imageName(null)
                .build();
    }
}
