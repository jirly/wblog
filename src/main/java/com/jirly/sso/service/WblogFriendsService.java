package com.jirly.sso.service;


import com.jirly.sso.domain.PageRequest;
import com.jirly.sso.domain.WblogFriends;

import java.util.List;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
public interface WblogFriendsService {
    List<Map<String,Object>> listUnAttUserList(PageRequest pageRequest);

    void addFriends(WblogFriends friends);

    List<Integer> getAttCount(Integer curUser);

    List<Map<String,Object>> listAttUserList(PageRequest pageRequest);

    void delFriends(WblogFriends friends);
}
