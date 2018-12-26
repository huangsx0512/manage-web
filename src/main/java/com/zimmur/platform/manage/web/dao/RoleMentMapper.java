package com.zimmur.platform.manage.web.dao;

import com.zimmur.platform.manage.web.domain.model.RoleMent;
import com.zimmur.platform.manage.web.domain.model.RoleMentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMentMapper {
    int countByExample(RoleMentExample example);

    int deleteByExample(RoleMentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleMent record);

    int insertSelective(RoleMent record);

    List<RoleMent> selectByExample(RoleMentExample example);

    RoleMent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleMent record, @Param("example") RoleMentExample example);

    int updateByExample(@Param("record") RoleMent record, @Param("example") RoleMentExample example);

    int updateByPrimaryKeySelective(RoleMent record);

    int updateByPrimaryKey(RoleMent record);
}