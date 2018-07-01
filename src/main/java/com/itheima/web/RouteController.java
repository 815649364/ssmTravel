package com.itheima.web;

import com.itheima.Service.IRouteService;
import com.itheima.pojo.ResultInfo;
import com.itheima.pojo.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * FileName: RouteController
 * Author:   李志
 * Date:     2018/7/1 9:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Controller
public class RouteController {
    @Autowired
    private IRouteService routeService;

    @RequestMapping("routeCareChoose")
    @ResponseBody
    public ResultInfo routeCareChoose(HttpServletRequest request){
        ResultInfo resultInfo = null;
        Map<String, List<Route>> map = routeService.routeCareChoose();
        if(map!=null){
            resultInfo = new ResultInfo(true,map,null);
        }
        return resultInfo;
    }
}
