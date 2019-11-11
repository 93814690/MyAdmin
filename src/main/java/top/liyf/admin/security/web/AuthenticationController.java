package top.liyf.admin.security.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liyf.admin.domain.AuthUser;
import top.liyf.admin.security.util.JwtTokenUtil;
import top.liyf.admin.util.EncryptUtils;

/**
 * @author liyf
 * Created in 2019-11-11
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationController(@Qualifier("myUserDetailsService") UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthUser user) {
        String password = EncryptUtils.encryptPassword(user.getPassword());
        System.out.println("password = " + password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if(!userDetails.getPassword().equals(password)){
            throw new AccountExpiredException("密码错误");
        }

        if(!userDetails.isEnabled()){
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }
}
