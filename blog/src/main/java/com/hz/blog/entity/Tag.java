package com.hz.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Configuration
public class Tag implements Serializable {

    private static final long serialVersionUID = 3783270111600233886L;
    private Integer id;

    @ApiModelProperty(value = "标题不能为空")
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空字符")
    private String name;

    private String introduction;

    private String image;
}
