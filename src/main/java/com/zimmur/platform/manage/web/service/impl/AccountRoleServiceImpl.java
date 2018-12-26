package com.zimmur.platform.manage.web.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zimmur.platform.manage.web.dao.AccountRoleMapper;
import com.zimmur.platform.manage.web.service.IAccountRoleService;

@Service
public class AccountRoleServiceImpl implements IAccountRoleService{
	@Autowired
	private AccountRoleMapper accountRoleMapper;
	@Override
	public Set<String> findRoleByAccountId(Integer accountId) {
		// TODO Auto-generated method stub
		return null;
	}

}
