package com.hz.blog.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 前端对应的Tag
 */

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
public class TagVo implements Serializable {

    private static final long serialVersionUID = 4246784093049168408L;

    @ApiModelProperty(value = "标题不能为空")
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空字符")
    private String name;

    private String introduction;

    private String image;

    private String tagId;
}
