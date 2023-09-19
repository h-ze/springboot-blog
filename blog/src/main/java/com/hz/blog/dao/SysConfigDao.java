package com.hz.blog.dao;

import com.hz.blog.entity.SysOss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigDao {
    int createSysConfig(SysOss sysOss);

    SysOss selectOss(String sysName);

    List<SysOss> selectAllOss();

    int updateOss(SysOss sysOss);

    int deleteOss(String sysName);
}
