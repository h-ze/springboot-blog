package com.hz.blog.service.impl;

import com.hz.blog.dao.LogDao;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.PageResult;
import com.hz.blog.service.LogService;
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
    public List<LogEntity> getLogListByOther(PageResult pageResult, String username, String email, String phone, String ip, Integer code, String operator) {
        return new ArrayList<>();
    }

    @Override
    public int deleteLog(String logId) {
        return 0;
    }
}
