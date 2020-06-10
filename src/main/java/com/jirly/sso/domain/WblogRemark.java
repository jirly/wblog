package com.jirly.sso.domain;


import com.jirly.sso.utils.DateUtils;

import java.util.Date;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
public class WblogRemark {
    private Integer id;
    private Integer blogId;
    private Integer remarkUser;
    private String remarkUserName;
    private String remarkUserPicPath;
    private Date remarkTime;
    private String remarkTimeStr;
    private String remarkContent;
    private Integer curUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getRemarkUser() {
        return remarkUser;
    }

    public void setRemarkUser(Integer remarkUser) {
        this.remarkUser = remarkUser;
    }

    public String getRemarkUserName() {
        return remarkUserName;
    }

    public void setRemarkUserName(String remarkUserName) {
        this.remarkUserName = remarkUserName;
    }

    public Date getRemarkTime() {
        return remarkTime;
    }

    public void setRemarkTime(Date remarkTime) {
        this.remarkTime = remarkTime;
        if (this.remarkTime != null) {
            this.remarkTimeStr = DateUtils.convertDateToString(this.remarkTime,DateUtils.DATETIME_PATTERN1);
        }
    }

    public String getRemarkTimeStr() {
        return remarkTimeStr;
    }

    public void setRemarkTimeStr(String remarkTimeStr) {
        this.remarkTimeStr = remarkTimeStr;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public String getRemarkUserPicPath() {
        return remarkUserPicPath;
    }

    public void setRemarkUserPicPath(String remarkUserPicPath) {
        this.remarkUserPicPath = remarkUserPicPath;
    }

    public Integer getCurUser() {
        return curUser;
    }

    public void setCurUser(Integer curUser) {
        this.curUser = curUser;
    }
}
