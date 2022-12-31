package com.hz.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class PostTiming {

    private Long id;
    private Long postId;
    private Integer status;
    private Long authorId;

}
