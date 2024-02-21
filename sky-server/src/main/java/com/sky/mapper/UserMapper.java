package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * 插入用户对象
     *
     * @param user
     */
    void insert(User user);

    /**
     * 根据openid查询用户
     *
     * @param openid
     * @return
     */
    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);

    /**
     * 根据动态条件查询用户数量
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
