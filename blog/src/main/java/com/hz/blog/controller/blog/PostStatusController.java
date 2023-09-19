package com.hz.blog.controller.blog;


import com.hz.blog.entity.BlogStatus;
import com.hz.blog.response.ServerResponseEntity;
import com.hz.blog.service.PostStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("status")
@Api(tags = "文档状态接口")
public class PostStatusController {

    @Autowired
    private PostStatusService postStatusService;

    @GetMapping("getStatus")
    @ApiOperation(value ="获取对应的status列表",notes="获取所有的label列表")
    @ApiImplicitParam(name = "label", value = "需要请求的label名",required = true, paramType="query")
    public ServerResponseEntity getLabels(){
        List<BlogStatus> labels = postStatusService.getStatus();
        return ServerResponseEntity.success("success",labels);
    }

    @GetMapping("getOptionsStatus")
    @ApiOperation(value ="获取对应的status列表",notes="根据需求获取label列表")
    @ApiImplicitParam(name = "status", value = "需要请求的label名",required = true, paramType="query")
    public ServerResponseEntity getLabel(String status){
        List<BlogStatus> labels = postStatusService.getOptionsStatus(status);
        return ServerResponseEntity.success("success",labels);
    }

    @PutMapping(value = "updateStatus",consumes = "application/x-www-form-urlencoded")
    @ApiOperation(value ="修改对应的label",notes="修改对应的label")
    @ApiImplicitParam(name = "status", value = "需要请求的label名",required = true, paramType="query")
    public ServerResponseEntity updateLabel(BlogStatus status){
        int i = postStatusService.updateStatus(status);
        if (i>0){
            return ServerResponseEntity.success("success");
        }
        return ServerResponseEntity.success(100001,"fail");
    }

    @PostMapping(value = "addStatus")
    @ApiOperation(value ="添加Status",notes="添加Status")
    @ApiImplicitParam(name = "status", value = "需要请求的status名",required = true, paramType="query")
    public ServerResponseEntity addLabel(@RequestBody() @ApiParam(name = "body",value = "Status信息",required = true) @Validated BlogStatus status){
        status.setStatusId(UUID.randomUUID().toString().replaceAll("-", ""));
        int i = postStatusService.addStatus(status);
        if (i>0){
            return ServerResponseEntity.success("success");
        }
        return ServerResponseEntity.success(100001,"fail");
    }

    @DeleteMapping(value = "deleteStatus")
    @ApiOperation(value ="修改对应的status",notes="修改对应的label")
    @ApiImplicitParam(name = "status", value = "需要请求的status名",required = true, paramType="query")
    public ServerResponseEntity deleteLabel(String statusId){
        int i = postStatusService.deleteStatus(statusId);
        if (i>0){
            return ServerResponseEntity.success("success");
        }
        return ServerResponseEntity.success(100001,"fail");
    }


}
