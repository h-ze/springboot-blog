package com.hz.blog.controller.blog;

import com.hz.blog.response.ServerResponseEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "博客标签接口")
@RestController
@RequestMapping("/postTag")
public class PostTagController {

    @GetMapping("getPostTag")
    public ServerResponseEntity getPostTag(){
        return ServerResponseEntity.success("postTag");
    }

}
