package com.hz.blog.service;

import com.hz.blog.entity.Comments;
import com.hz.blog.entity.PageResult;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {

    int addComments(Comments comments);

    int deleteComments(Long commentsId);

    PageResult<Comments> getComments(PageResult<Comments> pageResult,Long postId,Long commentsId);
}
