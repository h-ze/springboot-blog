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
        return postDao.addPost(post);
    }

    @Override
    public int updatePost(Post post) {
        return postDao.updatePost(post);
    }

    @Override
    public int deletePost(BigInteger id) {
        return postDao.deletePost(id);
    }

    @Override
    public Post getPostById(BigInteger id) {
        return postDao.getPostById(id);
    }

    @Override
    public Post getPostByName(String name) {
        return postDao.getPostByName(name);
    }

    @Override
    public List<Post> getPostListByAuthor(BigInteger authorId) {
        return postDao.getPostListByAuthor(authorId);
    }

    @Override
    public List<Post> getPost() {
        return postDao.getPost();
    }
}
