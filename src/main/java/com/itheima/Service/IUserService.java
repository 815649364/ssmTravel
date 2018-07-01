package com.itheima.Service;

import com.itheima.Exception.UserExistsException;
import com.itheima.Exception.UserNameNotNullException;
import com.itheima.Mapper.UserMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: IUserService
 * Author:   李志
 * Date:     2018/6/28 21:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface IUserService {
     //注册
     Boolean reguister(User user) throws Exception;
     //登陆
     User login(@Param("username") String username,@Param("password") String password) throws Exception;
     //激活
     boolean active(@Param("code") String code);
}
