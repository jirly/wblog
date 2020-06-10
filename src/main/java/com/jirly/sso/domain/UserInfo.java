package com.jirly.sso.domain;

public class UserInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403288301909657633L;
	private String id;
	private String name;
	private String loginName;
	private String showName;
	private String departmentID;
	private String departmentName;
	private String loginURL;
	private String workStateID;  //当前工作状态记录ID
	private int loginID;
	private int companyID;    //公司ID
	private boolean agent = false;  //是否代理厂商执行操作
	private int srcCompanyID;  //原公司ID

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getWorkStateID() {
		return workStateID;
	}
	public void setWorkStateID(String workStateID) {
		this.workStateID = workStateID;
	}
	public String getLoginURL() {
		return loginURL;
	}
	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}
	public int getLoginID() {
		return loginID;
	}
	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public boolean isAgent() {
		return agent;
	}
	public void setAgent(boolean agent) {
		this.agent = agent;
	}
	public int getSrcCompanyID() {
		return srcCompanyID;
	}
	public void setSrcCompanyID(int srcCompanyID) {
		this.srcCompanyID = srcCompanyID;
	}
}
