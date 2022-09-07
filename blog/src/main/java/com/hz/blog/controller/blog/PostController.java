package com.hz.blog.controller.blog;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.blog.entity.PageRequest;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.Post;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.PostService;
import com.hz.blog.utils.EntityConvertDtoAndVOUtils;
import com.hz.blog.utils.PageUtils;
import com.hz.blog.vo.PostVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Api(tags = "博客接口")
@RestController
@RequestMapping("postController")
public class PostController extends BaseController{

    @Autowired
    private PostService postService;

    @PostMapping("addPost")
    @ApiOperation(value ="添加博客",notes="添加博客")
    public ResponseResult addPost(@RequestBody() @ApiParam(name = "body",value = "标签信息",required = true) PostVo postVo){
        int i = postService.addPost(postVo);
        if (i>0){
            return ResponseResult.successResult(100000,"插入成功",postVo);
        }
        return ResponseResult.successResult(100001,"插入失败",postVo);
    }

    @PutMapping("updatePost")
    @ApiOperation(value ="修改博客",notes="修改博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "博客id",paramType = "query",dataType = "int",required = true),
    })
    public ResponseResult updatePost(BigInteger id,@ApiParam(value = "博客标题") String title){
        PageResult<Post> postListByOther = postService.getPostListByOther(initPage(1, 1), null, id, null, null);
        if (postListByOther.getTotalSize() ==0 ){
            return ResponseResult.successResult(100003,"当前内容不存在",id);
        }
        Post post = postListByOther.getData().get(0);
        post.setTitle(title);
        int i = postService.updatePost(post);
        if (i>0){
            return ResponseResult.successResult(100000,"修改成功",post);
        }
        return ResponseResult.successResult(100001,"修改失败",post);
    }

    @DeleteMapping("deletePost")
    @ApiOperation(value ="删除博客",notes="删除博客")
    public ResponseResult deletePost(BigInteger id){
        PageResult<Post> postListByOther = postService.getPostListByOther(initPage(1, 1), null, id, null, null);
        if (postListByOther.getTotalSize()==0){
            return ResponseResult.successResult(100003,"当前内容不存在",postListByOther.getData().get(0));
        }
        int i = postService.deletePost(id);
        if (i>0){
            return ResponseResult.successResult(100000,"删除成功",postListByOther.getData().get(0));
        }
        return ResponseResult.successResult(100001,"删除失败",postListByOther.getData().get(0));
    }

    @GetMapping("getPosts")
    @ApiOperation(value = "获取博客列表",notes = "获取博客列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ResponseResult getPosts(@RequestParam("per_page")Integer per_page,
                                        @RequestParam("page")Integer page) {
        //startPage(page,per_page);
        PageResult pageResult = postService.getPost(initPage(page,per_page));
        //PageInfo<?> pageList = getPageList(post);
        //PageResult postsPage = getPageResult(pageList);
        return ResponseResult.successResult(100000,pageResult);

    }

    @GetMapping("getPostListByOther")
    @ApiOperation(value = "根据需求获取博客列表",notes = "根据需求获取博客列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ResponseResult getPostListByOther(@RequestParam("per_page")Integer per_page,
                                             @RequestParam("page")Integer page, @NotNull(message = "参数不能为空") BigInteger authorId, BigInteger postId, Integer status, String title) {
        //startPage(page,per_page);
        PageResult<Post> postListByOther = postService.getPostListByOther(initPage(page, per_page), authorId, postId, status, title);
        //PageInfo<?> pageList = getPageList(post);
        //PageResult postsPage = getPageResult(pageList);
        return ResponseResult.successResult(100000,postListByOther);

    }

}
