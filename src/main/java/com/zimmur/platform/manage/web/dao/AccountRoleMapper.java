package com.zimmur.platform.manage.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zimmur.platform.manage.web.domain.model.AccountRole;
import com.zimmur.platform.manage.web.domain.model.AccountRoleExample;

public interface AccountRoleMapper {
    int countByExample(AccountRoleExample example);

    int deleteByExample(AccountRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountRole record);

    int insertSelective(AccountRole record);

    List<AccountRole> selectByExample(AccountRoleExample example);

    AccountRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountRole record, @Param("example") AccountRoleExample example);

    int updateByExample(@Param("record") AccountRole record, @Param("example") AccountRoleExample example);

    int updateByPrimaryKeySelective(AccountRole record);

    int updateByPrimaryKey(AccountRole record);
}