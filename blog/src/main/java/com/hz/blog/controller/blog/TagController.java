package com.hz.blog.controller.blog;


import com.hz.blog.entity.ResponseResult;
import com.hz.blog.entity.Tag;
import com.hz.blog.entity.UserInfo;
import com.hz.blog.mapper.TagMapper;
import com.hz.blog.service.TagService;
import com.hz.blog.vo.TagVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "标签接口")
@RestController()
@RequestMapping("tagController")
public class TagController extends BaseController{

    @Autowired
    private TagService tagService;


    @GetMapping("getTags")
    @ApiOperation(value ="获取所有的标签",notes="获取所有的标签")
    public ResponseResult getTags(){

        List<Tag> tags = tagService.getTags();
        return ResponseResult.successResult(100000,tags);
    }


    @DeleteMapping("deleteTag")
    @ApiOperation(value ="删除指定标签",notes="删除指定标签")
    public ResponseResult deleteTag(Integer id){
        int i = tagService.deleteTag(id);
        if (i>0){
            return ResponseResult.successResult(100000,"成功");
        }
        return ResponseResult.successResult(100001,"删除失败");

    }

    @PostMapping("addTag")
    @ApiOperation(value ="插入标签",notes="插入标签")
    public ResponseResult addTag(@RequestBody() @ApiParam(name = "body",value = "标签信息",required = true) @Validated TagVo tagVo){

        Tag tag = TagMapper.INSTANCE.toDTO(tagVo);

        String name = tag.getName();
        Tag tagByName = tagService.getTagByName(name);
        if (tagByName!=null){
            return ResponseResult.successResult(100000,"标签已存在",tagByName);
        }

        int i = tagService.addTag(tag);
        if (i>0){
            return ResponseResult.successResult(100000,"插入成功",tag);
        }
        return ResponseResult.successResult(100001,"插入失败",tag);
    }

    @PutMapping("updateTag")
    @ApiOperation(value ="修改标签",notes="修改标签")
    public ResponseResult updateTag(Tag tag){
        int i = tagService.updateTag(tag);
        if (i>0){
            return ResponseResult.successResult(100000,"成功");
        }
        return ResponseResult.successResult(100001,"修改失败");
    }

}
