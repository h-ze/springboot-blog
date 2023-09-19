package com.hz.blog.dao;



import com.hz.blog.entity.User;
import com.hz.blog.entity.UserRoles;
import com.hz.blog.entity.UserWithInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {
    int save(User user);
    int addUserRoles(UserRoles userRoles);
    int updateUserRoles(UserRoles userRoles);
    List<User> findAll();
    User getUser(@Param("email") String email);
    User getUserById(String userId);
    User findRolesByUsername(String username);
    int deleteUser(String userId);
    int deleteUserByOwner(String userId, String password);
    int updateUser(User user);

    UserWithInfo getUserWithInfo(@Param("userId") String userId,@Param("username") String username);


}
