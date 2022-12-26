package com.hz.blog.service;

import com.hz.blog.entity.ResultList;
import com.hz.blog.entity.Tag;
import com.hz.blog.vo.TagVo;

import java.util.List;

public interface TagService {

    int addTag(TagVo tag);

    int updateTag(Tag tag);

    ResultList<String> deleteTag(List<String> id);

    Tag getTagByName(String name);

    Tag getTagById(String id);

    List<Tag> getTags();
}
