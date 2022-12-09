package com.hz.blog.controller.blog;

import com.hz.blog.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "test1Controller接口")
@RestController
@RequestMapping("test1Controller")
@Slf4j
public class Test1Controller extends BaseController {

    @ApiOperation(value ="测试@InitBinder",notes="测试@InitBinder")
    @GetMapping("getTest")
    public String getTest(@RequestParam String param){
        log.info("param:{}",param);
        System.out.println("1");
        return param;
    }

}
