package com.hz.blog.service.impl;

import com.hz.blog.dao.LabelDao;
import com.hz.blog.entity.BlogLabel;
import com.hz.blog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelDao labelDao;

    @Override
    public List<BlogLabel> getLabels(String label) {
        return labelDao.getLabel(label);
    }

    @Override
    public List<BlogLabel> getLabels() {
        return labelDao.getLabels();
    }

    @Override
    public int updateLabel(BlogLabel label) {
        return labelDao.updateLabel(label);
    }

    @Override
    public int addLabel(BlogLabel label) {
        return labelDao.addLabel(label);
    }

    @Override
    public int deleteLabel(String labelId) {
        return labelDao.deleteLabel(labelId);
    }
}
