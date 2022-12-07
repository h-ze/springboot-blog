package com.hz.blog.controller.blog;

import com.hz.blog.controller.BaseController;
import com.hz.blog.entity.LogEntity;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.LogService;
import com.hz.blog.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.hz.blog.constant.Constant.LOG_LOGIN;
import static com.hz.blog.constant.Constant.LOG_POST;

@RestController
@RequestMapping("log")
@Api(tags = "日志接口")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("loginLogs")
    @ApiOperation(value = "获取登录日志列表",notes = "获取登录日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ResponseResult getLoginLogs(@RequestParam("per_page")Integer per_page,
                                   @RequestParam("page")Integer page) {

        String userId="";
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNoneEmpty(principal)) {
            Claims claims = jwtUtil.parseJWT(principal);
            userId = (String)claims.get("userId");
        }

        PageResult pageResult = logService.getLogListByOther(initPage(page,per_page),"","","","",null,"",userId,Integer.valueOf(LOG_LOGIN));
        return ResponseResult.successResult(100000,pageResult);

    }


    @GetMapping("postLogs")
    @ApiOperation(value = "获取Post日志列表",notes = "获取Post日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ResponseResult getPostLogs(@RequestParam("per_page")Integer per_page,
                                   @RequestParam("page")Integer page) {

        String userId="";
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNoneEmpty(principal)) {
            Claims claims = jwtUtil.parseJWT(principal);
            userId = (String)claims.get("userId");
        }

        PageResult pageResult = logService.getLogListByOther(initPage(page,per_page),"","","","",null,"",userId,Integer.valueOf(LOG_POST));
        return ResponseResult.successResult(100000,pageResult);

    }


}
