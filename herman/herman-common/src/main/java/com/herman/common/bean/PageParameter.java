package com.herman.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询对象
 * @param <T>
 */
public class PageParameter<T> implements Serializable{

    private static final long serialVersionUID = 4105045759855108221L;

    public static final int DEFAULT_PAGE_SIZE = 10;
    private int pageSize;
    private int pageNo;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int totalCount;
    private List<T> resultList = new ArrayList<T>();

    public PageParameter() {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageParameter(int currentPage, int pageSize) {
        this.pageNo = currentPage;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}
