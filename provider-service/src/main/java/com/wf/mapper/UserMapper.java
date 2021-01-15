package com.wf.mapper;

import com.wf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

//添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
@Mapper
public interface UserMapper {
    // 根据id查询用户信息
    @Select("select * from tb_user where id = #{id}")
    User finById(@RequestParam("id") Integer id);
}
