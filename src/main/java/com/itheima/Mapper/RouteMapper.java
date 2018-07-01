package com.itheima.Mapper;

import com.itheima.pojo.Route;

import java.util.List;

/**
 * FileName: IRouteMapper
 * Author:   李志
 * Date:     2018/6/30 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface RouteMapper {
    /**
     * 黑马精选
     * 人气旅游路线
     * @return
     */
    public List<Route> getPopularityRouteList();

    //最新线路旅游
    public List<Route> getNewestRouteList();

    //最新主题旅游
    public List<Route> getThemeRouteList();
}
