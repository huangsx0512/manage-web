package com.zimmur.platform.manage.web.shiro.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zimmur.platform.manage.web.domain.model.Account;
import com.zimmur.platform.manage.web.service.IAccountPermissionService;
import com.zimmur.platform.manage.web.service.IAccountRoleService;
import com.zimmur.platform.manage.web.service.IAccountService;
import com.zimmur.platform.manage.web.util.DateUtils;

public class MyRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(MyRealm.class);
	@Autowired
	private IAccountService accountSubjectServiceImpl;
	@Autowired
	private IAccountRoleService accountRoleService;
	@Autowired
	private IAccountPermissionService accountPermissionService;
	
	public MyRealm() {
		super();
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) 
			throws AuthenticationException{
		Account user =(Account)principals.getPrimaryPrincipal();
		if(user==null){
			throw new AccountException("用户未登录！");
		}
		Integer userId = user.getId();
		log.debug("给用户‘{}’授权",userId);
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
		Set<String> roles = accountRoleService.findRoleByAccountId(userId);
		info.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions = accountPermissionService.findPermissionByaccountId(userId);
		info.setStringPermissions(permissions);
        return info;  
	}

	/**
	 * 认证信息，主要针对用户登录，
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		ShiroToken token = (ShiroToken) authcToken;
		Account user = accountSubjectServiceImpl.login(token.getUsername(), token.getPswd());
		if (null == user) {
			throw new AccountException("帐号或密码不正确！");
			/**
			 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
			 */
		} else if (user.getStatus() != 1) {
			throw new DisabledAccountException("帐号已经禁止登录！");
		} else {
			// 更新登录时间 last login time
			user.setLastLoginTime(DateUtils.getNow());
			accountSubjectServiceImpl.updateByPrimaryKeySelective(user);
		}
		AuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(),getName());
		return info;
	}

	/**
	 * 清空当前用户权限信息
	 */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
