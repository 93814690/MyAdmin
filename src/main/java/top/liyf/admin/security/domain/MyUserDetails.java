package top.liyf.admin.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.liyf.admin.domain.User;

import java.util.Collection;

@Getter
public class MyUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String status;

    @Setter
    private Collection<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.id = user.getUid();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
    }

    /**
     * 用户账号没有过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号没有被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码没有过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户可用
     */
    @Override
    public boolean isEnabled() {
        return "1".equals(status);
    }
}
