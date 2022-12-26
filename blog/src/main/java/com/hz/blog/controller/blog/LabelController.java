package com.hz.blog.controller.blog;


import com.hz.blog.entity.BlogLabel;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.LabelService;
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
@RequestMapping("label")
@Api(tags = "label接口")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("getLabels")
    @ApiOperation(value ="获取对应的label列表",notes="获取所有的label列表")
    @ApiImplicitParam(name = "label", value = "需要请求的label名",required = true, paramType="query")
    public ResponseResult getLabels(){
        List<BlogLabel> labels = labelService.getLabels();
        return ResponseResult.successResult(100000,"success",labels);
    }

    @GetMapping("getLabel")
    @ApiOperation(value ="获取对应的label列表",notes="根据需求获取label列表")
    @ApiImplicitParam(name = "label", value = "需要请求的label名",required = true, paramType="query")
    public ResponseResult getLabel(String label){
        List<BlogLabel> labels = labelService.getLabels(label);
        return ResponseResult.successResult(100000,"success",labels);
    }


    @PutMapping(value = "updateLabel",consumes = "application/x-www-form-urlencoded")
    @ApiOperation(value ="修改对应的label",notes="修改对应的label")
    @ApiImplicitParam(name = "label", value = "需要请求的label名",required = true, paramType="query")
    public ResponseResult updateLabel(BlogLabel label){
        int i = labelService.updateLabel(label);
        if (i>0){
            return ResponseResult.successResult(100000,"success");
        }
        return ResponseResult.successResult(100001,"fail");
    }

    @PostMapping(value = "addLabel")
    @ApiOperation(value ="添加Label",notes="添加Label")
    @ApiImplicitParam(name = "label", value = "需要请求的label名",required = true, paramType="query")
    public ResponseResult addLabel(@RequestBody() @ApiParam(name = "body",value = "Label信息",required = true) @Validated BlogLabel label){
        label.setLabelId(UUID.randomUUID().toString().replaceAll("-", ""));
        int i = labelService.addLabel(label);
        if (i>0){
            return ResponseResult.successResult(100000,"success");
        }
        return ResponseResult.successResult(100001,"fail");
    }



    @DeleteMapping(value = "deleteLabel")
    @ApiOperation(value ="修改对应的label",notes="修改对应的label")
    @ApiImplicitParam(name = "label", value = "需要请求的label名",required = true, paramType="query")
    public ResponseResult deleteLabel(String labelId){
        int i = labelService.deleteLabel(labelId);
        if (i>0){
            return ResponseResult.successResult(100000,"success");
        }
        return ResponseResult.successResult(100001,"fail");
    }


}
