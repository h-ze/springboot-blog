package com.hz.blog.service;

import com.hz.blog.annotation.StartPage;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogService {

    int addLog(LogEntity logEntity);

    @StartPage
    PageResult<Post> getLogListByOther(PageResult pageResult,
                                       String username,
                                       String email,
                                       String phone,
                                       String ip,
                                       Integer code,
                                       String operator,
                                       String userId,
                                       Integer type
    );

    int deleteLog(String logId);
}
