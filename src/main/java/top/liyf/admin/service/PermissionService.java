package top.liyf.admin.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import top.liyf.admin.domain.Permission;
import top.liyf.admin.domain.Role;
import top.liyf.admin.mapper.PermissionMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    private final RoleService roleService;

    private final PermissionMapper permissionMapper;

    public PermissionService(RoleService roleService, PermissionMapper permissionMapper) {
        this.roleService = roleService;
        this.permissionMapper = permissionMapper;
    }

    public Collection<GrantedAuthority> getGrantedAuthorityByUid(Long userId) {
        List<Role> rolesByUid = roleService.getRolesByUid(userId);
        Set<String> permissions = new HashSet<>();
        for (Role role : rolesByUid) {
            permissions.add(role.getPermission());
            List<Permission> permissionList = getPermissionByRid(role.getRid());
            for (Permission permission : permissionList) {
                permissions.add(permission.getPermission());
            }
        }
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<Permission> getPermissionByRid(Integer rid) {
        return permissionMapper.findAllByRid(rid);
    }
}
