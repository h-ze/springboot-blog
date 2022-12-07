package com.hz.blog.task;

import com.hz.blog.entity.LogEntity;
import com.hz.blog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 添加登录日志
 */
@Component
//@EventQueue(name = LocalQueueConstants.SYSTEM_MESSAGE)
@Slf4j
public class LogListener implements TaskListener{

    private static final Logger logger = LoggerFactory.getLogger(LogListener.class);

    @Autowired
    private LogService logService;

    @Override
    public void onMessage(TaskParam taskParam) {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        LogEntity log = (LogEntity) taskParam.get("log");

        logger.info("异步任务------->");
        logService.addLog(log);
        logger.info("LogListener------->");
    }
}
