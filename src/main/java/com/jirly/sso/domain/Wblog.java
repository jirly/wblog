package com.jirly.sso.domain;



import com.jirly.sso.utils.DateUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/29
 */
public class Wblog {
    private Integer id;
    private Integer zoneType;
    private Integer createUser;
    private String createUserName;
    private Date createTime;
    private String createTimeStr;
    private String content;
    private String validContent;
    private String picAttaList;
    private String[] picList;
    private String userPic;
    private Integer remarkCount;
    private Integer upvoteCount;
    private Integer curUser;
    private String zoneName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZoneType() {
        return zoneType;
    }

    public void setZoneType(Integer zoneType) {
        this.zoneType = zoneType;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if (this.createTime != null) {
            this.createTimeStr = DateUtils.convertDateToString(this.createTime,DateUtils.DATETIME_PATTERN1);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if (this.content != null) {
            this.validContent = validContent(this.content);
        } else {
            this.validContent = "";
        }
    }

    public String getPicAttaList() {
        return picAttaList;
    }

    public void setPicAttaList(String picAttaList) {
        this.picAttaList = picAttaList;
        if (this.picAttaList != null) {
            this.picList = this.picAttaList.split(";");
        }
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String[] getPicList() {
        return picList;
    }

    public void setPicList(String[] picList) {
        this.picList = picList;
    }

    public Integer getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(Integer remarkCount) {
        this.remarkCount = remarkCount;
    }

    public Integer getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public String getValidContent() {
        return validContent;
    }

    public void setValidContent(String validContent) {
        this.validContent = validContent;
    }

    private  String validContent(String content) {
        String mode = "(http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&*=]*))";
        Pattern p = Pattern.compile(mode);
        Matcher m = p.matcher(content);
        String s = content;
        while (m.find()) {
            String url = m.group();
            s = s.replace(url,"<a href=\'" + url +"\'" + " target=\'_blank\'>" + url + "</a>");
        }
        return s;
    }

    public Integer getCurUser() {
        return curUser;
    }

    public void setCurUser(Integer curUser) {
        this.curUser = curUser;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
