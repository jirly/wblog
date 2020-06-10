package com.jirly.sso.service;


import com.jirly.sso.domain.PageRequest;
import com.jirly.sso.domain.Wblog;

import java.util.List;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/29
 */
public interface WblogService {
    void addBlog(Wblog wblog);

    List<Wblog> list(PageRequest parameters);

    int count(PageRequest queryParameters);

    void delBlog(Integer blogId);

    void saveOrUpdateUserPic(Integer userId, String s);

    String getUserPic(Integer curUser);

    Map<String,String> getUserOtherInfo(Integer curUser);

    List<Map> listConfig(Map<String, Object> params);

    void addConfig(Map<String, Object> params);

    void updateConfig(Map<String, Object> params);

    void validConfig(Integer id);

    List<Map> getEditUsers(String editUsers);
}
