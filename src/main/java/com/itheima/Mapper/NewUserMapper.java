package com.itheima.Mapper;

import com.github.abel533.mapper.Mapper;
import com.itheima.pojo.User;

import javax.persistence.Table;

/**
 * FileName: NewUserMapper
 * Author:   李志
 * Date:     2018/7/1 21:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Table(name = "tab_user")
public interface NewUserMapper extends Mapper<User> {
}
