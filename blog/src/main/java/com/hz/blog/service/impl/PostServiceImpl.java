package com.hz.blog.service.impl;

import com.hz.blog.entity.PostTag;
import com.hz.blog.service.PostService;
import com.hz.blog.dao.PostDao;
import com.hz.blog.entity.Post;
import com.hz.blog.service.PostTagService;
import com.hz.blog.utils.EntityConvertDtoAndVOUtils;
import com.hz.blog.utils.SnowflakeManager;
import com.hz.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostTagService postTagService;

    @Autowired
    private SnowflakeManager snowflakeManager;

    @Override
    public int addPost(PostVo postVo) {
        Post post = EntityConvertDtoAndVOUtils.convertBean(postVo, Post.class);

        try {
            post.setPostId(BigInteger.valueOf(snowflakeManager.nextValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        post.setCreated(new Date());

        int i = postDao.addPost(post);
        PostTag postTag = new PostTag();
        postTag.setPostId(post.getPostId());
        postTag.setTagId(postVo.getTagId());
        postTag.setWeight(BigInteger.valueOf(0L));
        postTagService.addPostTag(postTag);
        return i;
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

    @Override
    public List<Post> getPostListByOther(BigInteger authorId, Integer status, String title) {
        return postDao.getPostListByOther(authorId, status, title);
    }
}
