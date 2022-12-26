package com.hz.blog.utils;

import com.hz.blog.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ConvertUtils {

    public static List<Long> convertString(String string){
        List<Long> categoryids = new ArrayList<>();
        String[] idsArr = StringUtils.split(string, ",");
        try {
            categoryids = Arrays.stream(idsArr)
                    .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()) // 转换为 List<Integer>
                    .stream().distinct().collect(Collectors.toList()); // 去重操作
        } catch (Exception e) {
            throw new RRException("参数格式错误");
        }
        log.info("list：{}",categoryids);
        return categoryids;
    }

    public static List<String> stringConvertString(String string){
        List<String> categoryids = new ArrayList<>();
        String[] idsArr = StringUtils.split(string, ",");
        try {
            categoryids = Arrays.stream(idsArr)
                    .map(String::trim).collect(Collectors.toList()) // 转换为 List<Integer>
                    .stream().distinct().collect(Collectors.toList()); // 去重操作
        } catch (Exception e) {
            throw new RRException("参数格式错误");
        }
        log.info("list：{}",categoryids);
        return categoryids;
    }
}
