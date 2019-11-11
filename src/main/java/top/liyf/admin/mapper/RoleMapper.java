package top.liyf.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.liyf.admin.domain.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findByUid(Long userId);
}