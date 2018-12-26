package com.zimmur.platform.manage.web.service;

import com.zimmur.platform.manage.web.domain.model.Account;

public interface IAccountService {
	/**
	 * 登录验证
	 * @param loginName
	 * @param password
	 * @return 成功返回用户信息，失败返回null
	 */
	Account login(String loginName, String password);
	/**
	 * 更新用户信息
	 * @param account
	 * @return
	 */
	int updateByPrimaryKeySelective(Account account);
	
	//PageUtil<AccountVo> 
}
