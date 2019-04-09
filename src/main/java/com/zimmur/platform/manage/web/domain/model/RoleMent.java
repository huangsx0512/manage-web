package com.zimmur.platform.manage.web.domain.model;

import java.io.Serializable;

public class RoleMent implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer mentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMentId() {
        return mentId;
    }

    public void setMentId(Integer mentId) {
        this.mentId = mentId;
    }
}