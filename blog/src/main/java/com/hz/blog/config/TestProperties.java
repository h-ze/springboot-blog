package com.hz.blog.config;

import com.hz.blog.entity.SnowflakeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.hz.blog")
public class TestProperties {

    private SnowflakeProperties snowflake;

}
