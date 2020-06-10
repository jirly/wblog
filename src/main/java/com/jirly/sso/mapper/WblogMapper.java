package com.jirly.sso.mapper;

import com.jirly.sso.domain.PageRequest;
import com.jirly.sso.domain.Wblog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/29
 */
public interface WblogMapper {
    void insert(Wblog wblog);

    int findResultCount(PageRequest queryParameters);

    List<Wblog> findResults(PageRequest parameters);

    void deleteById(Integer blogId);

    void saveOrUpdateUserPic(@Param(value = "userId") Integer userId, @Param(value = "userPicUrl") String userPicUrl);

    String getUserPic(Integer curUser);

    Map<String,String> getUserOtherInfo(Integer curUser);

    List<Map> listConfig(Map<String, Object> params);

    void addConfig(Map<String, Object> params);

    void updateConfig(Map<String, Object> params);

    void validConfig(Integer id);

    List<Map> getEditUsers(@Param(value = "editUsers") String editUsers);
}
