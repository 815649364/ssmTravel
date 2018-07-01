package com.itheima.web;

import com.itheima.Service.IUserService;
import com.itheima.Utils.Md5Util;
import com.itheima.pojo.ResultInfo;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;


/**
 * FileName: UserController
 * Author:   李志
 * Date:     2018/6/29 14:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("userRegister")
    @ResponseBody
    public ResultInfo register(User user, HttpServletRequest request) throws Exception {
        //实例返回结果对象
        ResultInfo resultInfo = null;

        //获取用户输入的验证码
        String userCheck = request.getParameter("check");
        //获取服务器端生成的验证码
        String checkcodeServer = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //判断
        if(!checkcodeServer.equalsIgnoreCase(userCheck)){
            //验证失败
           resultInfo = new ResultInfo(false,null,"验证码错误!");
        }else {
            Boolean falg = userService.reguister(user);
            if(falg){
                resultInfo = new ResultInfo(true,null,null);
            }
        }
        return resultInfo;
    }

    /**
     * 用户登陆
     * @param request
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public ResultInfo login(HttpServletRequest request, @RequestParam("username")String username,@RequestParam("password")String password) throws Exception {
        ResultInfo resultInfo = null;

        password=Md5Util.encodeByMd5(password);

        User user = userService.login(username, password);

        //获取用户输入的验证码
        String userCheck = request.getParameter("check");
        //获取服务器端生成的验证码
        String checkcodeServer = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //判断
        if(!checkcodeServer.equalsIgnoreCase(userCheck)) {
            //验证失败
            resultInfo = new ResultInfo(false, null, "验证码错误!");
        }else if(user != null){
            request.getSession().setAttribute("user",user);
            resultInfo = new ResultInfo(true,null,null);
        }
        return resultInfo;
    }

    /**
     * 用户是否登陆
     * @param request
     * @return
     */
    @RequestMapping("getLoginUserData")
    @ResponseBody
    public ResultInfo getLoginUserData(HttpServletRequest request){

        ResultInfo resultInfo = null;
        //从session里面获取登录的用户数据
        User user1 = (User) request.getSession().getAttribute("user");
        if(user1==null){
            //说明用户没有登录
            resultInfo =  new ResultInfo(false,null,null);
        }else{
            //说明用户登录
            resultInfo = new ResultInfo(true,user1,null);
        }
        return resultInfo;
    }

    @RequestMapping("active")
    public ResultInfo active(HttpServletRequest request){
        ResultInfo resultInfo = null;
        try {
            //获取激活码
            String code = request.getParameter("code");
            //调用业务进行激活
            boolean flag =  userService.active(code);
            //返回结果
            if(flag) {
                //激活成功，跳转页面到login.html

                resultInfo = new ResultInfo(true,flag,"激活成功");
            }else {
                //激活失败,输出失败

                resultInfo = new ResultInfo(false,null,"激活失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
        return resultInfo;
    }
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:index.html";
    }
}
