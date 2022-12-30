package com.hz.blog.config.rabbitmq;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hz.blog.entity.Email;
import com.hz.blog.entity.MailConstants;
import com.hz.blog.entity.Post;
import com.hz.blog.task.MailReceiver;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 消费rabbitmq
 */
@Component
@Slf4j
public class ConsumerRabbit {

    @Autowired
    private MailReceiver mailReceiver;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME
            /*bindings = @QueueBinding(
            value = @Queue(value = MailConstants.MAIL_QUEUE_NAME,durable = "true"),
            exchange = @Exchange(name="order-exchange",durable = "true",type = "topic"),
            key = "order.*"
            )*/
    )
    public void handler(Message message, Channel channel) throws IOException {

        log.info("接收MAIL_QUEUE_NAME服务：{}",message);

        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            // 1.转换消息，传递过来的实体类会自动转为Message，我们需要再转换回来
            ObjectMapper mapper = new ObjectMapper();
            Email email = mapper.readValue(message.getBody(), Email.class);
            log.info("收到邮件消息------------------{}", email);

            mailReceiver.sendEmail(email);
//            ObjectMapper mapper = new ObjectMapper();
//            log.info("message: ={}",message);
//            log.info("channel: ={}",channel);
//            MessageProperties messageProperties = message.getMessageProperties();
//            log.info("message: {}",message);
//            ByteArrayInputStream inputStream=new ByteArrayInputStream(message.getBody());
//            ObjectInputStream oInputStream=new ObjectInputStream(inputStream);
//            Object obj=oInputStream.readObject();
//            Email email = (Email) obj;
//            Email email = mapper.readValue(message.getBody(), Email.class);

            // 消息标识
            //Integer emailId = email.getEmailId();
            // 消费幂等性，如果已经消费，不再重复发送邮件
            //调用接口判断是否已经处理过
//            MessageRecord messageRecord = messageRecordService.getById(msgId);
//            if (messageRecord == null || messageRecord.getStatus().equals(MessageRecordConstant.CONSUMED_SUCCESS)) {
//                log.info("重复消费, msgId: {}", msgId);
//                return;
//            }

            boolean success=true;
            // 2.处理业务逻辑（发送邮件）
            //success= mailUtil.sendRegisterMail(mailMessage);
            if (success) {
                // 更新状态
                //messageRecordService.updateStatus(msgId, MessageRecordConstant.CONSUMED_SUCCESS);
                // 手动签收
                channel.basicAck(deliveryTag, false);
            } else {
                throw new RuntimeException("邮件发送失败");
            }
        } catch (Exception exception) {
            log.error("出现错误----------{}", exception.getMessage());

            // 4.拒绝签收，并且不重新入队
            channel.basicNack(deliveryTag, true, false);
        }

    }

    @RabbitListener(queues = "test")
    public void test(Message message, Channel channel){
        MessageProperties messageProperties = message.getMessageProperties();
        log.info("test消息: {}",messageProperties);
    }

    /**
     * 通过死信方式接收定时消息
     * @param post 接收的Post对象
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitHandler
    @RabbitListener(queues ="queue.postdlx")//监听队列名称
    public void dlxProcess(Post post, Channel channel, Message message) throws IOException {
        log.info("死信DL_KEY收到  : " + post );
        //System.out.println(1/0);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
            // 4.拒绝签收，并且不重新入队
            channel.basicNack(deliveryTag, true, false);
        }
    }

    //@RabbitHandler
    //@RabbitListener(queues = "REDIRECT_QUEUE")
    public void process1(String msg, Channel channel, Message message)throws Exception {
        System.out.println("REDIRECT_QUEUE: " +msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
