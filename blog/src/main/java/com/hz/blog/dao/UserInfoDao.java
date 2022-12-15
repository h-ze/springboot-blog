package com.hz.blog.dao;


import com.hz.blog.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserInfoDao {

    int insertUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(String userId);

    int updateUserInfo(UserInfo userInfo);

    int deleteUserInfo(String userId);
}
