package com.zimmur.platform.manage.web.shiro.filter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zimmur.platform.manage.web.common.ReturnJsonEntity;
import com.zimmur.platform.manage.web.common.StatusCodeEnum;

/**
 * <p>
 * 
 * 权限校验 Filter
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　　<br/>
 *
 * @author 
 * @email  
 * @version 1.0,2017年02月16日 <br/>
 * 
 */
public class PermissionFilter extends AccessControlFilter {
	private final static Logger LOGGER = LoggerFactory.getLogger(PermissionFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		
		//先判断带参数的权限判断
		Subject subject = getSubject(request, response);
		if(null != mappedValue){
			String[] arra = (String[])mappedValue;
			for (String permission : arra) {
				if(subject.isPermitted(permission)){
					return Boolean.TRUE;
				}
			}
		}
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		/**
		 * 此处是改版后，为了兼容项目不需要部署到root下，也可以正常运行，但是权限没设置目前必须到root 的URI，
		 * 原因：如果你把这个项目叫 ShiroDemo，那么路径就是 /ShiroDemo/xxxx.shtml ，那另外一个人使用，又叫Shiro_Demo,那么就要这么控制/Shiro_Demo/xxxx.shtml 
		 * 理解了吗？
		 * 所以这里替换了一下，使用根目录开始的URI
		 */
		
		String uri = httpRequest.getRequestURI();//获取URI
		String basePath = httpRequest.getContextPath();//获取basePath
		if(null != uri && uri.startsWith(basePath)){
			uri = uri.replace(basePath, "");
		}
		if(subject.isPermitted(uri)){
			return Boolean.TRUE;
		}
		
		if(ShiroFilterUtils.isAjax(request)){//提示无操作权限
			ReturnJsonEntity entity =new ReturnJsonEntity(StatusCodeEnum.CODE200401);
    		ShiroFilterUtils.out(response, entity);
		}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
			
			Subject subject = getSubject(request, response);  
	        if (null == subject.getPrincipal()) {//表示没有登录，重定向到登录页面  
	            saveRequest(request);  
	            WebUtils.issueRedirect(request, response, ShiroFilterUtils.LOGIN_URL);  
	        } else {
	        	if(ShiroFilterUtils.isAjax(request)){//提示无操作权限
	        		ReturnJsonEntity entity =new ReturnJsonEntity(StatusCodeEnum.CODE200401);
	        		ShiroFilterUtils.out(response, entity);
	        	}else{//跳转到未授权页
	        		 WebUtils.issueRedirect(request, response, ShiroFilterUtils.UNAUTHORIZED);
	        	}
	        }  
		return Boolean.FALSE;
	}

}
