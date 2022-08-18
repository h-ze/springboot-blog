package com.hz.blog.dao;


import com.hz.blog.entity.Email;

import java.util.List;

public interface EmailDao {

    int addEmailMessage(Email email);

    Email getEmail(String email);

    List<Email> getEmailList(int status);

    int updateEmailStatus(Email email);

}
