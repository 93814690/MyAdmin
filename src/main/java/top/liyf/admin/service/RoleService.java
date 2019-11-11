package top.liyf.admin.service;

import org.springframework.stereotype.Service;
import top.liyf.admin.domain.Role;
import top.liyf.admin.mapper.RoleMapper;

import java.util.List;

@Service
public class RoleService {

    private final RoleMapper roleMapper;

    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public List<Role> getRolesByUid(Long userId) {
        return roleMapper.findByUid(userId);
    }
}
