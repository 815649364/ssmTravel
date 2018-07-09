package com.itheima.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.Mapper.FavoriteMapper;
import com.itheima.Mapper.RouteMapper;
import com.itheima.Service.FavoriteService;
import com.itheima.pojo.Favorite;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Route;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * FileName: FavoriteServiceImpl
 * Author:   李志
 * Date:     2018/7/3 17:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private RouteMapper routeMapper;

    @Override
    public boolean isFavoriteByRidAndUserId(int rid, int uid) {
        Favorite favoriteByRidAndUserId = favoriteMapper.findFavoriteByRidAndUserId(rid, uid);
        return favoriteByRidAndUserId!=null;
    }

    @Override
    public void addFavorite(int rid, User user) {
        //实例Favorite
        Favorite favorite = new Favorite();
        Route route = new Route();
        route.setRid(rid);
        favorite.setRoute(route);
        favorite.setUser(user);
        favorite.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int row = favoriteMapper.addFavorite(favorite);
        //更新当前线路的收藏数量+1
        routeMapper.updateCountByRid(rid);
    }

    @Override
    public PageBean<Favorite> getPageBean(int curPage, int uid) {
        //实例pagebean
        PageBean<Favorite> pageBean = new PageBean<>();

        PageHelper.startPage(curPage,4);
        //获取当前数据列表
        List<Favorite> favoriteListByPage = favoriteMapper.findFavoriteListByPage(uid);

        PageInfo<Favorite> favoritePageInfo = new PageInfo<Favorite>(favoriteListByPage);
        //封装当前页面
        pageBean.setCurPage(curPage);
        pageBean.setCount((int) favoritePageInfo.getTotal());
        pageBean.setPageSize(4);
        pageBean.setData(favoriteListByPage);
        return pageBean;
    }
}
