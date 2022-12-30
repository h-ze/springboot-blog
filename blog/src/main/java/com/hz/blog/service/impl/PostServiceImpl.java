package com.hz.blog.service.impl;

import com.hz.blog.entity.*;
import com.hz.blog.service.PostService;
import com.hz.blog.dao.PostDao;
import com.hz.blog.service.PostTagService;
import com.hz.blog.utils.SnowflakeManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.hz.blog.config.rabbitmq.RabbitConfig.*;
import static com.hz.blog.entity.QueueEnum.POST_MQ;


@Service
@Transactional(rollbackFor = Exception.class)

@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostTagService postTagService;

    @Autowired
    private SnowflakeManager snowflakeManager;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public int addPost(Post post) {

        try {
            post.setPostId(Long.valueOf(snowflakeManager.nextValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //SimpleDateFormat inSdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

        //SimpleDateFormat outSdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.CHINA);
        //System.out.println(outSdf.format(new Date()));

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        String format = sf.format(new Date());
        System.out.println("-----"+format);
        post.setCreated(new Date());

        System.out.println("post111"+post);

        int i = postDao.addPost(post);
        PostTag postTag = new PostTag();
        postTag.setPostId(post.getPostId());
        //postTag.setTagId(postVo.getTagId());
        postTag.setWeight(Long.valueOf(0L));
        postTagService.addPostTag(postTag);
        return i;
    }

    @Override
    public int addPostOnTiming(Post post,String delayTime) {
        try {
            post.setPostId(Long.valueOf(snowflakeManager.nextValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("postId:{}",post.getPostId());
        //将消息携带路由键值
        rabbitTemplate.convertAndSend(POST_MQ.getExchange(), POST_MQ.getRouteKey(),
                post,message->{
                    message.getMessageProperties().setExpiration(delayTime);
                    return message;
                },new CorrelationData(post.getPostId().toString()));

        //rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_QUEUE","email", new CorrelationData());

        //rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_QUEUE_NAME,"email", new CorrelationData(String.valueOf("1")));

        return 0;
    }

    @Override
    public int updatePost(Post post) {
        return postDao.updatePost(post);
    }

    @Override
    public int deletePost(Long id,Long authorId) {
        int i = postDao.deletePost(id, authorId);
        log.info("i：{}",i);
        return i;
    }

    @Override
    public ResultList<Long> deletePost(List<Long> posts, Long authorId) {
        //设置true的话以后的增删改就不用提交事务
        ResultList<Long> longResultList = new ResultList<>();
        int successNum=1;
        int failNum =0;
        List<Long> successList =new ArrayList<>();
        List<Long> failList = new ArrayList<>();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false)) {

            PostDao mapper = sqlSession.getMapper(PostDao.class);
            for (int i=0;i<posts.size();i++){
                int deletePost = mapper.deletePost(posts.get(i), authorId);
                System.out.println("==="+deletePost);
                if (deletePost>0){
                    successNum++;
                    successList.add(posts.get(i));
                }else {
                    failNum++;
                    failList.add(posts.get(i));
                }
                if (i%1000 ==999){
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
            sqlSession.commit();
            sqlSession.clearCache();
        }
        longResultList.setFailNum(failNum);
        longResultList.setSuccessNum(successNum);
        longResultList.setSuccessList(successList);
        longResultList.setFailList(failList);
        return longResultList;
        //return postDao.deletePosts(posts,authorId);
    }

    @Override
    public Post getPostById(BigInteger id) {
        return postDao.getPostById(id);
    }

    @Override
    public Post getPostByName(String name) {
        return postDao.getPostByName(name);
    }

    @Override
    public List<Post> getPostListByAuthor(BigInteger authorId) {
        return postDao.getPostListByAuthor(authorId);
    }

    @Override
    public PageResult getPost(PageResult pageResult) {
        List<Post> post = postDao.getPost(pageResult);
        return pageResult.getPageFilter(pageResult,post);
    }

    @Override
    public PageResult<Post> getPostListByOther(PageResult pageResult,Long authorId,String authorName,Long postId,Integer status,String title,String startTime,String endTime) {
        Date startTime1 = null;
        Date endTime1 = null;

        try {
            if (startTime!=null)
            startTime1 = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);

            if (endTime!=null)
            endTime1 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Post> postListByOther = postDao.getPostListByOther(pageResult, authorId,authorName, postId, status, title,startTime1,endTime1);
        log.info("post:{}",postListByOther);
        return pageResult.getPageFilter(pageResult,postListByOther);

    }

    @Override
    public PostNum getPostNum(String authorId) {
        return postDao.getStatusNum(authorId);
    }
}
