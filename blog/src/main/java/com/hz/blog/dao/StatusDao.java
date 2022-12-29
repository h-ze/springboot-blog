package com.hz.blog.dao;


import com.hz.blog.entity.BlogStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatusDao {

//    List<BlogStatus> getLabel(String label);
//
//    List<BlogStatus> getLabels();
//
//    int deleteLabel(String labelId);
//
//    int addLabel(BlogStatus label);
//
//    int updateLabel(BlogStatus label);


    List<BlogStatus> getOptionsStatus(String status);

    List<BlogStatus> getStatus();

    int updateStatus(BlogStatus status);

    int addStatus(BlogStatus status);

    int deleteStatus(String statusId);
}
