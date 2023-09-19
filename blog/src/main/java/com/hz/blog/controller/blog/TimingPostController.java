package com.hz.blog.controller.blog;


import com.hz.blog.controller.BaseController;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.PostTiming;
import com.hz.blog.response.ServerResponseEntity;
import com.hz.blog.service.PostTimingService;
import com.hz.blog.utils.ShiroUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hz.blog.constant.Constant.TYPE_SUPERADMIN;

@RestController
@RequestMapping("timingPost")
@Slf4j
public class TimingPostController extends BaseController {

    @Autowired
    private PostTimingService postTimingService;

    @Autowired
    private ShiroUtils shiroUtils;

    @ApiOperation(value ="获取所有的定时任务",notes="获取所有的定时任务")
    @GetMapping("getTimingPost")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "per_page",value = "每页数量",paramType = "query",dataType = "int",required = true)
    })
    public ServerResponseEntity getTimingPost(@RequestParam("per_page")Integer per_page,
                                                      @RequestParam("page")Integer page){
        String userId = shiroUtils.getUserId();

        PageResult<PostTiming> list = postTimingService.getPostTimingListByAuthor(Long.valueOf(userId), initPage(page, per_page));
        //List<TagVo> tagVos = EntityConvertDtoAndVOUtils.convertList(tags, TagVo.class);
        return ServerResponseEntity.success(list);
    }

    @ApiOperation(value ="删除指定定时任务",notes="删除指定定时任务")
    @DeleteMapping("deleteTimingPost")
    public ServerResponseEntity deleteTimingPost(Long postId){

        int i = postTimingService.deletePostTiming(postId);
        if (i>0){
            return ServerResponseEntity.success("删除成功");
        }
        return ServerResponseEntity.success(100001,"删除失败");

    }

    @RequiresRoles(TYPE_SUPERADMIN)
    @ApiOperation(value ="修改定时任务",notes="修改定时任务")
    @PutMapping(value = "updateTimingPost" ,consumes = "application/x-www-form-urlencoded")

    public ServerResponseEntity updateTimingPost(Long postId,Integer status){
        log.info("postId:{}",postId);
        PostTiming postTiming = postTimingService.getPostTimingById(postId);
        log.info("postTiming:{}",postTiming);
        if (postTiming ==null){
            return ServerResponseEntity.success(100002,"文档不存在");
        }
        postTiming.setStatus(status);
        int i = postTimingService.updatePostTiming(postTiming);
        if (i>0){
            return ServerResponseEntity.success("修改成功");
        }
        return ServerResponseEntity.success(100001,"修改失败");
    }
}
