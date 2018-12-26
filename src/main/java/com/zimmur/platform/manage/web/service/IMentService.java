package com.zimmur.platform.manage.web.service;

import java.util.List;

import com.zimmur.platform.manage.web.domain.model.Ment;
import com.zimmur.platform.manage.web.domain.model.MentExample;
import com.zimmur.platform.manage.web.domain.vo.MentVo;
import com.zimmur.platform.manage.web.util.PageUtil;
public interface IMentService extends IBaseService<Ment>{
	List<Ment> readByExample(MentExample example);

	/**
	* <p>Title: queryRoleInfoByAppId</p>
	* <p>Description:查询角色权限信息 </p>
	* @param roleId
	* @return
	*/
	List<MentVo> queryMentInfoByRoleId(Integer roleId);

	/**
	* <p>Title: queryMentInfo</p>
	* <p>Description: </p>
	* @param pageNo
	* @param pageSize
	* @param keyword
	* @return
	*/
	PageUtil<Ment> queryMentInfo(Integer pageNo, Integer pageSize, String keyword);
}