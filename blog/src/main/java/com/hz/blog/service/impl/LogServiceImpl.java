package com.hz.blog.service.impl;

import com.hz.blog.dao.LogDao;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.Post;
import com.hz.blog.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public int addLog(LogEntity logEntity) {
        return logDao.addLog(logEntity);
    }

    @Override
    public PageResult<Post> getLogListByOther(PageResult pageResult, String username, String email, String phone, String ip, Integer code, String operator,String userId,Integer type) {
        List<LogEntity> logListByOther =new ArrayList<>();
        if (StringUtils.isNotEmpty(userId)){
            logListByOther = logDao.getLogListByOther(pageResult, username, email, phone, ip, code, operator,userId,type);
        }
        return pageResult.getPageFilter(pageResult,logListByOther);
    }

    @Override
    public int deleteLog(String logId) {
        return 0;
    }
}
