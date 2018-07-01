package com.itheima.Service;


import com.itheima.pojo.Category;

import java.io.IOException;
import java.util.List;

/**
 * FileName: ICategoryService
 * Author:   李志
 * Date:     2018/6/30 11:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface ICategoryService {
    List<Category> findAllCategory() throws IOException;
}
