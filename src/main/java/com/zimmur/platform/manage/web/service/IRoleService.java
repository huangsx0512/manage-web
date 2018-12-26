package com.zimmur.platform.manage.web.service;

import java.util.List;

import com.zimmur.platform.manage.web.domain.model.Ment;
import com.zimmur.platform.manage.web.domain.model.Role;
import com.zimmur.platform.manage.web.domain.model.RoleExample;
import com.zimmur.platform.manage.web.domain.vo.RoleInfoVo;
import com.zimmur.platform.manage.web.domain.vo.RoleVo;
import com.zimmur.platform.manage.web.util.PageUtil;
public interface IRoleService extends IBaseService<Role>{
	List<Role> readByExample(RoleExample example);

	int grandAuthorityForRole(Integer roleId, Integer mentId);
	
	List<Ment> queryRoleAuthorities(Integer roleId);

	/**
	* <p>Title: queryRoleInfoByAppId</p>
	* <p>Description:根据应用id查询角色信息(角色是否被应用选中) </p>
	* @param appId
	* @return
	*/
	List<RoleVo> queryRoleInfoByAppId(Integer appId);

	/**
	* <p>Title: queryRoleInfo</p>
	* <p>Description: </p>
	* @param pageNo
	* @param pageSize
	* @param keyword
	* @return
	*/
	PageUtil<RoleInfoVo> queryRoleInfo(Integer pageNo, Integer pageSize, String keyword);
}