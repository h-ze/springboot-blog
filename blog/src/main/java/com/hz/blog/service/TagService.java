package com.hz.blog.service;

import com.hz.blog.entity.Tag;

import java.util.List;

public interface TagService {

    int addTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTag(Integer id);

    Tag getTagByName(String name);

    Tag getTagById(Integer id);

    List<Tag> getTags();
}
