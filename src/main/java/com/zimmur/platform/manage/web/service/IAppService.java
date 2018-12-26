package com.zimmur.platform.manage.web.service;

import java.util.List;

import com.zimmur.platform.manage.web.domain.model.App;
import com.zimmur.platform.manage.web.domain.model.AppExample;
import com.zimmur.platform.manage.web.domain.model.Role;
import com.zimmur.platform.manage.web.domain.vo.AppVo;
import com.zimmur.platform.manage.web.util.PageUtil;
public interface IAppService extends IBaseService<App>{

	List<App> readByExample(AppExample example);

	int grandRoleForApp(Integer appId, Integer roleId);

	List<Role> queryAppRoles(Integer appId);
	
	PageUtil<AppVo> queryAppInfo(Integer pageNo,Integer pageSize,String keyword);

	//List<App> selectByMuchparams(AppInfoParam appInfoParam);

	AppVo queryAppVo(Integer appId);

}