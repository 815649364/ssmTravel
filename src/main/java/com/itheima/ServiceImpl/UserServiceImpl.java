package com.itheima.ServiceImpl;

import com.itheima.Exception.*;
import com.itheima.Mapper.UserMapper;
import com.itheima.Service.IUserService;
import com.itheima.Utils.MailUtil;
import com.itheima.Utils.Md5Util;
import com.itheima.Utils.UuidUtil;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName: UserServiceImpl
 * Author:   李志
 * Date:     2018/6/28 21:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean reguister(User user) throws Exception {
        //数据验证，用户名不能为空
        if(user.getUsername()==null || "".equals(user.getUsername())){
            //抛出自定义异常
            throw new UserNameNotNullException("用户名不能为空");
        }
        //判断用户名是否已被注册
        User dbUser = userMapper.queryUserByUserName(user.getUsername());
        if(dbUser!=null){
            //抛出自定义异常
            throw new UserExistsException("用户名已存在");
        }
        //封装业务字段-激活状态为未激活
        user.setStatus("N");
        //封装业务字段-激活码（唯一，uuid）
        user.setCode(UuidUtil.getUuid());
        //密码加密，使用md5加密，md5号称不可逆的加密算法
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));

        //注册用户添加用户
        int addUser = userMapper.addUser(user);
        //发送邮件
        MailUtil.sendMail(user.getEmail(),"<a href='http://localhost:8080/active?code="+user.getCode()+"'>用户激活</a>");
        return true;
    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public User login(String username, String password) throws Exception {
        //数据校验
        if(username==null || username.equalsIgnoreCase("")){
            throw new UserNameNotNullException("用户名不能为空");
        }
        //判断用户名是否存在
        User dbUser = userMapper.queryUserByUserName(username);
        if(dbUser==null){
            throw new UserNotExistsException("用户名不存在");
        }
        //判断密码是否正确
        if(!dbUser.getPassword().equals(password)){
            throw new PasswordErrorException("密码错误");
        }
        //用户是否已激活
        if(dbUser.getStatus().equals("N")){
            throw new UserNoActiveException("用户未激活");
        }

        return dbUser;
    }

    /**
     * 激活
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        int active = userMapper.active(code);
        return active>0;
    }
}
