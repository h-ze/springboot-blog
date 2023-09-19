package com.hz.blog.entity;


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
public class Comments {
    private Integer id;
    private Long commentsId;
    private Long postId;
    private String content;
    private Integer status;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;

    private String ip;
    private Long replyId;
    private String name;
    private String email;
}
