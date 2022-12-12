package com.hz.blog.dao;

import com.hz.blog.annotation.StartPage;
import com.hz.blog.entity.Document;
import com.hz.blog.entity.PageResult;
import com.hz.blog.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface PostDao {

    int addPost(Post post);

    int updatePost(Post post);

    int deletePost(@Param("postId") Long postId,@Param("authorId") Long authorId);

    int deletePosts(@Param("posts") List<Long> posts,@Param("authorId") Long authorId);
    //int addDocs(@Param("documents") List<Document> documents);


    Post getPostById(BigInteger id);

    Post getPostByName(String name);

    List<Post> getPostListByAuthor(BigInteger authorId);

    @StartPage
    List<Post> getPost(PageResult pageResult);

    @StartPage
    List<Post> getPostListByOther(PageResult pageResult,@Param("authorId") Long authorId,@Param("authorName") String authorName, @Param("postId") Long postId,@Param("status") Integer status, @Param("title")String title);
}
