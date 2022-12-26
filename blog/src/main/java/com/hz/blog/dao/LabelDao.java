package com.hz.blog.dao;


import com.hz.blog.entity.BlogLabel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelDao {

    List<BlogLabel> getLabel(String label);

    List<BlogLabel> getLabels();

    int deleteLabel(String labelId);

    int addLabel(BlogLabel label);

    int updateLabel(BlogLabel label);
}
