package com.itheima.ServiceImpl;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.Mapper.CategoryMapper;
import com.itheima.Service.ICategoryService;
import com.itheima.Utils.JedisUtil;
import com.itheima.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: CategoryServiceImpl
 * Author:   李志
 * Date:     2018/6/30 11:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service("ICategoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAllCategory() throws IOException {
        //定义返回的json
        String jsonData = null;
        //如果jedis访问不了，会发生异常
        Jedis jedis = null;
        //
        ObjectMapper mapper = new ObjectMapper();
        try {
            //1.从redis缓存数据库取
            //1.1获取jedis连接对象
            jedis = JedisUtil.getJedis();
            //1.2获取jedis里面的数据
            jsonData = jedis.get("categoryList");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断有效性，是否空
        if(jsonData==null || "".equals(jsonData)) {
            //为空，去数据库获取泛型集合分类数据
            List<Category> categoryList = categoryMapper.findAllCategory();
            System.out.println(categoryList);
            //将集合对象转换json
            jsonData = new ObjectMapper().writeValueAsString(categoryList);
            try {
                //将json写入redis缓存数据库中
                jedis.set("categoryList ",jsonData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Category.class);
        List<Category> categories = mapper.readValue(jsonData, javaType);
        return categories;
    }
}
