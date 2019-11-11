package top.liyf.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.liyf.admin.domain.Permission;

import java.util.List;

@Mapper
public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> findAllByRid(Integer rid);
}