package com.hz.blog.controller;

import com.hz.blog.annotation.AccessLimit;
import com.hz.blog.annotation.RepeatSubmit;
import com.hz.blog.response.ServerResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试防刷注解接口")
@RequestMapping("Fangshua")
public class FangshuaController {

    @ApiOperation(value ="防刷测试",notes="测试防刷")
    @AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
    @GetMapping("/fangshua")
    public ServerResponseEntity fangshua() {

        //ResponseResult<Object> objectResponseResult = new ResponseResult<>();
        //return new ResponseResult<>(100000, "请求成功", "请求成功");
        return ServerResponseEntity.success("请求成功");
    }


    @ApiOperation(value ="不防刷",notes="测试不防刷")
    @AccessLimit(seconds = 5, maxCount = 5, needLogin = false)
    @GetMapping("/dontfangshua")
    public ServerResponseEntity dontfangshua() {
        //return new ResponseResult<>(100000, "请求成功", "请求成功");
        return ServerResponseEntity.success("请求成功");

    }


    @ApiOperation(value ="参数相同多次提交",notes="测试参数相同多次提交")
    @RepeatSubmit()
    @GetMapping("/paramSame")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docId",value = "测试Id",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "encryptConfig",value = "加密策略",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "docName",value = "文档名称",paramType = "query",dataType = "String")
    })
    public ServerResponseEntity paramSame(@RequestParam("docId") String docId,
                                    @RequestParam("encryptConfig")String encryptConfig,
                                    @RequestParam("docName")String docName) {
        //return new ResponseResult<>(100000, "请求成功", "请求成功");
        return ServerResponseEntity.success("请求成功");

    }
}
