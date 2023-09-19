package com.hz.blog.service;

import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.Post;
import com.hz.blog.entity.PostTiming;

import java.math.BigInteger;
import java.util.List;

public interface PostTimingService {


    int addPostTiming(PostTiming postTiming);

    int updatePostTiming(PostTiming postTiming);

    int deletePostTiming(Long postId);

    PageResult<PostTiming> getPostTimingListByAuthor(Long authorId,PageResult pageResult);

    PostTiming getPostTimingById(Long postId);

}
