package fun.mizhuo.hrserver.mapper;

import fun.mizhuo.hrserver.model.Hr;
import fun.mizhuo.hrserver.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HrMapper {
    @Select("select * from hr where username = #{username}")
    Hr loadUserByUserName(@Param("username") String username);

    List<Role> getRolesByHrId(Integer id);
}