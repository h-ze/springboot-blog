package com.hz.blog.task;

import com.hz.blog.entity.Email;
import com.hz.blog.entity.MailConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hz.blog.entity.Post;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


//利用rabbitmq自动发送邮箱激活链接 自动发送一次
@Component
@Slf4j
public class MailReceiver {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;
    //@Autowired
    //TemplateEngine templateEngine;
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 发送邮件
     * @param email
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void sendEmail(Email email) throws MessagingException, UnsupportedEncodingException {
        log.info("发送邮件");
        //构造SMTP邮件服务器的基本环境
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        //构造邮件

        List<File> fileList = new ArrayList<>();
        //fileList.add(new File("C:\\Users\\Admin\\Desktop\\Phantom权限标志位整理.xlsx"));

        List<File> picList = new ArrayList<>();
        //picList.add(new File("C:\\Users\\Admin\\Desktop\\捕获.PNG"));
        //picList.add(new File("C:\\Users\\Admin\\Desktop\\捕获.PNG"));

        MimeMessage mimeMessage = saveMessage(session,"1102211390@qq.com","1554752374@qq.com",null,"邮件主题",email,fileList,picList);
        //发送邮件
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", 25, "iskpdrftnlgohbih","kuwvhzyxkknujigi");
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//发送邮件，第二个参数为收件人
        transport.close();
    }

    /**
     * 构建主体内容
     * @param list 主体图片集合
     * @return
     */
    private String setEmailContent(List<String> list,Email email){
//        Context context = new Context();
//        context.setVariable("name", "test");
//        context.setVariable("posName", "test");
//        context.setVariable("joblevelName", "test");
//        context.setVariable("departmentName", "test");
//        context.setVariable("test","test");
//        String mail = templateEngine.process("mail", context);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String name : list) {
//            stringBuilder =stringBuilder.append("<img src='cid:"+name+"'/>");
//        }
//        return "<h1>Hello大家好，这是一封测试邮件"+stringBuilder.toString()+"</h1>"+mail;
        return "<h1>Hello大家好，这是一封测试邮件</h1>";
    }

    /**
     * 设置发送内容，包含附件等复杂内容
     * @param session Session对象
     * @param fromEmail 发送邮件的邮箱
     * @param toEmail 发送至的邮箱
     * @param ccEmail 抄送至的邮箱
     * @param subject 邮件主题
     * @param files 附件内容
     * @param headPic 正文的图片
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private MimeMessage saveMessage(Session session,String fromEmail, String toEmail, String ccEmail, String subject,Email email,List<File> files,List<File> headPic) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipients(javax.mail.Message.RecipientType.TO, toEmail);//设置收信人
        if (ccEmail!=null){
            mimeMessage.addRecipients(javax.mail.Message.RecipientType.CC, ccEmail);//抄送
        }
        mimeMessage.setFrom(fromEmail);//邮件发送人
        mimeMessage.setSubject(subject);//邮件主题

        MimeMultipart mixed = new MimeMultipart("mixed");
        mimeMessage.setContent(mixed);//设置整封邮件的MIME消息体为混合的组合关系

        for (File file : files) {
            addAttach(mixed,file);
        }
        MimeBodyPart content = new MimeBodyPart();//创建邮件正文
        mixed.addBodyPart(content);//将正文添加到消息体中

        MimeMultipart bodyMimeMultipart = new MimeMultipart("related");//设置正文的MIME类型
        content.setContent(bodyMimeMultipart);//将bodyMimeMultipart添加到正文消息体中

        MimeBodyPart bodyPart = new MimeBodyPart();//正文的HTML部分
        List<String> list = new ArrayList<>();
        for (File file : headPic) {
            MimeBodyPart picPart = new MimeBodyPart();//正文的图片部分
            DataHandler dataHandler = new DataHandler(new FileDataSource(file.getPath()));
            picPart.setDataHandler(dataHandler);
            String id = UUID.randomUUID().toString().replace("-","") + ".png";
            picPart.setContentID(id);
            list.add(id);
            bodyMimeMultipart.addBodyPart(picPart);
        }

        bodyPart.setContent(setEmailContent(list,email),"text/html;charset=utf-8");

        //将正文的HTML和图片部分分别添加到bodyMimeMultipart中
        bodyMimeMultipart.addBodyPart(bodyPart);

        mimeMessage.saveChanges();
        return mimeMessage;
    }

    private void addAttach(MimeMultipart mixed, File file) throws MessagingException, UnsupportedEncodingException {
        MimeBodyPart attach2 = new MimeBodyPart();//创建附件2

        mixed.addBodyPart(attach2);//将附件二添加到MIME消息体中

        //附件二的操作与附件一类似，这里就不一一注释了
        FileDataSource fds2 = new FileDataSource(file/*new File("C:\\Users\\sang\\Desktop\\博客笔记.xlsx")*/);
        DataHandler dh2 = new DataHandler(fds2);
        attach2.setDataHandler(dh2);
        attach2.setFileName(MimeUtility.encodeText(file.getName()));//设置文件名时，如果有中文，可以通过MimeUtility类中的encodeText方法进行编码，避免乱码
    }

}
