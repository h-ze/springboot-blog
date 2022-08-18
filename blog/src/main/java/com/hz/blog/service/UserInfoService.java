package com.hz.blog.service;


import com.hz.blog.entity.UserInfo;

public interface UserInfoService {

    int insertUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(String userId);

    int updateUserInfo(UserInfo userInfo);

    int deleteUserInfo(String userId);

}
