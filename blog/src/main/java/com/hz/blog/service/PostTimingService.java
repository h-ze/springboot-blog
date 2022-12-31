package com.hz.blog.service;

import com.hz.blog.entity.Post;
import com.hz.blog.entity.PostTiming;

import java.math.BigInteger;
import java.util.List;

public interface PostTimingService {


    int addPostTiming(PostTiming postTiming);

    int updatePostTiming(Long postId,Integer status);

    int deletePostTiming(Long postId);

    List<PostTiming> getPostTimingListByAuthor(Long authorId);

    PostTiming getPostTimingById(Long postId);

}
