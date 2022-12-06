package com.hz.blog.service;

import com.hz.blog.annotation.StartPage;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogService {

    int addLog(LogEntity logEntity);

    @StartPage
    List<LogEntity> getLogListByOther(PageResult pageResult,
                                      @Param("username") String username,
                                      @Param("email") String email,
                                      @Param("phone") String phone,
                                      @Param("ip")String ip,
                                      @Param("code")Integer code,
                                      @Param("operator")String operator
    );

    int deleteLog(String logId);
}
