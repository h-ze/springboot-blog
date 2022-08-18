package com.hz.blog.service;


import com.hz.blog.entity.DocTask;

import java.util.concurrent.Future;

public interface AsyncService {

    void test1();

    void test2();

    void test3();

    Future<String> test4();

    void convert(DocTask docTask);

    int createTask(DocTask docTask);

    int updateTask(DocTask docTask);

    DocTask getTask(String taskId);
}
