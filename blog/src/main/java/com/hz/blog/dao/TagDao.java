package com.hz.blog.dao;

import com.hz.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDao {
    int addTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTag(String id);

    Tag getTagByName(String name);

    Tag getTagById(String id);

    List<Tag> getTags();
}
