package com.jirly.sso.domain;

import java.util.Date;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
public class WblogFriends {
    private Integer id;
    private Integer attentionedUser;
    private String attentionedUserName;
    private Integer attentionUser;
    private String attentionUserName;
    private Date attentionTime;
    private String attentionTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttentionedUser() {
        return attentionedUser;
    }

    public void setAttentionedUser(Integer attentionedUser) {
        this.attentionedUser = attentionedUser;
    }

    public String getAttentionedUserName() {
        return attentionedUserName;
    }

    public void setAttentionedUserName(String attentionedUserName) {
        this.attentionedUserName = attentionedUserName;
    }

    public Integer getAttentionUser() {
        return attentionUser;
    }

    public void setAttentionUser(Integer attentionUser) {
        this.attentionUser = attentionUser;
    }

    public String getAttentionUserName() {
        return attentionUserName;
    }

    public void setAttentionUserName(String attentionUserName) {
        this.attentionUserName = attentionUserName;
    }

    public Date getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(Date attentionTime) {
        this.attentionTime = attentionTime;
    }

    public String getAttentionTimeStr() {
        return attentionTimeStr;
    }

    public void setAttentionTimeStr(String attentionTimeStr) {
        this.attentionTimeStr = attentionTimeStr;
    }
}
