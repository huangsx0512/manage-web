package com.zimmur.platform.manage.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zimmur.platform.manage.web.domain.model.AccountRolePermission;
import com.zimmur.platform.manage.web.domain.model.AccountRolePermissionExample;

public interface AccountRolePermissionMapper {
    int countByExample(AccountRolePermissionExample example);

    int deleteByExample(AccountRolePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountRolePermission record);

    int insertSelective(AccountRolePermission record);

    List<AccountRolePermission> selectByExample(AccountRolePermissionExample example);

    AccountRolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountRolePermission record, @Param("example") AccountRolePermissionExample example);

    int updateByExample(@Param("record") AccountRolePermission record, @Param("example") AccountRolePermissionExample example);

    int updateByPrimaryKeySelective(AccountRolePermission record);

    int updateByPrimaryKey(AccountRolePermission record);
}