package com.hz.blog.service.impl;

import com.hz.blog.service.PostService;
import com.hz.blog.dao.PostDao;
import com.hz.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public int addPost(Post post) {
        return 0;
    }

    @Override
    public int updatePost(Post post) {
        return 0;
    }

    @Override
    public int deletePost(BigInteger id) {
        return 0;
    }

    @Override
    public Post getPostById(BigInteger id) {
        return null;
    }

    @Override
    public Post getPostByName(String name) {
        return null;
    }

    @Override
    public List<Post> getPostListByAuthor(BigInteger authorId) {
        return null;
    }
}
