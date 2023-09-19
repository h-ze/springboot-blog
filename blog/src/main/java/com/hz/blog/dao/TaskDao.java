package com.hz.blog.dao;


import com.hz.blog.entity.DocTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskDao {

    int addTask(DocTask docTask);

    int updateTask(DocTask docTask);

    DocTask selectTask(String taskId);
}
