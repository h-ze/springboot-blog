package com.hz.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
public class PostNum implements Serializable {
    private Integer totalCount;
    private Integer type1;
    private Integer type2;
    private Integer type3;
    private Integer type4;
}
