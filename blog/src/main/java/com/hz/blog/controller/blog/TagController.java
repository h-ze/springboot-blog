package com.hz.blog.controller.blog;


import com.hz.blog.controller.BaseController;
import com.hz.blog.entity.ResultList;
import com.hz.blog.entity.Tag;
import com.hz.blog.response.ServerResponseEntity;
import com.hz.blog.service.TagService;
import com.hz.blog.utils.EntityConvertDtoAndVOUtils;
import com.hz.blog.vo.TagVo;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hz.blog.constant.Constant.TYPE_SUPERADMIN;
import static com.hz.blog.utils.ConvertUtils.stringConvertString;


@Api(tags = "标签接口")
@RestController()
@RequestMapping("tagController")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;


    @ApiOperation(value ="获取所有的标签",notes="获取所有的标签")
    @GetMapping("getTags")
    public ServerResponseEntity getTags(){
        List<Tag> tags = tagService.getTags();
        List<TagVo> tagVos = EntityConvertDtoAndVOUtils.convertList(tags, TagVo.class);
        return ServerResponseEntity.success(tagVos);
    }


    @RequiresRoles(TYPE_SUPERADMIN)
    @ApiOperation(value ="删除指定标签",notes="删除指定标签")
    @DeleteMapping("deleteTag")
    public ServerResponseEntity deleteTag(String tagId){

        List<String> list = stringConvertString(tagId);

//        String id = categoryids.get(0);
//        logger.info("id:{}",id);
//        Tag tagById = tagService.getTagById(id);
//        if (tagById ==null){
//            return ResponseResult.successResult(100004,"标签不存在",new Tag());
//        }
        ResultList<String> stringResultList = tagService.deleteTag(list);
        if (stringResultList.getSuccessNum()>0){
            return ServerResponseEntity.success("删除成功");
        }
        return ServerResponseEntity.success(100001,"删除失败");

    }

    @RequiresRoles(TYPE_SUPERADMIN)
    @ApiOperation(value ="插入标签",notes="插入标签")
    @PostMapping("addTag")
    public ServerResponseEntity addTag(@RequestBody() @ApiParam(name = "body",value = "标签信息",required = true) @Validated TagVo tagVo){
        String name = tagVo.getName();
        Tag tagByName = tagService.getTagByName(name);
        if (tagByName!=null){
            return ServerResponseEntity.success(100003,"标签已存在");
        }
        int i = tagService.addTag(tagVo);
        if (i>0){
            return ServerResponseEntity.success("插入成功",tagVo);
        }
        return ServerResponseEntity.success(100001,"插入失败",tagVo);
    }

    @RequiresRoles(TYPE_SUPERADMIN)
    @ApiOperation(value ="修改标签",notes="修改标签")
    @PutMapping(value = "updateTag" ,consumes = "application/x-www-form-urlencoded")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",dataType = "String",value = "标签名",required = true, paramType = "form"),
            @ApiImplicitParam(name = "introduction",dataType = "String",value = "标签介绍", paramType = "form"),
            @ApiImplicitParam(name = "image",dataType = "String",value = "图片地址",paramType = "form"),
            @ApiImplicitParam(name = "tagId",dataType = "String",value = "tagId",required = true,paramType = "form")
    })
    public ServerResponseEntity updateTag(String name,String introduction,String image,String tagId/*Tag tag*/){
        logger.info("tagId:{}",tagId);
        Tag tagById = tagService.getTagById(tagId);
        logger.info("tag:{}",tagById);
        if (tagById ==null){
            return ServerResponseEntity.success(100002,"文档不存在");
        }
        tagById.setName(name);
        tagById.setImage(image);
        tagById.setIntroduction(introduction);
        int i = tagService.updateTag(tagById);
        if (i>0){
            return ServerResponseEntity.success("成功");
        }
        return ServerResponseEntity.success(100001,"修改失败");
    }

}
