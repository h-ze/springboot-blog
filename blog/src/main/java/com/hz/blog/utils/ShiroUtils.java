package com.hz.blog.utils;


import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiroUtils {


    @Autowired
    private JWTUtil jwtUtil;

    public String getUserId(){
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        Claims claims = jwtUtil.parseJWT(principal);
        return (String)claims.get("userId");
    }
}
