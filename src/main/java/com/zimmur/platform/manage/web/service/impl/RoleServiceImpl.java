package com.zimmur.platform.manage.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zimmur.platform.manage.web.dao.MentMapper;
import com.zimmur.platform.manage.web.dao.RoleMapper;
import com.zimmur.platform.manage.web.dao.RoleMentMapper;
import com.zimmur.platform.manage.web.domain.model.Ment;
import com.zimmur.platform.manage.web.domain.model.MentExample;
import com.zimmur.platform.manage.web.domain.model.Role;
import com.zimmur.platform.manage.web.domain.model.RoleExample;
import com.zimmur.platform.manage.web.domain.model.RoleMent;
import com.zimmur.platform.manage.web.domain.model.RoleMentExample;
import com.zimmur.platform.manage.web.domain.vo.AppVo;
import com.zimmur.platform.manage.web.domain.vo.RoleInfoVo;
import com.zimmur.platform.manage.web.domain.vo.RoleVo;
import com.zimmur.platform.manage.web.service.IRoleService;
import com.zimmur.platform.manage.web.util.PageUtil;
@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMentMapper roleMentMapper;
	@Autowired
	private MentMapper mentMapper;
	
	@Override
	public int add(Role role) {
		int flag = roleMapper.insertSelective(role);
		return role.getId();
	}

	@Override
	public int update(Role t) {
		int flag =roleMapper.updateByPrimaryKeySelective(t);
		return flag;
	}
	@Override
	public int delete(Integer id) {
		//1.查询角色权限关系表的记录
		RoleMentExample example = new RoleMentExample();
		example.createCriteria().andRoleIdEqualTo(id);
		List<RoleMent> ments = roleMentMapper.selectByExample(example);
		if(ments!=null&&!ments.isEmpty()){
			//2.删除角色权限关系表的记录
			roleMentMapper.deleteByExample(example);
		}
		//3.删除role记录
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Role> readByExample(RoleExample example) {
		List<Role> roles = roleMapper.selectByExample(example);
		return roles;
	}
	@Override
	public Role readById(Integer id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		return role;
	}
	/**
	 * 
	 * 赋予角色权限
	 */
	@Transactional
	@Override
	public int grandAuthorityForRole(Integer roleId, Integer mentId) {
		//1.查询是否已经绑定角色权限
		RoleMentExample roleMentExample = new RoleMentExample();
		roleMentExample.createCriteria().andRoleIdEqualTo(roleId).andMentIdEqualTo(mentId);
		List<RoleMent> roleMents = roleMentMapper.selectByExample(roleMentExample);
		if(roleMents!=null&&!roleMents.isEmpty()){
			//删除原赋予的权限
			RoleMentExample example = new RoleMentExample();
			example.or().andRoleIdEqualTo(roleId);
			roleMentMapper.deleteByExample(example);
		}
		//2.赋予角色权限
		RoleMent roleMent = new RoleMent();
		roleMent.setMentId(mentId);
		roleMent.setRoleId(roleId);
		return roleMentMapper.insertSelective(roleMent);
	}
	/**
	 * 赋予角色权限
	 */
	@Override
	public List<Ment> queryRoleAuthorities(Integer roleId) {
		//1.查询角色权限关系表
		List<Ment> roleMetns = new ArrayList<Ment>();
		RoleMentExample roleMentExample = new RoleMentExample();
		roleMentExample.createCriteria().andRoleIdEqualTo(roleId);
		List<RoleMent> roleMents = roleMentMapper.selectByExample(roleMentExample);
		if(roleMents!=null&&!roleMents.isEmpty()){
			for(RoleMent a:roleMents){
				if(a.getRoleId()!=null&&a.getRoleId()>0){
					//2.查询权限表
					MentExample example = new MentExample();
					example.createCriteria().andIdEqualTo(a.getMentId());
					List<Ment> ments = mentMapper.selectByExample(example);
					//3.
					roleMetns.addAll(ments);
				}
			}
		}
		return roleMetns;
	}

	/* (non-Javadoc)
	 * @see com.zimmur.platform.manage.web.service.IRoleService#queryRoleInfoByAppId(java.lang.Integer)
	 */
	@Override
	public List<RoleVo> queryRoleInfoByAppId(Integer appId) {
		return roleMapper.queryRoleInfoByAppId(appId);
	}

	/* (non-Javadoc)
	 * @see com.zimmur.platform.manage.web.service.IRoleService#queryRoleInfo(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public PageUtil<RoleInfoVo> queryRoleInfo(Integer pageNo, Integer pageSize, String keyword) {
		PageUtil<RoleInfoVo> page = new PageUtil<RoleInfoVo>();
		List<RoleInfoVo> roleInfoList = new ArrayList<RoleInfoVo>();
		//查询统计数据
		int count = roleMapper.countByParam(keyword);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		//查询角色信息
		roleInfoList = roleMapper.queryRoleInfo(page.getPageBeginNo(),pageSize,keyword);
		page.setItemList(roleInfoList);
		page.setTotalRecord(count);
		return page;
	}
}
