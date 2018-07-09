package com.itheima.Mapper;

import com.itheima.pojo.Route;
import org.apache.ibatis.annotations.Param;

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
     List<Route> getPopularityRouteList();

    //最新线路旅游
     List<Route> getNewestRouteList();

    //最新主题旅游
     List<Route> getThemeRouteList();

    //分页获取国内游线路列表数据
     List<Route> findRouteListByPage(@Param("cid") int cid, @Param("curPage") int curPage,@Param("pageSize") int pageSize,@Param("rname") String rname);
    //获取指定分类的总记录数
    int getCountByCid(@Param("cid") int cid,@Param("rname") String rname);
    //获取分页类
    List<Route> findRouteListByCidAndRname(@Param("cid") int cid,@Param("rname") String rname);

    Route findRouteByRid(@Param("rid") String rid);

    void updateCountByRid(@Param("rid") int rid);
}
