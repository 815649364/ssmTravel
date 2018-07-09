package com.itheima.Service;

import com.itheima.pojo.PageBean;
import com.itheima.pojo.Route;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * FileName: IRouteService
 * Author:   李志
 * Date:     2018/6/30 16:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface IRouteService {
    //查询黑马精选
    Map<String,List<Route>> routeCareChoose();

    //获取分页类
    PageBean getPageBean(int cid, int curPage,String rname);
    //根据rid获取旅游线路
    Route findRouteByRid(String rid) ;

}
