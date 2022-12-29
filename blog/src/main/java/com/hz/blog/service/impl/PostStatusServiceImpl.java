package com.hz.blog.service.impl;

import com.hz.blog.dao.StatusDao;
import com.hz.blog.entity.BlogStatus;
import com.hz.blog.service.PostStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostStatusServiceImpl implements PostStatusService {

    @Autowired
    private StatusDao statusDao;

    @Override
    public List<BlogStatus> getOptionsStatus(String status) {
        return statusDao.getOptionsStatus(status);
    }

    @Override
    public List<BlogStatus> getStatus() {
        return statusDao.getStatus();
    }

    @Override
    public int updateStatus(BlogStatus status) {
        return statusDao.updateStatus(status);
    }

    @Override
    public int addStatus(BlogStatus status) {
        return statusDao.addStatus(status);
    }

    @Override
    public int deleteStatus(String statusId) {
        return statusDao.deleteStatus(statusId);
    }
}
