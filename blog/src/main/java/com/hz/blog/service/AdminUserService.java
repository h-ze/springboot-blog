package com.hz.blog.service;

import com.hz.blog.entity.PageRequest;
import com.hz.blog.entity.PageResult;

public interface AdminUserService {

    PageResult getUserList(PageRequest request,String status,String name);

    int changeUserTypeByUserId(String type, String cas_id);

    int changeUserTypeByName(String type,String name);

    int transferAdmin(String username);

    int switchUserType(String cas_id);
}
