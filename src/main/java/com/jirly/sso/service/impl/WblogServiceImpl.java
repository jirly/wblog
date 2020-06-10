package com.jirly.sso.service.impl;

import com.jirly.sso.domain.PageRequest;
import com.jirly.sso.domain.Wblog;
import com.jirly.sso.mapper.WblogMapper;
import com.jirly.sso.service.WblogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/29
 */
@Service
public class WblogServiceImpl implements WblogService {
    private static Logger logger = Logger.getLogger(WblogServiceImpl.class);

    @Autowired
    private WblogMapper wblogMapper;

    @Override
    public void addBlog(Wblog wblog) {
        wblogMapper.insert(wblog);
    }

    @Override
    public List<Wblog> list(PageRequest parameters) {
        return wblogMapper.findResults(parameters);
    }

    @Override
    public int count(PageRequest queryParameters) {
        return wblogMapper.findResultCount(queryParameters);
    }

    @Override
    public void delBlog(Integer blogId) {
        wblogMapper.deleteById(blogId);
    }

    @Override
    public void saveOrUpdateUserPic(Integer userId, String s) {
        wblogMapper.saveOrUpdateUserPic(userId,s);
    }

    @Override
    public String getUserPic(Integer curUser) {
        return wblogMapper.getUserPic(curUser);
    }

    @Override
    public Map<String, String> getUserOtherInfo(Integer curUser) {
        return wblogMapper.getUserOtherInfo(curUser);
    }

    @Override
    public List<Map> listConfig(Map<String, Object> params) {
        return wblogMapper.listConfig(params);
    }

    @Override
    public void addConfig(Map<String, Object> params) {
        wblogMapper.addConfig(params);
    }

    @Override
    public void updateConfig(Map<String, Object> params) {
        wblogMapper.updateConfig(params);
    }

    @Override
    public void validConfig(Integer id) {
        wblogMapper.validConfig(id);
    }


    @Override
    public List<Map> getEditUsers(String editUsers) {
        return wblogMapper.getEditUsers(editUsers);
    }
}
