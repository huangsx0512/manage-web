package com.zimmur.platform.manage.web.dao;

import com.zimmur.platform.manage.web.domain.model.AppRole;
import com.zimmur.platform.manage.web.domain.model.AppRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppRoleMapper {
    int countByExample(AppRoleExample example);

    int deleteByExample(AppRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppRole record);

    int insertSelective(AppRole record);

    List<AppRole> selectByExample(AppRoleExample example);

    AppRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppRole record, @Param("example") AppRoleExample example);

    int updateByExample(@Param("record") AppRole record, @Param("example") AppRoleExample example);

    int updateByPrimaryKeySelective(AppRole record);

    int updateByPrimaryKey(AppRole record);
}