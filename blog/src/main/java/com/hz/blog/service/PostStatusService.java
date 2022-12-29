package com.hz.blog.service;

import com.hz.blog.entity.BlogStatus;

import java.util.List;

public interface PostStatusService {
    List<BlogStatus> getOptionsStatus(String status);

    List<BlogStatus> getStatus();

    int updateStatus(BlogStatus status);

    int addStatus(BlogStatus status);

    int deleteStatus(String statusId);
}
