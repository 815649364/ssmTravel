package com.itheima.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.Mapper.RouteMapper;
import com.itheima.Service.IRouteService;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: RouteServiceImpl
 * Author:   李志
 * Date:     2018/6/30 16:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service("IRouteService")
public class RouteServiceImpl implements IRouteService {
    @Autowired
    private RouteMapper routeMapper;

    @Override
    public Map<String, List<Route>> routeCareChoose() {
        //定义集合
        Map<String,List<Route>> map = new HashMap<>();
        //人气路线
        List<Route> popularityRouteList = routeMapper.getPopularityRouteList();
        //最新路线
        List<Route> newestRouteList = routeMapper.getNewestRouteList();
        //主题路线
        List<Route> themeRouteList = routeMapper.getThemeRouteList();
        //将数据存入map集合中
        map.put("popularity",popularityRouteList);
        map.put("news",newestRouteList);
        map.put("themes",themeRouteList);
        return map;
    }




    @Override
    public PageBean getPageBean(int cid, int curPage, String rname) {
        //封装分页类数据
        PageBean<Route> pageBean = new PageBean<Route>();

        PageHelper.startPage(curPage,3);
        List<Route> routeList = routeMapper.findRouteListByCidAndRname(cid, rname);
        PageInfo<Route> routePageInfo = new PageInfo<>(routeList);

        //封装当前页
        pageBean.setCurPage(curPage);
        //封装页容量
        pageBean.setPageSize(3);
        //封装页内数据
        pageBean.setData(routePageInfo.getList());
        //封装总条目数
        pageBean.setCount((int) routePageInfo.getTotal());
        //返回分页类数据
        return pageBean;
    }

    @Override
    public Route findRouteByRid(String rid){
        return  routeMapper.findRouteByRid(rid);
    }
}
