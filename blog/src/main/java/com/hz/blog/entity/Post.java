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
    private Integer channelId;
    private Integer comments;
    private DateTime created;
    private Integer favors;
    private Integer featured;
    private Integer status;
    private String summary;
    private String tags;
    private String thumbnail;
    private String title;
    private Integer views;
    private Integer weight;
}
