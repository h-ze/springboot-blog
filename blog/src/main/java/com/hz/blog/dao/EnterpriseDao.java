package com.hz.blog.dao;

import com.hz.blog.entity.EnterpriseInfo;

public interface EnterpriseDao {

    int addEnterpriseInfo(EnterpriseInfo enterpriseInfo);

    int updateEnterpriseInfo(EnterpriseInfo enterpriseInfo);

    EnterpriseInfo getEnterpriseInfo(String enterpriseId);

    int deleteEnterpriseInfo(String enterpriseId);

    EnterpriseInfo getEnterpriseInfoByUserId(String userId);
}
