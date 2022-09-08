package com.hz.blog.service.impl;

import com.hz.blog.dao.CommentsDao;
import com.hz.blog.entity.Comments;
import com.hz.blog.entity.PageResult;
import com.hz.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsDao commentsDao;

    @Override
    public int addComments(Comments comments) {
        return commentsDao.addComments(comments);
    }

    @Override
    public int deleteComments(Long commentsId) {
        return commentsDao.deleteComments(commentsId);
    }

    @Override
    public PageResult<Comments> getComments(PageResult<Comments> pageResult,Long postId,Long commentsId) {
        List<Comments> comments = commentsDao.getComments(postId,commentsId);
        return pageResult.getPageFilter(pageResult,comments);
    }
}
