package com.hz.blog.controller.blog;


import com.hz.blog.annotation.LogOperator;
import com.hz.blog.constant.Constant;
import com.hz.blog.controller.BaseController;
import com.hz.blog.entity.*;
import com.hz.blog.exception.RRException;
import com.hz.blog.response.ServerResponseEntity;
import com.hz.blog.service.PostService;
import com.hz.blog.utils.EntityConvertDtoAndVOUtils;
import com.hz.blog.utils.JWTUtil;
import com.hz.blog.utils.ShiroUtils;
import com.hz.blog.vo.PostVo;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.hz.blog.constant.Constant.*;
import static com.hz.blog.utils.ConvertUtils.convertString;


@Api(tags = "博客接口")
@RestController
@RequestMapping("postController")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private ShiroUtils shiroUtils;

    @Autowired
    private JWTUtil jwtUtil;


    @LogOperator(value = "新建博客",type = LOG_POST)
    @PostMapping("addPost")
    @ApiOperation(value ="添加博客",notes="添加博客")
    //@RequiresRoles(value = {TYPE_ADMIN,TYPE_USER,TYPE_PRODUCT,TYPE_ASSISTANT,TYPE_SUPERADMIN})
    public ServerResponseEntity addPost(@RequestBody() @ApiParam(name = "body",value = "标签信息",required = true) @Validated PostVo postVo){


        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        Claims claims = jwtUtil.parseJWT(principal);
        String userId = (String)claims.get("userId");
        String fullName = (String)claims.get("fullName");
        // String userId = shiroUtils.getUserId();
        logger.info("userId:{}",userId);

        Post post = EntityConvertDtoAndVOUtils.convertBean(postVo, Post.class);
        post.setAuthorId(Long.valueOf(userId));
        post.setAuthorName(fullName);

        int i = postService.addPost(post);
        if (i>0){
            return ServerResponseEntity.success("插入成功",postVo);
        }
        return ServerResponseEntity.success(100001,"插入失败",postVo);
    }

    //@LogOperator(value = "定时发送博客",type = LOG_POST)
    @PostMapping("addPostOnTiming")
    @ApiOperation(value ="添加博客",notes="添加博客")
    //@RequiresRoles(value = {TYPE_ADMIN,TYPE_USER,TYPE_PRODUCT,TYPE_ASSISTANT,TYPE_SUPERADMIN})
    public ServerResponseEntity addPostOnTiming(@RequestBody() @ApiParam(name = "body",value = "标签信息",required = true) @Validated PostVo postVo){


        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        Claims claims = jwtUtil.parseJWT(principal);
        String userId = (String)claims.get("userId");
        String fullName = (String)claims.get("fullName");
        // String userId = shiroUtils.getUserId();
        logger.info("userId:{}",userId);

        Post post = EntityConvertDtoAndVOUtils.convertBean(postVo, Post.class);
        post.setAuthorId(Long.valueOf(userId));
        post.setAuthorName(fullName);

        String delayTime ="10000";
        int i = postService.addPostOnTiming(post,delayTime);
        logger.info("定时发布结果:{}",i);
        if (i>0){
            return ServerResponseEntity.success("插入成功",postVo);
        }
        return ServerResponseEntity.success(100001,"插入失败",postVo);
    }





    @LogOperator(value = "修改博客",type = LOG_POST)
    @PutMapping(value = "updatePost",consumes = "application/x-www-form-urlencoded")
    @ApiOperation(value ="修改博客",notes="修改博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId",value = "文章id",paramType = "form",dataType = "Long",required = true),
            @ApiImplicitParam(name = "title",value = "文章标题",paramType = "form",dataType = "String"),
            @ApiImplicitParam(name = "tags",value = "文章标签",paramType = "form",dataType = "String"),
            @ApiImplicitParam(name = "summary",value = "文章概要",paramType = "form",dataType = "String"),
            @ApiImplicitParam(name = "status",value = "文章状态",paramType = "form",dataType = "int")

    })
    //@RequiresRoles(value = {TYPE_ADMIN,TYPE_USER,TYPE_PRODUCT,TYPE_ASSISTANT,TYPE_SUPERADMIN})
    public ServerResponseEntity updatePost(Long postId,String title,String tags,String summary,Integer status){
        PageResult<Post> postListByOther = postService.getPostListByOther(initPage(1, 1), null,null, postId, null, null,null,null);
        if (postListByOther.getTotalSize() ==0 ){
            return ServerResponseEntity.success(100003,"当前内容不存在",postId);
        }
        Post post = postListByOther.getData().get(0);
        logger.info("post1:{}",post);
        Long authorId = post.getAuthorId();

        //String principal = (String) SecurityUtils.getSubject().getPrincipal();
        //Claims claims = jwtUtil.parseJWT(principal);
        //String userId = (String)claims.get("userId");
        //String userId = shiroUtils.getUserId();
        //logger.info("userId:{}",userId);
        logger.info("authorId:{}",authorId);
        logger.info("userId:{}",shiroUtils.getUserId());
        if (StringUtils.equals(String.valueOf(authorId),shiroUtils.getUserId())){
            post.setTitle(title);
            post.setStatus(status);
            post.setTags(tags);
            post.setSummary(summary);
            int i = postService.updatePost(post);
            if (i>0){
                return ServerResponseEntity.success("修改成功",post);
            }
            return ServerResponseEntity.success(100001,"修改失败",post);
        }else {
            return ServerResponseEntity.success(100002,"修改失败，您不是当前博客的作者",post);
        }

    }

    @LogOperator(value = "删除博客",type = LOG_POST)
    @DeleteMapping("deletePost")
    @ApiOperation(value ="删除博客",notes="删除博客")
    //@RequiresRoles(value = {TYPE_ADMIN,TYPE_USER,TYPE_PRODUCT,TYPE_ASSISTANT,TYPE_SUPERADMIN})
    public ServerResponseEntity deletePost(String ids){
        System.out.println("delete");
        List<Long> categoryids = convertString(ids);
        Long id = categoryids.get(0);

//        PageResult<Post> postListByOther = postService.getPostListByOther(initPage(1, 1), null, null,id, null, null);
//        if (postListByOther.getTotalSize()==0){
//            return ResponseResult.successResult(100003,"当前内容不存在",postListByOther.getData().get(0));
//        }
//        if (StringUtils.equals(String.valueOf(postListByOther.getData().get(0).getAuthorId()),shiroUtils.getUserId())){
//            ResponseResult.successResult(100001,"删除失败，您不是当前博客的作者",postListByOther.getData().get(0));
//        }
        String userId = shiroUtils.getUserId();

        ResultList<Long> longResultList = postService.deletePost(categoryids, Long.parseLong(userId));
        if (longResultList.getSuccessNum()>0){
            return ServerResponseEntity.success("删除成功",longResultList/*,postListByOther.getData().get(0)*/);
        }
        return ServerResponseEntity.success(100001,"删除失败",longResultList/*,postListByOther.getData().get(0)*/);
    }

    @GetMapping("getPosts")
    @ApiOperation(value = "获取博客列表",notes = "获取博客列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ServerResponseEntity getPosts(@RequestParam("per_page")Integer per_page,
                                        @RequestParam("page")Integer page) {
        PageResult pageResult = postService.getPost(initPage(page,per_page));
        return ServerResponseEntity.success(pageResult);

    }

    @GetMapping("getPostListByOther")
    @ApiOperation(value = "根据需求获取博客列表",notes = "根据需求获取博客列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ServerResponseEntity getPostListByOther(@RequestParam("per_page")Integer per_page,
                                             @RequestParam("page")Integer page/*, Long authorId*/, String authorName, Long postId, Integer status, String title, String startTime, String endTime) throws ParseException {
        //startPage(page,per_page);
        //String principal = (String) SecurityUtils.getSubject().getPrincipal();
        //Claims claims = jwtUtil.parseJWT(principal);
        //String userId =(String)claims.get("userId");
        String userId = shiroUtils.getUserId();
        if (userId==null /*&& !StringUtils.equals(userId,String.valueOf(authorId))*/){
            return ServerResponseEntity.success(initPage(page, per_page).getPageFilter(initPage(page, per_page),new ArrayList()));
        }

        PageResult<Post> postListByOther = postService.getPostListByOther(initPage(page, per_page), Long.parseLong(userId), authorName,postId, status, title,startTime,endTime);
        logger.info("postList：{}",postListByOther);
        //PageInfo<?> pageList = getPageList(post);
        //PageResult postsPage = getPageResult(pageList);
        return ServerResponseEntity.success(postListByOther);

    }


    @GetMapping("getPublicPostListByOther")
    @ApiOperation(value = "对外根据需求获取博客列表",notes = "对外根据需求获取博客列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ServerResponseEntity getPublicPostListByOther(@RequestParam("per_page")Integer per_page,
                                             @RequestParam("page")Integer page, Long authorId,String authorName, Long postId, Integer status, String title, String startTime, String endTime) {
        //startPage(page,per_page);
        //String principal = (String) SecurityUtils.getSubject().getPrincipal();
        //Claims claims = jwtUtil.parseJWT(principal);
        //String userId =(String)claims.get("userId");
        String userId = shiroUtils.getUserId();
        if (authorId!=null && StringUtils.equals(userId,String.valueOf(authorId))){
            return ServerResponseEntity.success(initPage(page, per_page).getPageFilter(initPage(page, per_page),new ArrayList()));
        }
        PageResult<Post> postListByOther = postService.getPostListByOther(initPage(page, per_page), authorId, authorName,postId, status, title,startTime,endTime);
        logger.info("postList：{}",postListByOther);
        //PageInfo<?> pageList = getPageList(post);
        //PageResult postsPage = getPageResult(pageList);
        return ServerResponseEntity.success(postListByOther);

    }

    @GetMapping("getPostNum")
    @ApiOperation(value = "获取所有文档的状态",notes = "获取所有文档的状态")
    public ServerResponseEntity getPostNum(){
        String userId = shiroUtils.getUserId();
        PostNum postNum = postService.getPostNum(userId);
        return ServerResponseEntity.success(postNum);
    }

}
