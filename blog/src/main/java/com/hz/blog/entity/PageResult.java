package com.hz.blog.entity;

import java.util.List;
/**
 * 分页返回结果
 */
public class PageResult<T> {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<T> list;

    private int currentSize;

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public long getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public List<T> getData() {
        return list;
    }
    public void setData(List<T> data) {
        this.list = data;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    /**
     * @param obj 继承了PageFilter类的子类
     * @param response 从数据库查询出来的参数
     * @return
     */
    public PageResult<T> getPageFilter(PageResult obj,List<T> response) {
        PageResult<T> pages = new PageResult();
        pages.setPageNum(obj.getPageNum());
        pages.setPageSize(obj.getPageSize());
        pages.setTotalSize(obj.getTotalSize());
        pages.setTotalPages(obj.getTotalPages());
        pages.setData(response);
        pages.setCurrentSize(response.size());
        //pageResult.setData(page.getList());
        //pageResult.setCurrentSize(page.getList().size());
        return pages;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                ", totalPages=" + totalPages +
                ", list=" + list +
                ", currentSize=" + currentSize +
                '}';
    }
}