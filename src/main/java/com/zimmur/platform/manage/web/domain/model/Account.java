package com.zimmur.platform.manage.web.domain.model;

import java.util.Date;

public class Account {
    private Integer id;

    private String loginName;

    private String password;

    private String nickname;

    private Date createTime;

    private Date lastLoginTime;

    private Byte status;
    
    public Account(){
    	
    }
    public Account(Account account){
    	this.setId(account.getId());
    	this.setLoginName(account.getLoginName());
    	this.setNickname(account.getNickname());
    	this.setPassword(account.getPassword());
    	this.setCreateTime(account.getCreateTime());
    	this.setLastLoginTime(account.getLastLoginTime());
    	this.setStatus(account.getStatus());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}