package com.hz.blog.dao;


import com.hz.blog.entity.DocTask;

public interface TaskDao {

    int addTask(DocTask docTask);

    int updateTask(DocTask docTask);

    DocTask selectTask(String taskId);
}
