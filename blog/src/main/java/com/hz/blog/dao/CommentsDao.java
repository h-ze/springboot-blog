package com.hz.blog.dao;

import com.hz.blog.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface CommentsDao {
    int addComments(Comments comments);

    int deleteComments(BigInteger commentsId);

    List<Comments> getComments(BigInteger postId);
}
