package com.hz.blog.controller;


import com.github.pagehelper.PageInfo;
import com.hz.blog.entity.PageRequest;
import com.hz.blog.entity.PageResult;
import com.hz.blog.utils.DateUtils;
import com.hz.blog.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.xml.crypto.Data;
import java.beans.PropertyEditorSupport;
import java.util.List;

public class BaseController<T> {
    protected final Logger logger =LoggerFactory.getLogger(this.getClass());

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){

        // Date 类型转换
        webDataBinder.registerCustomEditor(Data.class,
                new PropertyEditorSupport()
                {
                    @Override
                    public void setAsText(String text)
                    {
                        setValue(DateUtils.convertParseDate(text));
                    }
                });
        webDataBinder.registerCustomEditor(String.class,
                new StringTrimmerEditor(true));
    }



    protected PageResult initPage(int page,int per_page){
        PageResult<Class> pageResult = new PageResult();
        pageResult.setPageNum(page);
        pageResult.setPageSize(per_page);
        return pageResult;
    }

    /**
     * 设置请求分页数据
     * @param pageRequest
     * @param list
     */
    protected PageInfo<?> startPage(PageRequest pageRequest, List<?> list){
        PageInfo<?> pageInfo = PageUtils.startPage(pageRequest, list);
        return pageInfo;
    }

    /**
     *
     * @param pageInfo
     * @return
     */
    protected PageResult getPageResult(PageInfo<?> pageInfo){
        PageResult pageResult = PageUtils.getPageResult(pageInfo);
        return pageResult;
    }

}
