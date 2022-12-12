package com.hz.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class ResultList<T> implements Serializable {

    private Integer successNum;

    private Integer failNum;

    private List<T> successList;

    private List<T> failList;


}
