package com.jirly.sso.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2018/6/29
 */
public class PageRequest {
    private Integer pageID;
    private Integer pageCount;
    private Map<String, Object> params;
    private PageInfo page;

    public Integer getPageID() {
        return pageID;
    }

    public void setPageID(Integer pageID) {
        this.pageID = pageID;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public PageRequest addParam(String key, Object value) {
        if (key == null) {
            return this;
        }
        if (value == null || "".equals(value)) {
            return this;
        }
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put(key, value);
        return this;
    }
}
