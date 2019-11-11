package top.liyf.admin.service;

import org.springframework.stereotype.Service;
import top.liyf.admin.domain.User;
import top.liyf.admin.mapper.UserMapper;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findByName(String username) {
        return userMapper.findByUsername(username);
    }
}
