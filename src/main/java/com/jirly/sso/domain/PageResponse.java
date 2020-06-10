package com.jirly.sso.domain;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2018/6/29
 */
public class PageResponse extends JsonResp {
    private Integer totalCount;
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPage;
    private  List items;
    private Object other;

    public PageResponse(Integer totalCount, Integer pageSize, Integer currentPage, Integer totalPage, List items, Object other) {
        super(SUCCESS_STATUS,items);
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.items = items;
        this.other = other;
    }

    public PageResponse(Integer totalCount, Integer pageSize, Integer currentPage, Integer totalPage, List items) {
        super(SUCCESS_STATUS,items);
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.items = items;
    }

    public PageResponse(Integer totalCount, List items) {
        super(SUCCESS_STATUS,items);
        this.totalCount = totalCount;
        this.items = items;
    }

    public PageResponse(int status, Integer totalCount, List items, String msg) {
        super(status,items,msg);
        this.totalCount = totalCount;
        this.items = items;
    }

    public static PageResponse emptyResult() {
        return new PageResponse(0,Collections.EMPTY_LIST);
    }

    public static PageResponse fail(String msg) {
        return new PageResponse(DEFAULT_FAIL_STATUS,0,Collections.EMPTY_LIST,msg);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }
}
