package com.hz.blog.controller.blog;


import com.hz.blog.annotation.ParamCheck;
import com.hz.blog.entity.Comments;
import com.hz.blog.entity.Config;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.ResponseResult;
import com.hz.blog.service.CommentService;
import com.hz.blog.utils.JWTUtil;
import com.hz.blog.utils.SnowflakeManager;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;



@Api(tags = "commentController接口")
@RestController
@RequestMapping("commentController")
@Slf4j
public class CommentController extends BaseController{

    @Autowired
    private CommentService commentService;

    @Autowired
    private SnowflakeManager snowflakeManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private Config config;




    @PostMapping("addComments")
    @ApiOperation(value = "添加评论",notes = "添加评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "post_id",value = "回复的博客id",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "replyId",value = "回复的评论id",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "content",value = "回复内容",paramType = "query",dataType = "String",required = true)
    })
    @ParamCheck
    public ResponseResult addComments(HttpServletRequest request, @ParamCheck BigInteger post_id, @ParamCheck BigInteger replyId,@ParamCheck String content){


        /*String principal = (String) SecurityUtils.getSubject().getPrincipal();
        Claims claims = jwtUtil.parseJWT(principal);
        String userId = String.valueOf(claims.get("userId"));
        String fullName = String.valueOf(claims.get("fullName"));
        log.info("userId:{}",userId);
        log.info("fullName:{}",fullName);*/
        Object requestBody = request.getAttribute("requestBody");
        log.info("requestBody:{}",requestBody);

        log.info("config-requestBody:{}",config.getJsonObject());
        BigInteger snowflake = null;
        try {
            snowflake = BigInteger.valueOf(snowflakeManager.nextValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Comments comments = new Comments(0,snowflake , post_id, content, 0, new Date(), "1",replyId, "", "");
        int i = commentService.addComments(comments);
        if (i>0){
            return ResponseResult.successResult(100000,"评论成功",comments);
        }
        return ResponseResult.successResult(100001,"评论失败",comments);

    }


    @DeleteMapping("deleteComments")
    @ApiOperation(value = "删除评论",notes = "删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentsId",value = "评论的id",paramType = "query",dataType = "Long",required = true)
    })
    public ResponseResult deleteComments(BigInteger commentsId){

        PageResult<Comments> commentsList = commentService.getComments(initPage(1, 1), commentsId);
        Comments comments = commentsList.getData().get(0);
        if (comments ==null){
            return ResponseResult.successResult(100003,"评论不存在");
        }
        int i = commentService.deleteComments(commentsId);
        if (i>0){
            return ResponseResult.successResult(100000,"删除成功");
        }
        return ResponseResult.successResult(100001,"删除失败");

    }


}
