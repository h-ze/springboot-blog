package com.hz.blog.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class Config {
    private JSONObject jsonObject;
}
