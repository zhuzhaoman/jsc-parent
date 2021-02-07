package com.zzm.sqlite.utils;

import java.util.List;

/**
 * 通用分页
 */
public class PagedGridResult<T> {

    // 当前页码
    protected int page;
    // 每页条数
    protected int size;
    // 总条数
    protected long total;
    // 总页数
    protected int totalPage;
    // 数据
    protected List<?> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
