package com.classAttendance.common.vo;

import java.util.List;

/**
 * @projectName ClassAttendance
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-04-30 23:06
 * @Describe：
 **/
public class PageBean<T> {

    /**
     * 当前页数
     */
    private int currPage;
    /**
     * 每页显示的记录数
     */
    private int pageSize;
    /**
     * 总计录数
     */
    private int total;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 每页显示的数据
     */
    private List<T> rows;

    public int getCurrPage() {
        return currPage;
    }

    public PageBean<T> setCurrPage(int currPage) {
        this.currPage = currPage;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageBean<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PageBean<T> setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public PageBean<T> setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public PageBean<T> setRows(List<T> rows) {

        /**
         * 计算总页数：
         *      因为我在执行方法之前通过QBC获得了总记录数
         */
        if (total > 0) {
            this.totalPage = this.total%this.pageSize == 0 ? this.total/this.pageSize : this.total/this.pageSize + 1;
        }

        this.rows = rows;
        return this;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", rows=" + rows +
                '}';
    }

}
