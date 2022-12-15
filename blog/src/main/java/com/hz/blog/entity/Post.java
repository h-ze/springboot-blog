package com.hz.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
public class Post implements Serializable {

    //自增id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    //postId
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long postId;

    //作者id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long authorId;

    private String content;

    //渠道id
    private Integer channelId;

    //评论数
    private Integer comments;

    //创建时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;

    //喜爱数
    private Integer favors;

    //特点
    private Integer featured;

    //状态
    private Integer status;

    //概要内容
    private String summary;

    //标签
    private String tags;

    //缩略图
    private String thumbnail;

    //标题
    private String title;

    //查看次数
    private Integer views;

    //权重
    private Integer weight;

    //作者姓名
    private String authorName;
}
