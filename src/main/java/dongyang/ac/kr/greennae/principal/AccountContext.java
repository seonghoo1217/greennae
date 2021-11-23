package dongyang.ac.kr.greennae.principal;

import dongyang.ac.kr.greennae.domain.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AccountContext implements UserDetails {


    private Long id;
    private String username;
    private String password;
    private String role;

    public AccountContext(Users users) {
        this.id=users.getId();
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.role = users.getRole();
    }

    public AccountContext() {

    }

    public Users returnUser(){

        Users buildUser = Users.builder()
                .id(id)
                .username(username)
                .password(password)
                .role(role)
                .build();

        return buildUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    public Long getId(){return this.id;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //계정비밀번호만료여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //계정활성화여부
    public boolean isEnabled() {
        return true;
    }
}
