package com.jirly.sso.service;



import com.jirly.sso.domain.QueryParameters;
import com.jirly.sso.domain.WblogUpvote;

import java.util.List;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
public interface WblogUpvoteService {
    void addUpvote(WblogUpvote upvote);

    void delUpvote(WblogUpvote upvote);

    List<WblogUpvote> list(QueryParameters qp);
}
