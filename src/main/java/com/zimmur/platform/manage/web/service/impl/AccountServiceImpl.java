package com.zimmur.platform.manage.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zimmur.platform.manage.web.dao.AccountMapper;
import com.zimmur.platform.manage.web.domain.model.Account;
import com.zimmur.platform.manage.web.domain.model.AccountExample;
import com.zimmur.platform.manage.web.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{
	@Autowired
	private AccountMapper accountMapper;
	@Override
	public Account login(String loginName, String password) {
		AccountExample example = new AccountExample();
		example.setOrderByClause(" id asc");
		example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
		List<Account> accounts=accountMapper.selectByExample(example);
		if(accounts!=null && accounts.size()>0){
			return accounts.get(0);
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Account account) {
		return accountMapper.updateByPrimaryKeySelective(account);
	}

}
