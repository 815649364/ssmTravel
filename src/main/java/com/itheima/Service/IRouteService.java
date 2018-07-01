package com.itheima.Service;

import com.itheima.pojo.Route;

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
    Map<String,List<Route>> routeCareChoose();
}
