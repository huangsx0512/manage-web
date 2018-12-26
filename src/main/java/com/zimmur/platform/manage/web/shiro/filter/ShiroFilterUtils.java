package com.zimmur.platform.manage.web.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zimmur.platform.manage.web.common.ReturnJsonEntity;

/**
 * <p>
 * 
 * Shiro Filter 工具类
 * 
 * <p>
 * 
 * @author 
 * @email 
 * @version 1.0,2017-02-16 <br/>
 * 
 */
public class ShiroFilterUtils {
	private final static Logger LOGGER = LoggerFactory.getLogger(ShiroFilterUtils.class);
	
	//登录页面
	static final String LOGIN_URL = "/open/login.shtml";
	//没有权限提醒
	final static String UNAUTHORIZED = "/errorpage/unauthorized.shtml";
	/**
	 * 是否是Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	
	/**
	 * response 输出JSON
	 * @param hresponse
	 * @param resultMap
	 * @throws IOException
	 */
	public static void out(ServletResponse response, Map<String, String> resultMap){
		
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSONObject.toJSONString(resultMap));
		} catch (Exception e) {
			LOGGER.error("输出JSON报错。",e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	public static void out(ServletResponse response, ReturnJsonEntity entity){
		
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSONObject.toJSONString(entity));
		} catch (Exception e) {
			LOGGER.error("输出JSON报错。",e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
}
