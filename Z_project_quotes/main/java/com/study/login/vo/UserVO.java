package com.study.login.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserVO {
	private String userId;
	private String userName;
	private String userPass;
	private String userRole;
	private String userImg;
	
	public UserVO() {
		
	}
	
	public UserVO(String userId, String userName, String userPass, String userRole, String userImg) {
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.userRole = userRole;
		this.userImg = userImg;
	}
	
	
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this,ToStringStyle.MULTI_LINE_STYLE);
	}

	
	
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	
	
	
	
	
}


