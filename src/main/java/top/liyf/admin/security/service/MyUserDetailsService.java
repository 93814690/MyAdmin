package top.liyf.admin.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import top.liyf.admin.domain.User;
import top.liyf.admin.exception.BusinessException;
import top.liyf.admin.result.ResultCode;
import top.liyf.admin.security.domain.MyUserDetails;
import top.liyf.admin.service.PermissionService;
import top.liyf.admin.service.UserService;

import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final PermissionService permissionService;

    public MyUserDetailsService(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        MyUserDetails userDetails = new MyUserDetails(user);
        Collection<GrantedAuthority> grantedAuthority = permissionService.getGrantedAuthorityByUid(user.getUid());
        userDetails.setAuthorities(grantedAuthority);

        return userDetails;
    }
}
