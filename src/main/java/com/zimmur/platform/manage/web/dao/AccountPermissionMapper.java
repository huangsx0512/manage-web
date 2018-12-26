package com.zimmur.platform.manage.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zimmur.platform.manage.web.domain.model.AccountPermission;
import com.zimmur.platform.manage.web.domain.model.AccountPermissionExample;

public interface AccountPermissionMapper {
    int countByExample(AccountPermissionExample example);

    int deleteByExample(AccountPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountPermission record);

    int insertSelective(AccountPermission record);

    List<AccountPermission> selectByExample(AccountPermissionExample example);

    AccountPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountPermission record, @Param("example") AccountPermissionExample example);

    int updateByExample(@Param("record") AccountPermission record, @Param("example") AccountPermissionExample example);

    int updateByPrimaryKeySelective(AccountPermission record);

    int updateByPrimaryKey(AccountPermission record);
}