package com.itheima.Mapper;

import com.itheima.pojo.Category;

import java.util.List;

/**
 * FileName: CategoryMapper
 * Author:   李志
 * Date:     2018/6/30 11:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface CategoryMapper {
    List<Category> findAllCategory();
}
