package com.hz.blog.service;

import com.hz.blog.annotation.StartPage;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.Post;
import com.hz.blog.vo.PostVo;

import java.math.BigInteger;
import java.util.List;

public interface PostService {

    int addPost(Post post);

    int updatePost(Post post);

    int deletePost(Long id);

    Post getPostById(BigInteger id);

    Post getPostByName(String name);

    List<Post> getPostListByAuthor(BigInteger authorId);

    PageResult<Post> getPost(PageResult pageResult);


    PageResult<Post> getPostListByOther(PageResult pageResult,BigInteger authorId,Long postId,Integer status,String title);
}
