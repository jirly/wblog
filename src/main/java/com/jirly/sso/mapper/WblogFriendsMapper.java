package com.jirly.sso.mapper;


import com.jirly.sso.domain.PageRequest;
import com.jirly.sso.domain.WblogFriends;

import java.util.List;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
public interface WblogFriendsMapper {
    List<Map<String,Object>> listUnAttUserList(PageRequest pageRequest);

    void insert(WblogFriends friends);

    List<Integer> getAttCount(Integer curUser);

    List<Map<String,Object>> listAttUserList(PageRequest pageRequest);

    void delete(WblogFriends friends);
}
