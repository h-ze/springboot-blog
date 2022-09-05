package com.hz.blog.dao;

import com.hz.blog.entity.PostTag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostTagDao {

    int addPostTag(PostTag postTag);
}
