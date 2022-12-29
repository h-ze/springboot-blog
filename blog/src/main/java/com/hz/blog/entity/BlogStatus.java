package com.hz.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class BlogStatus {
    private String statusId;
    private Integer statusName;
    private String statusValue;
    private String statusType;
}
