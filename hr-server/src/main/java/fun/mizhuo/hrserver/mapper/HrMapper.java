package fun.mizhuo.hrserver.mapper;

import fun.mizhuo.hrserver.model.Hr;
import fun.mizhuo.hrserver.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    @Select("select * from hr where username = #{username}")
    Hr loadUserByUserName(@Param("username") String username);

    List<Role> getRolesByHrId(Integer id);
}