package com.hz.blog.service.impl;

import com.hz.blog.entity.PageRequest;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.VisiableUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.blog.dao.AdminUserDao;
import com.hz.blog.service.AdminUserService;
import com.hz.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminUser")
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public PageResult getUserList(PageRequest pageRequest,String status,String name) {
        return PageUtils.getPageResult(getPageInfo(pageRequest,status,name));
    }

    @Override
    public int changeUserTypeByUserId(String type, String cas_id) {
        return adminUserDao.changeUserTypeByUserId(type,cas_id);
    }

    @Override
    public int changeUserTypeByName(String type, String name) {
        return adminUserDao.changeUserTypeByName(type,name);
    }

    @Override
    public int transferAdmin(String username) {

        return adminUserDao.transferAdmin(username);
    }

    @Override
    public int switchUserType(String cas_id) {
        return 0;
    }

    private PageInfo<VisiableUser> getPageInfo(PageRequest pageRequest, String status,String name) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<VisiableUser> userList = adminUserDao.getUserList(status,name);
        return new PageInfo<>(userList);
    }
}
