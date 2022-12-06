package com.hz.blog.controller.blog;

import com.hz.blog.controller.BaseController;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log")
@Api(tags = "日志接口")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @GetMapping("logs")
    @ApiOperation(value = "获取登录日志列表",notes = "获取登录日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ResponseResult getPosts(@RequestParam("per_page")Integer per_page,
                                   @RequestParam("page")Integer page) {
        PageResult pageResult = logService.getLogListByOther(initPage(page,per_page),"","","","",null,"");
        return ResponseResult.successResult(100000,pageResult);

    }


}
