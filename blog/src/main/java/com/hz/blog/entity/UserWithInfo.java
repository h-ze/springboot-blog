package com.hz.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class UserWithInfo extends User implements Serializable {

    private static final long serialVersionUID = -717083638131665718L;
    private String fullName;
}
