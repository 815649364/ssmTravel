package com.itheima.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.Service.ICategoryService;
import com.itheima.pojo.Category;
import com.itheima.pojo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * FileName: CategoryController
 * Author:   李志
 * Date:     2018/6/30 11:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Controller
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @RequestMapping("category")
    @ResponseBody
    public ResultInfo findAllCategory(HttpServletRequest request) throws IOException {
        //调用业务逻辑层获取分类列表的json数据
        List<Category> jsonData = null;
        //定义json转换对象
        ObjectMapper objectMapper = new ObjectMapper();
        //定义返回数据对象
        ResultInfo resultInfo = new ResultInfo(false, null, "服务器正忙。。");
        try {
            jsonData = categoryService.findAllCategory();
            if (jsonData != null) {
                resultInfo = new ResultInfo(true, jsonData, null);
            }
            //由于这里已经是json字符串所以直接输出返回
        } catch (Exception e) {
            e.printStackTrace();


        }
        return resultInfo;
    }
}
