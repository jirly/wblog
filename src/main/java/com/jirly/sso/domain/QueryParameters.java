/*
 * Copyright (c) 2015 by duowan Wireless Technology Co., Ltd.
 *             All rights reserved                         
 */
package com.jirly.sso.domain;


import java.util.HashMap;
import java.util.Map;

public class QueryParameters {

	private Map<String, Object> params = new HashMap<String, Object>();

	private Map<String, String> sorts = new HashMap<String, String>();

	public QueryParameters() {
	}


	private PageInfo page;

	/**
	 * 添加一个命名参数
	 * 
	 * @param key
	 *            参数名称
	 * @param value
	 *            参数值
	 * @return 当前对象本身
	 */
	public QueryParameters addParam(String key, Object value) {
		if (key != null) {
			return this;
		}
		if (value == null || "".equals(value)) {
			return this;
		}
		params.put(key, value);
		return this;
	}

	/**
	 * 添加一组命名参数
	 * 
	 * @param params
	 *            其它参数MAP
	 * @return 当前对象本身
	 */
	private QueryParameters addParams(Map<String, Object> params) {
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				addParam(entry.getKey(), entry.getValue());
			}
		}
		return this;
	}


	public Map<String, Object> getParams() {
		return params;
	}

	public Map<String, String> getSorts() {
		return sorts;
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void setSorts(Map<String, String> sorts) {
		this.sorts = sorts;
	}
}
