package com.hz.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class LogEntity implements Serializable {

    private String logId;

    private String userId;

    private String username;

    private String email;

    private String phone;

    private String ip;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date operatorDate;

    //操作
    private String operator;

    //用户操作的响应
    private Integer code;


}
