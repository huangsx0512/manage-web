package com.zimmur.platform.manage.web.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zimmur.platform.manage.web.common.ReturnJsonEntity;
import com.zimmur.platform.manage.web.common.StatusCodeEnum;
import com.zimmur.platform.manage.web.shiro.session.CustomSessionManager;
import com.zimmur.platform.manage.web.shiro.session.SessionStatus;


/**
 * <p>
 * 判断是否踢出
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　xxxxx　2017年02月17日 　<br/>
 *
 * @author jack
 * @email  jiaxh@zimmur.com
 * @version 1.0,2017年02月17日 <br/>
 * 
 */
public class SimpleAuthFilter extends AccessControlFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAuthFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		String url = httpRequest.getRequestURI();
		if(url.startsWith("/open/")){
			return Boolean.TRUE;
		}
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		Map<String, String> resultMap = new HashMap<String, String>();
		SessionStatus sessionStatus = (SessionStatus) session.getAttribute(CustomSessionManager.SESSION_STATUS);
		if (null != sessionStatus && !sessionStatus.isOnlineStatus()) {
			//判断是不是Ajax请求
			if (ShiroFilterUtils.isAjax(request) ) {
				LOGGER.debug("当前用户已经被踢出，并且是Ajax请求！");
				ReturnJsonEntity entity=new ReturnJsonEntity(StatusCodeEnum.CODE200000);
				out(response, entity);
			}
			return  Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		
		//先退出
		Subject subject = getSubject(request, response);
		subject.logout();
		/**
		 * 保存Request，用来保存当前Request，然后登录后可以跳转到当前浏览的页面。
		 * 比如：
		 * 我要访问一个URL地址，/admin/index.html，这个页面是要登录。然后要跳转到登录页面，但是登录后要跳转回来到/admin/index.html这个地址，怎么办？
		 * 传统的解决方法是变成/user/login.shtml?redirectUrl=/admin/index.html。
		 * shiro的解决办法不是这样的。需要：<code>WebUtils.getSavedRequest(request);</code>
		 * 							 然后：{@link UserLoginController.submitLogin(...)}中的<code>String url = WebUtils.getSavedRequest(request).getRequestUrl();</code>
		 * 如果还有问题，请咨询我。
		 */
		WebUtils.saveRequest(request);
		//再重定向
		WebUtils.issueRedirect(request, response, "/open/kickedOut.shtml");
		return false;
	}

	private void out(ServletResponse hresponse, ReturnJsonEntity entity)
			throws IOException {
		hresponse.setCharacterEncoding("UTF-8");
		PrintWriter out = hresponse.getWriter();
		out.println(JSONObject.toJSONString(entity));
		out.flush();
		out.close();
	}
}
