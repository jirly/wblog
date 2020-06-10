package com.jirly.sso.mapper;



import com.jirly.sso.domain.QueryParameters;
import com.jirly.sso.domain.WblogRemark;

import java.util.List;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/30
 */
public interface WblogRemarkMapper {
    void insert(WblogRemark remark);
    List<WblogRemark> findResults(QueryParameters qp);

    void deleteById(Integer remarkId);
}
