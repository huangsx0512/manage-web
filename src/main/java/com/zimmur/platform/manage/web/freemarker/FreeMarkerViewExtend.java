package com.zimmur.platform.manage.web.freemarker;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.zimmur.platform.manage.web.common.Constant;
import com.zimmur.platform.manage.web.domain.model.Account;
import com.zimmur.platform.manage.web.shiro.token.TokenManager;



public class FreeMarkerViewExtend extends FreeMarkerView {
	private final static Logger LOGGER = LoggerFactory.getLogger(FreeMarkerViewExtend.class);
	
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request){
		
		try {
			super.exposeHelpers(model, request);
		} catch (Exception e) {
			LOGGER.error("FreeMarkerViewExtend 加载父类出现异常。请检查。",e);
		}
		model.put(Constant.CONTEXT_PATH, request.getContextPath());
		model.putAll(Ferrmarker.initMap);
		Account token = TokenManager.getToken();
		//String ip = IPUtils.getIP(request);
		model.put("token", token);//登录的token
		model.put("_time", new Date().getTime());
		model.put("NOW_YEAY", Constant.NOW_YEAY);//今年
		
		//model.put("_v", Constant.VERSION);//版本号，重启的时间
		//model.put("cdn", Constant.DOMAIN_CDN);//CDN域名
		model.put("basePath", request.getContextPath());//base目录。
		
	}
}
