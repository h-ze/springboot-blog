package com.hz.blog.dao;

import com.hz.blog.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface PostDao {

    int addPost(Post post);

    int updatePost(Post post);

    int deletePost(BigInteger id);

    Post getPostById(BigInteger id);

    Post getPostByName(String name);

    List<Post> getPostListByAuthor(BigInteger authorId);

    List<Post> getPost();

    List<Post> getPostListByOther(@Param("authorId") BigInteger authorId, @Param("status") Integer status, @Param("title")String title);
}
