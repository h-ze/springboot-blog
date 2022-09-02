package com.hz.blog.dao;

import com.hz.blog.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface PostDao {

    int addPost();

    int updatePost();

    int deletePost();

    Post getPostById(BigInteger id);

    Post getPostByName(String name);

    List<Post> getPostListByAuthor(BigInteger authorId);
}
