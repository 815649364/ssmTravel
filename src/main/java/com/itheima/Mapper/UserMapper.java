package com.itheima.Mapper;

import com.itheima.pojo.Category;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;


/**
 * FileName: UserMapper
 * Author:   李志
 * Date:     2018/6/28 20:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface UserMapper {
    //根据用户名查询用户
    User queryUserByUserName(@Param("username") String username);
    //保存用户
    int addUser(User user);

    //激活
    int active(@Param("code") String code);


}
