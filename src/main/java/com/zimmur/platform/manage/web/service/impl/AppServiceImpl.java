package com.zimmur.platform.manage.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zimmur.platform.manage.web.dao.AppMapper;
import com.zimmur.platform.manage.web.dao.AppRoleMapper;
import com.zimmur.platform.manage.web.dao.MentMapper;
import com.zimmur.platform.manage.web.dao.RoleMapper;
import com.zimmur.platform.manage.web.dao.RoleMentMapper;
import com.zimmur.platform.manage.web.domain.model.App;
import com.zimmur.platform.manage.web.domain.model.AppExample;
import com.zimmur.platform.manage.web.domain.model.AppRole;
import com.zimmur.platform.manage.web.domain.model.AppRoleExample;
import com.zimmur.platform.manage.web.domain.model.Role;
import com.zimmur.platform.manage.web.domain.model.RoleExample;
import com.zimmur.platform.manage.web.domain.vo.AppVo;
import com.zimmur.platform.manage.web.service.IAppService;
import com.zimmur.platform.manage.web.util.PageUtil;
@Service
public class AppServiceImpl implements IAppService{

	@Autowired
	private AppMapper appMapper;
	@Autowired
	private AppRoleMapper appRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMentMapper roleMentMapper;
	@Autowired
	private MentMapper mentMapper;
	@Override
	public int add(App zmApp) {
		appMapper.insertSelective(zmApp);
		return zmApp.getId();//返回主键id
	}

	@Override
	public int update(App t) {
		int flag =appMapper.updateByPrimaryKeySelective(t);
		return flag;
	}
	@Override
	public int delete(Integer id) {
		//1.查询用户角色关系表的记录
		AppRoleExample example = new AppRoleExample();
		example.createCriteria().andAppIdEqualTo(id);
		List<AppRole> roles = appRoleMapper.selectByExample(example);
		if(roles!=null&&!roles.isEmpty()){
			//2.删除用户角色关系表的记录
			appRoleMapper.deleteByExample(example);
		}
		//3.删除用户信息
		int flag =appMapper.deleteByPrimaryKey(id);
		return flag;
	}

	@Override
	public List<App> readByExample(AppExample example) {
		List<App> apps = appMapper.selectByExample(example);
		return apps;
	}
	@Override
	public App readById(Integer id) {
		App app = appMapper.selectByPrimaryKey(id);
		return app;
	}
	/**
	 * 赋予角色
	 * 
	 */
	@Transactional
	@Override
	public int grandRoleForApp(Integer appId, Integer roleId) {
		//1.查询是否已经给此应用赋予了角色
		AppRoleExample appRoleExample = new AppRoleExample();
		appRoleExample.createCriteria().andAppIdEqualTo(appId).andRoleIdEqualTo(roleId);
		List<AppRole> appRoles = appRoleMapper.selectByExample(appRoleExample);
		if(appRoles!=null&&!appRoles.isEmpty()){
			//删除原赋予的角色信息
			AppRoleExample example = new AppRoleExample();
			example.or().andAppIdEqualTo(appId);
			appRoleMapper.deleteByExample(example);
		}
		//2.赋予角色，插入用户角色关系表
		AppRole appRole = new AppRole();
		appRole.setAppId(appId);
		appRole.setRoleId(roleId);
		return appRoleMapper.insertSelective(appRole);
	}
	/**
	 * 查询用户具有的角色
	 */
	@Override
	public List<Role> queryAppRoles(Integer appId) {
		//1.查询角色关系表
		List<Role> entireRoles = new ArrayList<Role>();
		AppRoleExample appRoleExample = new AppRoleExample();
		appRoleExample.createCriteria().andAppIdEqualTo(appId);
		List<AppRole> appRoles = appRoleMapper.selectByExample(appRoleExample);
		if(appRoles!=null&&!appRoles.isEmpty()){
			for(AppRole a:appRoles){
				if(a.getRoleId()!=null&&a.getRoleId()>0){
					//2.查询角色表
					RoleExample example = new RoleExample();
					example.createCriteria().andIdEqualTo(a.getRoleId());
					List<Role> roles = roleMapper.selectByExample(example);
					//3.
					entireRoles.addAll(roles);
				}
			}
		}
		return entireRoles;
	}
	/*@Override
	public List<App> selectByMuchparams(AppInfoParam appInfoParam) {
		List<App> list = appMapper.selectByMuchParam(appInfoParam);//查询应用信息
		return list;
	}*/
	//@Override
	/*public AppVo queryAppVo(Integer appId) {
		AppVo appVo = new AppVo();
		App app = appMapper.selectByPrimaryKey(appId);
		if(app == null){
			return null;
		}
		appVo.setAppKey(app.getAppKey());
		appVo.setId(appId);
		appVo.setIsEnabled(app.getIsEnabled());
		appVo.setIsIp(app.getIsIp());
		if(app.getIsIp().equals(ConstData.APP_IP_OPEN) && !StringUtil.isEmpty(app.getIps())){
			String[] ips = app.getIps().split(",");
			Map<String,Integer> ipMap=new HashMap<>();
			for(String ip : ips){
				ipMap.put(ip, 1);
			}
			appVo.setIpMap(ipMap);
		}
		List<Integer> roleIds = appRoleMapper.selectRoleByAppId(appId);
		if(roleIds == null || roleIds.size()==0 ){
			return appVo;
		}
		roleIds = roleMapper.selectRoleIds(roleIds);
		if(roleIds == null || roleIds.size()==0 ){
			return appVo;
		}
		List<Integer> mentIds = roleMentMapper.selectMentByRoleId(roleIds);
		if(mentIds == null || mentIds.size()==0 ){
			return appVo;
		}
		List<Ment> ments=mentMapper.selectMents(mentIds);
		if(ments == null || ments.size()==0 ){
			return appVo;
		}
		Map<String,Ment> mentMap = new HashMap<>();
		for(Ment ment:ments){
			String key = "/"+ment.getApiServer()+ment.getUrl();
			mentMap.put(key, ment);
		}
		appVo.setMentMap(mentMap);
		return appVo;
	}*/

	/* (non-Javadoc)
	 * @see com.zimmur.platform.manage.web.service.IAppService#queryAppVo(java.lang.Integer)
	 */
	@Override
	public PageUtil<AppVo> queryAppInfo(Integer pageNo, Integer pageSize, String keyword) {
		PageUtil<AppVo> page = new PageUtil<AppVo>();
		List<AppVo> appInfoList = new ArrayList<AppVo>();
		//查询统计数据
		int count = appMapper.countByParam(keyword);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		//查询应用信息
		appInfoList = appMapper.queryAppInfo(page.getPageBeginNo(),pageSize,keyword);
		page.setItemList(appInfoList);
		page.setTotalRecord(count);
		return page;
	}
	@Override
	public AppVo queryAppVo(Integer appId) {
		// TODO Auto-generated method stub
		return null;
	}
}
