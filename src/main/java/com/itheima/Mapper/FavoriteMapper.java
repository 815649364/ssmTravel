package com.itheima.Mapper;

import com.itheima.pojo.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: FavoriteMapper
 * Author:   李志
 * Date:     2018/7/3 16:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface FavoriteMapper {
    List<Favorite> findFavoriteListByPage(@Param("uid") int uid);

    Favorite findFavoriteByRidAndUserId(@Param("rid") int rid, @Param("uid") int uid);

    int addFavorite(Favorite favorite);


}
