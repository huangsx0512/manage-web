package com.zimmur.platform.manage.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zimmur.platform.manage.web.dao.MentMapper;
import com.zimmur.platform.manage.web.domain.model.Ment;
import com.zimmur.platform.manage.web.domain.model.MentExample;
import com.zimmur.platform.manage.web.domain.vo.MentVo;
import com.zimmur.platform.manage.web.domain.vo.RoleInfoVo;
import com.zimmur.platform.manage.web.service.IMentService;
import com.zimmur.platform.manage.web.util.PageUtil;
@Service
public class MentServiceImpl implements IMentService{

	@Autowired
	private MentMapper mentMapper;
	
	@Override
	public int add(Ment ment) {
		int flag = mentMapper.insertSelective(ment);
		return flag;
	}

	@Override
	public int update(Ment t) {
		int flag =mentMapper.updateByPrimaryKeySelective(t);
		return flag;
	}
	@Override
	public int delete(Integer id) {
		int flag =mentMapper.deleteByPrimaryKey(id);
		return flag;
	}

	@Override
	public List<Ment> readByExample(MentExample example) {
		List<Ment> ments = mentMapper.selectByExample(example);
		return ments;
	}
	@Override
	public Ment readById(Integer id) {
		Ment ment = mentMapper.selectByPrimaryKey(id);
		return ment;
	}

	/*
	 * 
	 */
	@Override
	public List<MentVo> queryMentInfoByRoleId(Integer roleId) {
		return mentMapper.queryMentInfoByRoleId(roleId);
	}

	/**
	 * 综合检索权限信息
	 */
	@Override
	public PageUtil<Ment> queryMentInfo(Integer pageNo, Integer pageSize, String keyword) {
		PageUtil<Ment> page = new PageUtil<Ment>();
		List<Ment> mentInfoList = new ArrayList<Ment>();
		//查询统计数据
		int count = mentMapper.countByParam(keyword);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		//查询角色信息
		mentInfoList = mentMapper.queryMentInfo(page.getPageBeginNo(),pageSize,keyword);
		page.setItemList(mentInfoList);
		page.setTotalRecord(count);
		return page;
	}
}
