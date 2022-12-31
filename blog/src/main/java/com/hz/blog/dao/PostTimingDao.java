package com.hz.blog.dao;


import com.hz.blog.entity.Post;
import com.hz.blog.entity.PostTiming;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostTimingDao {

    int addPostTiming(PostTiming postTiming);

    int updatePostTiming(@Param("postId") Long postId, @Param("status") Integer status);

    int deletePostTiming(Long postId);

    List<PostTiming> getPostTimingListByAuthor(Long authorId);

    PostTiming getPostTimingById(Long postId);
}
