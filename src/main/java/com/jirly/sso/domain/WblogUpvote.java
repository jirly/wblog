package com.jirly.sso.domain;

import java.util.Date;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
public class WblogUpvote {
    private Integer id;
    private Integer blogId;
    private Integer upvoteUser;
    private String upvoteUserName;
    private Date upvoteTime;
    private String upvoteTimeStr;
    private int count;

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

    public Integer getUpvoteUser() {
        return upvoteUser;
    }

    public void setUpvoteUser(Integer upvoteUser) {
        this.upvoteUser = upvoteUser;
    }

    public String getUpvoteUserName() {
        return upvoteUserName;
    }

    public void setUpvoteUserName(String upvoteUserName) {
        this.upvoteUserName = upvoteUserName;
    }

    public Date getUpvoteTime() {
        return upvoteTime;
    }

    public void setUpvoteTime(Date upvoteTime) {
        this.upvoteTime = upvoteTime;
    }

    public String getUpvoteTimeStr() {
        return upvoteTimeStr;
    }

    public void setUpvoteTimeStr(String upvoteTimeStr) {
        this.upvoteTimeStr = upvoteTimeStr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
