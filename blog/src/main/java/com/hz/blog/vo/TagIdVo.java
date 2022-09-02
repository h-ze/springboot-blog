package com.hz.blog.vo;


import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 前端对应的Tag
 */
public class TagIdVo implements Serializable {


    private static final long serialVersionUID = 5929981794410040338L;
    private Integer id;
    
    @ApiModelProperty(value = "标题不能为空")
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空字符")
    private String name;

    private String introduction;

    private String image;
}
