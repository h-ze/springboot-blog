package com.hz.blog.service.impl;

import com.hz.blog.entity.Email;
import com.hz.blog.dao.EmailDao;
import com.hz.blog.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("emailService")
@Transactional
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailDao emailDao;

    @Override
    public List<Email> getUnactivatedEmails(int status) {
        return emailDao.getEmailList(status);
    }

    @Override
    public int updateEmailStatus(Email email,int status) {
        email.setStatus(status);
        return emailDao.updateEmailStatus(email);
    }

    @Override
    public Email getEmail(String email) {
        return emailDao.getEmail(email);
    }

    @Override
    public int addEmailMessage(Email email) {
        return emailDao.addEmailMessage(email);
    }
}
