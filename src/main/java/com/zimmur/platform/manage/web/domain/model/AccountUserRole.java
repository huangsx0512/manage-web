package com.zimmur.platform.manage.web.domain.model;

import java.io.Serializable;

public class AccountUserRole implements Serializable {
    private Integer id;

    private Integer accountId;

    private Integer accountRoleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountRoleId() {
        return accountRoleId;
    }

    public void setAccountRoleId(Integer accountRoleId) {
        this.accountRoleId = accountRoleId;
    }
}