package com.hz.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
@ApiModel(value = "PostVo对象", description = "PostVo对象详情")
public class PostVo {

    private Long id;

    private Long postId;

    private Long authorId;

    private String content;

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
    @ApiModelProperty(value = "状态不能为空")
    @NotNull(message = "文章状态不能为空")
    //@Range(min = 1, max = 120, message = "年龄必须在1到120之间")
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

    private Long tagId;

    private String authorName;
}
