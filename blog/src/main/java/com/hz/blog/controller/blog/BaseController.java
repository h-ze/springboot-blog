package com.hz.blog.controller.blog;


import com.github.pagehelper.PageInfo;
import com.hz.blog.entity.PageRequest;
import com.hz.blog.entity.PageResult;
import com.hz.blog.utils.DateUtils;
import com.hz.blog.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.xml.crypto.Data;
import java.beans.PropertyEditorSupport;
import java.util.List;

public class BaseController {
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
     * 只有紧跟着PageHelper.startPage(pageNum,pageSize)的sql语句才被pagehelper起作用
     * @param page
     * @param per_page
     */
    protected void startPage(int page,int per_page){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(page);
        pageRequest.setPageSize(per_page);
        PageUtils.startPage(pageRequest);
    }

    protected PageInfo<?> getPageList(List<?> list){
        return new PageInfo<>(list);
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


    /**
     * 清理分页的线程变量
     */
    protected void clearPage(){
        PageUtils.clearPage();
    }

}
