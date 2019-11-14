package top.liyf.admin.security.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liyf.admin.domain.AuthUser;
import top.liyf.admin.exception.BusinessException;
import top.liyf.admin.result.ResultBean;
import top.liyf.admin.result.ResultCode;
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

    public AuthenticationController(@Qualifier("myUserDetailsService") UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResultBean<String> login(@RequestBody AuthUser user) {
        String password = EncryptUtils.encryptPassword(user.getPassword());
        System.out.println("password = " + password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if(!userDetails.getPassword().equals(password)){
            throw new BusinessException(ResultCode.WRONG_PASSWORD);
        }

        if(!userDetails.isEnabled()){
            throw new BusinessException(ResultCode.DEACTIVATED_ACCOUNT);
        }
        String token = jwtTokenUtil.generateToken(userDetails);

        return new ResultBean<>(token);
    }
}
