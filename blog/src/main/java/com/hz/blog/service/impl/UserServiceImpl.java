package com.hz.blog.service.impl;

import com.hz.blog.entity.*;
import com.hz.blog.annotation.BaseService;
import com.hz.blog.dao.EmailDao;
import com.hz.blog.dao.UserDAO;
import com.hz.blog.service.EmailService;
import com.hz.blog.service.RedisService;
import com.hz.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service("userService")
@Transactional
@Slf4j
@BaseService
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /*@Autowired
    private EmailDao emailDao;*/

    @Autowired
    private EmailService emailService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisService redisService;

    //@Autowired
    //private RedisUtils redisUtils;

    @Override
    //@Async("asyncServiceExecutor")
    public int save(User user, UserRoles userRoles) {
        log.info("异步");
        //user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setId(0);
        if (userRoles!=null){
            userRoles.setId(0);
            userDAO.addUserRoles(userRoles);
            Email email = new Email();
            email.setEmailId(0);
            email.setEmail(user.getName());
            email.setStatus(2);
            int i = emailService.addEmailMessage(email);


/*            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(String.valueOf(email.getEmailId()));
            Message message = new Message(email.toString().getBytes(),new MessageProperties());
            correlationData.setReturnedMessage(message);*/

            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_QUEUE_NAME,email, new CorrelationData(String.valueOf(email.getEmailId())));

            //CorrelationData correlationData = new CorrelationData();
            //correlationData.setId(String.valueOf(email.getEmailId()));
            //correlationData.setReturnedMessage();
            //rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_QUEUE_NAME,email, new CorrelationData(String.valueOf(email.getEmailId())));


            //rabbitTemplate.convertAndSend("test.exchange", "test",email, new CorrelationData(String.valueOf(email.getEmailId())));


            //log.info("i: {}",i);
        }

        return userDAO.save(user);
    }

    @Override
    public int addUserRoles(UserRoles userRoles) {
        userRoles.setRoleId(0);

        return userDAO.addUserRoles(userRoles);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User getUser(String username) {

        return userDAO.getUser(username);
        //throw new RuntimeException("认证失败");
    }

    @Override
    public User getUserWithRoles(String username) {
        User user = userDAO.getUser(username);
        if (user !=null){
            User rolesByUsername = userDAO.findRolesByUsername(username);
            user.setRoles(rolesByUsername.getRoles());
        }
        return user;
    }

    @Override
    public User getUserByUserId(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User findRolesByUsername(String username) {
        return userDAO.findRolesByUsername(username);
    }

    @Override
    public int deleteUser(String userId, String password) {
        int i = userDAO.deleteUserByOwner(userId, password);
        if (i>0){
            redisService.addExpireRedis();
        }
        return i;
    }

    @Override
    public int deleteUser(String userId) {
        int i = userDAO.deleteUser(userId);
        if (i>0){
            redisService.addExpireRedis();
        }
        return i;
    }

    @Override
    public int updateUserPassword(User user) {
        int i = userDAO.updateUser(user);
        if (i>0)
            redisService.addExpireRedis();

        return i;
    }

    @Override
    public String createQrImg() {
        return UUID.randomUUID().toString().replace("-","").trim();
    }

    @Override
    public UserWithInfo getUserWithInfo(String userId,String username) {
        UserWithInfo userWithInfo = userDAO.getUserWithInfo(userId,username);
        return userWithInfo;
    }

}
