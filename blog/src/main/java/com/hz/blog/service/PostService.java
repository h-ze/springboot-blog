package com.hz.blog.service;

import com.hz.blog.entity.Post;
import com.hz.blog.vo.PostVo;

import java.math.BigInteger;
import java.util.List;

public interface PostService {

    int addPost(PostVo postVo);

    int updatePost(Post post);

    int deletePost(BigInteger id);

    Post getPostById(BigInteger id);

    Post getPostByName(String name);

    List<Post> getPostListByAuthor(BigInteger authorId);

    List<Post> getPost();

    List<Post> getPostListByOther(BigInteger authorId,Integer status,String title);
}
