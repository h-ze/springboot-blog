package com.hz.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
public class PostVo {

    private BigInteger id;

    private BigInteger postId;

    private BigInteger authorId;

    //渠道id
    private Integer channelId;

    //评论数
    private Integer comments;

    //创建时间
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    //private Date created;

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

    private BigInteger tagId;

    private String authorName;
}
