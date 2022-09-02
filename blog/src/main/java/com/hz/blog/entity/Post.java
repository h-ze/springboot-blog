package com.hz.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
public class Post {

    private BigInteger id;

    private BigInteger authorId;

    //渠道id
    private Integer channelId;

    //评论数
    private Integer comments;

    //创建时间
    private DateTime created;

    //喜爱数
    private Integer favors;

    //
    private Integer featured;

    //状态
    private Integer status;

    //概要
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
}
