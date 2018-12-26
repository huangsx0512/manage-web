package com.zimmur.platform.manage.web.aop;



import com.zimmur.platform.manage.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BaseInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("BaseInterceptor begin...");
		BaseController.setRequest(request);
		BaseController.setResponse(response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("BaseInterceptor end...");
		BaseController.removeRequest();
		BaseController.removeResponse();
	}

}
