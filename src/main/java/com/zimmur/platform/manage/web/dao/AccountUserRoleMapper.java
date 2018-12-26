package com.zimmur.platform.manage.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zimmur.platform.manage.web.domain.model.AccountUserRole;
import com.zimmur.platform.manage.web.domain.model.AccountUserRoleExample;

public interface AccountUserRoleMapper {
    int countByExample(AccountUserRoleExample example);

    int deleteByExample(AccountUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountUserRole record);

    int insertSelective(AccountUserRole record);

    List<AccountUserRole> selectByExample(AccountUserRoleExample example);

    AccountUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountUserRole record, @Param("example") AccountUserRoleExample example);

    int updateByExample(@Param("record") AccountUserRole record, @Param("example") AccountUserRoleExample example);

    int updateByPrimaryKeySelective(AccountUserRole record);

    int updateByPrimaryKey(AccountUserRole record);
}