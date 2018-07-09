package com.itheima.Service;

import com.itheima.pojo.Favorite;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;

/**
 * FileName: FavoriteService
 * Author:   李志
 * Date:     2018/7/2 21:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface FavoriteService {
    boolean isFavoriteByRidAndUserId(int rid, int uid);
    void addFavorite(int rid, User user);
    PageBean<Favorite> getPageBean(int curPage, int uid);
}
