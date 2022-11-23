package com.hz.blog.service;



import com.hz.blog.entity.User;
import com.hz.blog.entity.UserRoles;
import com.hz.blog.entity.UserWithInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int save(User user, UserRoles userRoles);

    int addUserRoles(UserRoles userRoles);

    List<User> findAll();

    User getUser(String username);

    User getUserWithRoles(String username);

    User getUserByUserId(String userId);

    User findRolesByUsername(String username);

    int deleteUser(String userId, String password);

    int deleteUser(String userId);

    int updateUserPassword(User user);

    String createQrImg();

    UserWithInfo getUserWithInfo(@Param("userId") String userId, @Param("username")String username);

    int changeUserRoles(User user);
}
