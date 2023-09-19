package com.hz.blog.dao;


import com.hz.blog.entity.Email;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailDao {

    int addEmailMessage(Email email);

    Email getEmail(String email);

    List<Email> getEmailList(int status);

    int updateEmailStatus(Email email);

}
