package com.hz.blog.dao;



import com.hz.blog.entity.UserFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserFileDao {
    //根据用户id获取文件列表
    List<UserFile> findByUserId(Integer id);

    void savaFileMessage(UserFile userFile);

    UserFile findFileById(String id);

    void updateFile(UserFile userFile);
}
