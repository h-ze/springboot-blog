package com.hz.blog.service.impl;

import com.hz.blog.dao.TagDao;
import com.hz.blog.entity.Tag;
import com.hz.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    @Override
    public int addTag(Tag tag) {
        int i = tagDao.addTag(tag);
        return i;
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Integer id) {
        int i = tagDao.deleteTag(id);
        return i;
    }

    @Override
    public Tag getTagByName(String name) {
        Tag tag = tagDao.getTagByName(name);
        return tag;
    }

    @Override
    public Tag getTagById(Integer id) {
        Tag tag = tagDao.getTagById(id);
        return tag;
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = tagDao.getTags();
        return tags;
    }
}
