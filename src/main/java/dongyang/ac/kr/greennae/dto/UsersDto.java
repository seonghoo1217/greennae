package dongyang.ac.kr.greennae.dto;

import dongyang.ac.kr.greennae.domain.Users;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UsersDto {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private int age;

    private String realname;

    private String street;

    private String role;

    @Builder
    public UsersDto(Long id,String username,String realname,String password,String street,int age,String role){
        this.id=id;
        this.username=username;
        this.realname=realname;
        this.password=password;
        this.age=age;
        this.street=street;
        this.role=role;
    }

    public Users toEntity(){
        return Users.builder()
                .id(id)
                .username(username)
                .realname(realname)
                .password(password)
                .age(age)
                .street(street)
                .role(role)
                .build();
    }
}
