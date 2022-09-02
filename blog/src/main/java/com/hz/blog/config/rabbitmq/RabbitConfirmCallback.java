package com.hz.blog.config.rabbitmq;

import com.hz.blog.entity.Email;
import com.hz.blog.entity.MailConstants;
import com.hz.blog.service.EmailService;
import com.hz.blog.task.MailReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

@Component("confirmCallback")
@Slf4j
public class RabbitConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private EmailService emailService;

    @Autowired
    private MailReceiver mailReceiver;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息唯一标识: {}", correlationData.getId());
        log.info("确认状态: {}", ack);
        log.info("原因 cause: ={}",cause);
        Message message = correlationData.getReturnedMessage();
        String s = correlationData.toString();
        log.info("s:{}",s);
        log.info("返回消息:{}",message);

/*        try{
            if (MailConstants.MAIL_QUEUE_NAME.equals(message.getMessageProperties().getConsumerQueue())){
                log.info("信息: "+message);
                System.out.println("消费的消息来自的队列名为："+message.getMessageProperties().getConsumerQueue());
                //System.out.println("消息成功消费到  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
                System.out.println("执行MAIL_QUEUE_NAME中的消息的业务处理流程......");


                byte[] data = message.getBody();
                ByteArrayInputStream inputStream=new ByteArrayInputStream(data);
                ObjectInputStream oInputStream=new ObjectInputStream(inputStream);
                Object obj=oInputStream.readObject();
                Email email = (Email) obj;
                Integer emailId = email.getEmailId();

                log.info("obj为: {}",obj);
                log.info("email为: {} ",email);
                log.info("emailId:{}",emailId);
                mailReceiver.sendEmail(email);

                emailService.updateEmailStatus(email,1);
                //channel.basicAck(emailId,false);
                //channel.basicPublish();

            }

            if ("fanout.A".equals(message.getMessageProperties().getConsumerQueue())){
                System.out.println("消费的消息来自的队列名为："+message.getMessageProperties().getConsumerQueue());
                //System.out.println("消息成功消费到  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
                System.out.println("执行fanout.A中的消息的业务处理流程......");

            }

            if ("test".equals(message.getMessageProperties().getConsumerQueue())){
                System.out.println("消费的消息来自的队列名为："+message.getMessageProperties().getConsumerQueue());
                //System.out.println("消息成功消费到  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
                System.out.println("执行test中的消息的业务处理流程......");

            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }


}