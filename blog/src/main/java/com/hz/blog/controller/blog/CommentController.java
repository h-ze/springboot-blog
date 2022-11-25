package com.hz.blog.controller.blog;


import com.hz.blog.annotation.ParamCheck;
import com.hz.blog.controller.BaseController;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;

import static com.hz.blog.constant.Constant.*;


@Api(tags = "commentController接口")
@RestController
@RequestMapping("commentController")
@Slf4j
public class CommentController extends BaseController {

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
    @RequiresRoles(value = {TYPE_ADMIN,TYPE_USER,TYPE_PRODUCT,TYPE_ASSISTANT,TYPE_SUPERADMIN})
    @ParamCheck
    public ResponseResult addComments(HttpServletRequest request, @ParamCheck BigInteger post_id, @ParamCheck BigInteger replyId,@ParamCheck String content){


        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        Claims claims = jwtUtil.parseJWT(principal);
        String userId = String.valueOf(claims.get("userId"));
        String fullName = String.valueOf(claims.get("fullName"));
        log.info("userId:{}",userId);
        log.info("fullName:{}",fullName);
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
    @RequiresRoles(value = {TYPE_ADMIN,TYPE_USER,TYPE_PRODUCT,TYPE_ASSISTANT,TYPE_SUPERADMIN})
    public ResponseResult deleteComments(Long commentsId){

        PageResult<Comments> commentsList = commentService.getComments(initPage(1, 1),null, commentsId);
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

    @GetMapping("getComments")
    @ApiOperation(value = "查询评论",notes = "查询评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentsId",value = "评论的id",paramType = "query",dataType = "Long",required = true),
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ResponseResult getComments(@ParamCheck Long post_id,Long commentsId,@RequestParam("per_page")Integer per_page,
                                      @RequestParam("page")Integer page){
        PageResult<Comments> commentsList = commentService.getComments(initPage(page, per_page),post_id, commentsId);
        return ResponseResult.successResult(100000,commentsList);
    }


}
