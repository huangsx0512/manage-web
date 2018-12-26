package com.zimmur.platform.manage.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zimmur.platform.manage.web.common.ReturnJsonEntity;
import com.zimmur.platform.manage.web.common.StatusCodeEnum;
import com.zimmur.platform.manage.web.domain.model.Account;
import com.zimmur.platform.manage.web.shiro.token.TokenManager;


/**
 * 判断登录
 * 
 * @author Administrator
 *
 */
public class LoginFilter extends AccessControlFilter {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Account token = TokenManager.getToken();

		if (null != token || isLoginRequest(request, response)) {
			return true;
		}
		// ajax请求
		if (ShiroFilterUtils.isAjax(request)) {
			ReturnJsonEntity entity =new ReturnJsonEntity(StatusCodeEnum.CODE200402);
    		ShiroFilterUtils.out(response, entity);
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 保存Request和Response 到登录后的链接
		saveRequestAndRedirectToLogin(request, response);
		return false;
	}

}
