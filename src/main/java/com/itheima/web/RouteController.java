package com.itheima.web;

import com.itheima.Service.IRouteService;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ResultInfo;
import com.itheima.pojo.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 黑马精选
     *
     * @return
     */
    @PostMapping("routeCareChoose")
    @ResponseBody
    public ResultInfo routeCareChoose(){
        ResultInfo resultInfo = null;
        Map<String, List<Route>> map = routeService.routeCareChoose();
            resultInfo = new ResultInfo(true,map,null);
        return resultInfo;
    }

    /**
     * 处理线路详情页面查询线路数据异步
     * @param rid
     * @return
     */
    @PostMapping("findRouteByRid")
    @ResponseBody
        public ResultInfo findRouteByRid(@RequestParam("rid")String rid){
        try {
            // 调用业务逻辑根据rid获取旅游线路对象
            Route route = routeService.findRouteByRid(rid);
            // 实例结果
            return new ResultInfo(true, route, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultInfo(false);
        }

    /**
     * 获取国内游数据
     */
    @PostMapping("findRouteListByCid")
    @ResponseBody
    public ResultInfo findRouteListByCid(@RequestParam("cid")Integer cid,
                                         @RequestParam("rname")String rname,
                                         @RequestParam(value = "curPage",defaultValue = "1")Integer curPage){

        try {
            // 调用业务逻辑层获取国内游分页数据PageBean
            PageBean pageBean = routeService.getPageBean(cid, curPage, rname);
            // 返回正常数据
            return new ResultInfo(true, pageBean, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ResultInfo(false,null,"服务器正忙");
    }
}
