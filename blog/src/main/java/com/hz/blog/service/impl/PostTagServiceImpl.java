package com.hz.blog.service.impl;

import com.hz.blog.dao.PostTagDao;
import com.hz.blog.entity.PostTag;
import com.hz.blog.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostTagServiceImpl implements PostTagService {

    @Autowired
    private PostTagDao postTagDao;

    @Override
    public int addPostTag(PostTag postTag) {
        int i = postTagDao.addPostTag(postTag);
        return i;
    }
}
