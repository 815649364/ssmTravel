package com.itheima.web;

import com.itheima.Service.FavoriteService;
import com.itheima.Service.IRouteService;
import com.itheima.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * FileName: FavoriteController
 * Author:   李志
 * Date:     2018/7/2 21:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private IRouteService routeService;

    @PostMapping("FavoriteByRid")
    @ResponseBody
    public ResultInfo FavoriteByRid(HttpServletRequest request, @RequestParam("rid")Integer rid){
        ResultInfo resultInfo = null;
        try {
            //判断用户是否登录
            User user =(User) request.getSession().getAttribute("user");
            if(user==null) {
                //用户没有登录,返回false
                //第一个参数,代表正常处理结果
                //第二个参数,代表没有收藏
                resultInfo = new ResultInfo(true,false,null);
            }else {

                //用户登录,根据rid和用户去数据判断是否有收藏记录
                boolean flag = favoriteService.isFavoriteByRidAndUserId(rid,user.getUid());
                if(flag) {
                    //收藏了,返回true
                    resultInfo = new ResultInfo(true,true,null);
                }else {
                    //没有收藏,返回false
                    resultInfo = new ResultInfo(true,false,null);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"服务器正忙..");
        }

       return  resultInfo;
    }

    @PostMapping("findFavoriteByPage")
    @ResponseBody
    public ResultInfo findFavoriteByPage(HttpServletRequest request) {

        ResultInfo resultInfo = null;
        try {
            //获取curPage
            //获取curPage
            int curPage = 1;
            String curPageStr = request.getParameter("curPage");
            if(curPageStr!=null && !"".equals(curPageStr)){
                curPage = Integer.parseInt(curPageStr);
            }
            //获取当前登录的用户
            User user = (User)request.getSession().getAttribute("user");

            //根据curPage和user调用业务获取收藏数据的PageBean
            PageBean<Favorite> pageBean =  favoriteService.getPageBean(curPage,user.getUid());

            //封装结果
            resultInfo = new ResultInfo(true,pageBean,null);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"服务器正忙..");
        }

       return  resultInfo;
    }

    @RequestMapping("addFavorite")
    @ResponseBody
    public ResultInfo addFavorite(HttpServletRequest request,@RequestParam("rid")int rid) {

        ResultInfo resultInfo = null;

        try {
            //判断用户是否登录
            User user =(User) request.getSession().getAttribute("user");
            if(user==null) {
                //没有登录返回0
                resultInfo = new ResultInfo(true,0,null);
            }else {
                //登录成功根据rid和uid实现添加收藏业务

                favoriteService.addFavorite (rid,user);

                //根据rid获取线路对象
                Route route = routeService.findRouteByRid(rid+"");

                //根据线路对象获取最新收藏数量
                int count = route.getCount();
                //返回
                resultInfo = new ResultInfo(true,count,null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"服务器正忙..");
        }
       return resultInfo;
    }
}

