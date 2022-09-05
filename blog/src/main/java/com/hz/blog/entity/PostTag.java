package com.hz.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
public class PostTag {
    private BigInteger id;
    private BigInteger postId;
    private BigInteger tagId;
    private BigInteger weight;
}
