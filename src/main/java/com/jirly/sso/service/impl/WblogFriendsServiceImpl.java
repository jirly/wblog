package com.jirly.sso.service.impl;


import com.jirly.sso.domain.PageRequest;
import com.jirly.sso.domain.WblogFriends;
import com.jirly.sso.mapper.WblogFriendsMapper;
import com.jirly.sso.service.WblogFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
@Service
public class WblogFriendsServiceImpl implements WblogFriendsService {
    @Autowired
    private WblogFriendsMapper mapper;

    @Override
    public List<Map<String, Object>> listUnAttUserList(PageRequest pageRequest) {
        return mapper.listUnAttUserList(pageRequest);
    }

    @Override
    public void addFriends(WblogFriends friends) {
        mapper.insert(friends);
    }

    @Override
    public List<Integer> getAttCount(Integer curUser) {
        return mapper.getAttCount(curUser);
    }

    @Override
    public List<Map<String, Object>> listAttUserList(PageRequest pageRequest) {
        return mapper.listAttUserList(pageRequest);
    }

    @Override
    public void delFriends(WblogFriends friends) {
        mapper.delete(friends);
    }
}
