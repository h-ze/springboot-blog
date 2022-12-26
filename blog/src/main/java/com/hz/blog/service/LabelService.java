package com.hz.blog.service;

import com.hz.blog.entity.BlogLabel;

import java.util.List;

public interface LabelService {
    List<BlogLabel> getLabels(String label);

    List<BlogLabel> getLabels();

    int updateLabel(BlogLabel label);

    int addLabel(BlogLabel label);

    int deleteLabel(String labelId);
}
