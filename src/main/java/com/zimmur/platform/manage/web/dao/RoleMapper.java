package com.zimmur.platform.manage.web.dao;

import com.zimmur.platform.manage.web.domain.model.Role;
import com.zimmur.platform.manage.web.domain.model.RoleExample;
import com.zimmur.platform.manage.web.domain.vo.RoleInfoVo;
import com.zimmur.platform.manage.web.domain.vo.RoleVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	/**
	* <p>Title: queryRoleInfoByAppId</p>
	* <p>Description: </p>
	* @param appId
	* @return
	*/
	List<RoleVo> queryRoleInfoByAppId(@Param("appId")Integer appId);

	/**
	* <p>Title: countByParam</p>
	* <p>Description:查询角色信息总数量 </p>
	* @param keyword
	* @return
	*/
	int countByParam(@Param("keyword")String keyword);

	/**
	* <p>Title: queryRoleInfo</p>
	* <p>Description: 查询角色信息(以及角色的权限)</p>
	* @param pageNo
	* @param pageSize
	* @param keyword
	* @return
	*/
	List<RoleInfoVo> queryRoleInfo(@Param("pageBeginNo")Integer pageBeginNo, @Param("pageSize")Integer pageSize, @Param("keyword")String keyword);
}