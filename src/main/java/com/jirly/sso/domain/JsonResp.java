/*
 * Copyright (c) 2015 by duowan Wireless Technology Co., Ltd.
 *             All rights reserved                         
 */
package com.jirly.sso.domain;

import org.springframework.util.Assert;


public class JsonResp {

	public static final int SUCCESS_STATUS = 0;

	public static final int DEFAULT_FAIL_STATUS = -1;

	/** 0-成功，其它-失败 */
	private int status;

	private Object data;

	private String errorMsg;

	public JsonResp(int status, Object data) {
		this.status = status;
		this.data = data;
	}

	public JsonResp(int status, Object data, String errorMsg) {
		this.status = status;
		this.data = data;
		this.errorMsg = errorMsg;
	}

	public static JsonResp success() {
		return new JsonResp(SUCCESS_STATUS, null);
	}

	public static JsonResp success(Object data) {
		return new JsonResp(SUCCESS_STATUS, data);
	}

	public static JsonResp fail() {
		return fail("系统错误，请联系系统管理员！");
	}

	public static JsonResp fail(String errorMsg) {
		return fail(DEFAULT_FAIL_STATUS, errorMsg);
	}

	public static JsonResp fail(int status, String errorMsg) {
		Assert.isTrue(status != SUCCESS_STATUS, "Must be not success status: " + status);
		return new JsonResp(status, null, errorMsg);
	}

	public static JsonResp fail(int status, Object data, String errorMsg) {
		Assert.isTrue(status != SUCCESS_STATUS, "Must be not success status: " + status);
		return new JsonResp(status, data, errorMsg);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
