package com.hz.blog.controller.blog;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.blog.entity.PageRequest;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.Post;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.PostService;
import com.hz.blog.utils.PageUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Api(tags = "博客接口")
@RestController
@RequestMapping("postController")
public class PostController extends BaseController{

    @Autowired
    private PostService postService;

    @ApiOperation(value ="添加博客",notes="添加博客")
    @PostMapping("addPost")
    public ResponseResult addPost(@RequestBody() @ApiParam(name = "body",value = "标签信息",required = true) Post post){
        post.setCreated(new Date());
        logger.info("date:{}",post.getCreated());
        int i = postService.addPost(post);
        if (i>0){
            return ResponseResult.successResult(100000,"插入成功",post);
        }
        return ResponseResult.successResult(100001,"插入失败",post);
    }

    @ApiOperation(value ="修改博客",notes="修改博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "博客id",paramType = "query",dataType = "int",required = true),
    })
    @PutMapping("updatePost")
    public ResponseResult updatePost(BigInteger id,@ApiParam(value = "博客标题") String title){
        Post postById = postService.getPostById(id);
        if (postById ==null){
            return ResponseResult.successResult(100003,"当前内容不存在",id);
        }

        postById.setTitle(title);

        int i = postService.updatePost(postById);

        if (i>0){
            return ResponseResult.successResult(100000,"修改成功",postById);
        }
        return ResponseResult.successResult(100001,"修改失败",postById);
    }





    @ApiOperation(value ="删除博客",notes="删除博客")
    @DeleteMapping("deletePost")
    public ResponseResult deletePost(BigInteger id){
        Post post = postService.getPostById(id);

        if (post==null){
            return ResponseResult.successResult(100003,"当前内容不存在",post);
        }
        int i = postService.deletePost(id);
        if (i>0){
            return ResponseResult.successResult(100000,"删除成功",post);
        }
        return ResponseResult.successResult(100001,"删除失败",post);
    }


    @ApiOperation(value = "获取博客列表",notes = "获取博客列表")
    @GetMapping("getPosts")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ResponseResult getPosts(@RequestParam("per_page")Integer per_page,
                                        @RequestParam("page")Integer page) {
        startPage(page,per_page);
        List<Post> post = postService.getPost();
        PageInfo<?> pageList = getPageList(post);
        //PageResult docsPage = docService.getDocsPage(pageRequest,userId);
        PageResult postsPage = getPageResult(pageList);
        return ResponseResult.successResult(100000,postsPage);

    }

}
