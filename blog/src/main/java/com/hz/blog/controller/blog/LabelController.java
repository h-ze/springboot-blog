package com.hz.blog.controller.blog;


import com.hz.blog.entity.BlogLabel;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
