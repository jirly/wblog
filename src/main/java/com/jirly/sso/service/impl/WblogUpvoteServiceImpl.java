package com.jirly.sso.service.impl;


import com.jirly.sso.domain.QueryParameters;
import com.jirly.sso.domain.WblogUpvote;
import com.jirly.sso.mapper.WblogUpvoteMapper;
import com.jirly.sso.service.WblogUpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
@Service
public class WblogUpvoteServiceImpl implements WblogUpvoteService {
    @Autowired
    private WblogUpvoteMapper mapper;

    @Override
    public void addUpvote(WblogUpvote upvote) {
        mapper.insert(upvote);
    }

    @Override
    public void delUpvote(WblogUpvote upvote) {
        mapper.deleteById(upvote);
    }

    @Override
    public List<WblogUpvote> list(QueryParameters qp) {
        return mapper.findResults(qp);
    }
}
