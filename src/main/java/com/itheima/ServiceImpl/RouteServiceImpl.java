package com.itheima.ServiceImpl;

import com.itheima.Mapper.RouteMapper;
import com.itheima.Service.IRouteService;
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
        map.put("popularityRouteList",popularityRouteList);
        map.put("newestRouteList",newestRouteList);
        map.put("themeRouteList",themeRouteList);
        return map;
    }
}
