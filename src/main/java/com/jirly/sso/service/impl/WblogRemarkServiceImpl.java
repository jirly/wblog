package com.jirly.sso.service.impl;


import com.jirly.sso.domain.QueryParameters;
import com.jirly.sso.domain.WblogRemark;
import com.jirly.sso.mapper.WblogRemarkMapper;
import com.jirly.sso.service.WblogRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
@Service
public class WblogRemarkServiceImpl implements WblogRemarkService {
    @Autowired
    private WblogRemarkMapper mapper;

    @Override
    public void addRemark(WblogRemark remark) {
        mapper.insert(remark);
    }

    @Override
    public List<WblogRemark> list(QueryParameters qp) {
        return mapper.findResults(qp);
    }

    @Override
    public void delRemark(Integer remarkId) {
        mapper.deleteById(remarkId);
    }
}
