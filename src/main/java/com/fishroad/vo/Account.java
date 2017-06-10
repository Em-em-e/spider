package com.fishroad.vo;

import java.util.Date;

public class Account {
    private Integer id;

    private String platform;

    private String username;

    private String password;

    private String emailPassword;

    private Integer allotUser;

    private String loginCookie;

    private Date lastLoginTime;

    private String remark1;

    private String remark2;
    
    private String allotUserQuery;
    
    private int count;

    public String getAllotUserQuery() {
		return allotUserQuery;
	}

	public void setAllotUserQuery(String allotUserQuery) {
		this.allotUserQuery = allotUserQuery;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword == null ? null : emailPassword.trim();
    }

    public Integer getAllotUser() {
        return allotUser;
    }

    public void setAllotUser(Integer allotUser) {
        this.allotUser = allotUser;
    }

    public String getLoginCookie() {
        return loginCookie;
    }

    public void setLoginCookie(String loginCookie) {
        this.loginCookie = loginCookie == null ? null : loginCookie.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
}