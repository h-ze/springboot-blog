package com.hz.blog.service;

import com.hz.blog.entity.Post;

import java.math.BigInteger;
import java.util.List;

public interface PostService {

    int addPost(Post post);

    int updatePost(Post post);

    int deletePost(BigInteger id);

    Post getPostById(BigInteger id);

    Post getPostByName(String name);

    List<Post> getPostListByAuthor(BigInteger authorId);
}
