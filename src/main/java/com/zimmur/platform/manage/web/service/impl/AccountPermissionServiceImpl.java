package com.zimmur.platform.manage.web.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zimmur.platform.manage.web.dao.AccountPermissionMapper;
import com.zimmur.platform.manage.web.service.IAccountPermissionService;
@Service
public class AccountPermissionServiceImpl implements IAccountPermissionService{

	@Autowired
	private AccountPermissionMapper accountPermissionMapper;
	
	@Override
	public Set<String> findPermissionByaccountId(Integer accountId) {
		// TODO Auto-generated method stub
		return null;
	}

}
