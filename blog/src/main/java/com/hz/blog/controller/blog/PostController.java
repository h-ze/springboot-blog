package com.hz.blog.controller.blog;


import com.hz.blog.entity.Post;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;


@Api(tags = "博客接口")
@RestController
@RequestMapping("postController")
public class PostController extends BaseController{

    @Autowired
    private PostService postService;

    @ApiOperation(value ="添加博客",notes="添加博客")
    @PostMapping("addPost")
    public ResponseResult addPost(@RequestBody() @ApiParam(name = "body",value = "标签信息",required = true) Post post){
        int i = postService.addPost(post);
        if (i>0){
            return ResponseResult.successResult(100000,"插入成功",post);
        }
        return ResponseResult.successResult(100001,"插入失败",post);
    }



    @ApiOperation(value ="删除博客",notes="删除博客")
    @DeleteMapping("deletePost")
    public ResponseResult deletePost(BigInteger id){
        Post post = postService.getPostById(id);

        if (post==null){
            return ResponseResult.successResult(100003,"内容不存在",post);
        }
        int i = postService.deletePost(id);
        if (i>0){
            return ResponseResult.successResult(100000,"删除成功",post);
        }
        return ResponseResult.successResult(100001,"删除失败",post);
    }

}
