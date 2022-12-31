package com.hz.blog.service.impl;

import com.hz.blog.dao.PostTimingDao;
import com.hz.blog.entity.Post;
import com.hz.blog.entity.PostTiming;
import com.hz.blog.service.PostTimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class PostTimingServiceImpl implements PostTimingService {

    @Autowired
    private PostTimingDao postTimingDao;

    @Override
    public int addPostTiming(PostTiming postTiming) {
        return postTimingDao.addPostTiming(postTiming);
    }

    @Override
    public int updatePostTiming(Long postId,Integer status) {
        return postTimingDao.updatePostTiming(postId,status);
    }

    @Override
    public int deletePostTiming(Long postId) {
        return postTimingDao.deletePostTiming(postId);
    }

    @Override
    public List<PostTiming> getPostTimingListByAuthor(Long authorId) {
        return postTimingDao.getPostTimingListByAuthor(authorId);
    }

    @Override
    public PostTiming getPostTimingById(Long postId) {
        return postTimingDao.getPostTimingById(postId);
    }
}
