package com.zimmur.platform.manage.web.controller;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController{	
	private final static Logger log=LoggerFactory.getLogger(BaseController.class);
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	/**
     * 请求上下文
     */
    private static ThreadLocal<HttpServletRequest>  request_thread_local = new ThreadLocal<HttpServletRequest>() ;
    /**
     * 应答上下文
     */
    private static ThreadLocal<HttpServletResponse>  response_thread_local = new ThreadLocal<HttpServletResponse>() ;;
    
    /**
     * Spring MVC的统一异常处理     
     * @param request
     * @param e
     * @return
     */
	@ExceptionHandler
    public String exception(HttpServletRequest request, Exception e) {
		log.error("\n\r请求地址：{} \n\r异常信息: {} ",request.getServletPath(),e);
		if (e instanceof NumberFormatException) {
			request.setAttribute("msg", "类型转换异常");
			
	    } else if (e instanceof NullPointerException) {
	    	request.setAttribute("msg", "空指针异常");
	       
	    } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
	    	request.setAttribute("msg", "网络异常");
	    }else{
	    	request.setAttribute("msg", "未知异常");
	    }
		return "/errorpage/404";
	}
	
	
	public static void setRequest(HttpServletRequest request) {
		 request_thread_local.set(request);
	}


	public static void setResponse(HttpServletResponse response) {
		response_thread_local.set(response);
	}


	/**
     * 当前的请求对象
     * @return
     */
    protected HttpServletRequest getRequest(){
        return request_thread_local.get();
    }
    
    /**
     * 获取当前的应答对象
     * @return
     */
    protected HttpServletResponse getResponse(){
        return response_thread_local.get();
    }
    
    /**
     * 移除当前Request
     */
    public static void removeRequest(){
    	request_thread_local.remove();
    }
    /**
     * 移除当前Reponse
     */
    public static void removeResponse(){
    	response_thread_local.remove();
    }
    /**
    * 获取远程访问IP
    */
   private String remoteIp = null;
   /**
    * 获取远程访问IP
    * @return
    */
   
   protected String getRemoteIp(){
       HttpServletRequest request = this.getRequest();
       if (this.remoteIp==null || this.remoteIp.length()==0)
       {
           this.remoteIp = request.getHeader("x-forwarded-for");
           if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
               this.remoteIp= request.getHeader("X-Real-IP");
           }
           if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
               this.remoteIp= request.getHeader("Proxy-Client-IP");
           }
           if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
               this.remoteIp= request.getHeader("WL-Proxy-Client-IP");
           }
           if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
               this.remoteIp= request.getHeader("HTTP_CLIENT_IP");
           }
           if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
               this.remoteIp= request.getHeader("HTTP_X_FORWARDED_FOR");
           }
           if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
               this.remoteIp= request.getRemoteAddr();
           }
           if (this.remoteIp == null || this.remoteIp.isEmpty() || "unknown".equalsIgnoreCase(this.remoteIp)) {
               this.remoteIp= request.getRemoteHost();
           }
       }
       return remoteIp.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":remoteIp;
   }
   /*********************获取访问参数*******************/
   /**
    * 获取所有参数
    * @return
    */
   protected Map<String,String[]> getParams(){
       HttpServletRequest request = this.getRequest();
       return request.getParameterMap();
   }
   /**
    * 获取指定的配置
    * @param name
    * @return
    */
   protected String getParam(String name){
       return getParam(name, "");
   }
   /**
    * 根据参数名称获取参数值，如果没有找到则以默认值返回
    * @param name
    * @param defaultValue
    * @return
    */
   protected String getParam(String name, String defaultValue){
       HttpServletRequest request = this.getRequest();
       String strValue = request.getParameter(name);
       return strValue == null ? defaultValue : strValue;
   }
   /**
    * 获取整形的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected int getIntParam(String name){
       return getParam(name, 0);
   }
   /**
    * 获取整形的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected int getParam(String name, Integer defaultValue){
       String strValue = getParam(name, defaultValue.toString());
       try{
           return Integer.valueOf(strValue);
       }
       catch(Exception e){
           return defaultValue;
       }
   }
   /**
    * 获取长整形的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected long getLongParam(String name){
       return getParam(name, 0L);
   }
   /**
    * 获取长整形的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected long getParam(String name, Long defaultValue){
       String strValue = getParam(name, defaultValue.toString());
       try{
           return Long.valueOf(strValue);
       }
       catch(Exception e){
           return defaultValue;
       }
   }
   /**
    * 获取单精度的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected float getFloatParam(String name){
       return getParam(name, 0F);
   }
   /**
    * 获取单精度的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected float getParam(String name, Float defaultValue){
       String strValue = getParam(name, defaultValue.toString());
       try{
           return Float.valueOf(strValue);
       }
       catch(Exception e){
           return defaultValue;
       }
   }
   /**
    * 获取双精度的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected double getDoubleParam(String name){
       return getParam(name, 0D);
   }
   /**
    * 获取双精度的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected double getParam(String name, Double defaultValue){
       String strValue = getParam(name, defaultValue.toString());
       try{
           return Double.valueOf(strValue);
       }
       catch(Exception e){
           return defaultValue;
       }
   }
   /**
    * 获取字节的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected byte getByteParam(String name){
       return getParam(name, (byte)0);
   }
   /**
    * 获取字节的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected byte getParam(String name, Byte defaultValue){
       String strValue = getParam(name, defaultValue.toString());
       try{
           return Byte.valueOf(strValue);
       }
       catch(Exception e){
           return defaultValue;
       }
   }
   /**
    * 获取字节的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected short getShortParam(String name){
       return getParam(name, (short)0);
   }
   /**
    * 获取字节的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected short getParam(String name, Short defaultValue){
       String strValue = getParam(name, defaultValue.toString());
       try{
           return Short.valueOf(strValue);
       }
       catch(Exception e){
           return defaultValue;
       }
   }
   /**
    * 获取布尔的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected boolean getBooleanParam(String name){
       return getParam(name, false);
   }
   /**
    * 获取布尔的参数值
    * @param name
    * @param defaultValue
    * @return
    */
   protected boolean getParam(String name, Boolean defaultValue){
       String strValue = getParam(name, defaultValue.toString());
       try{
           return Boolean.valueOf(strValue);
       }
       catch(Exception e){
           return defaultValue;
       }
   }
   
   
   /*********************获取访问参数*******************/
   
   protected String getDomainUrl(){
	   return getRequest().getScheme()+"://"+ getRequest().getServerName();
   }
   
   protected String getCurrentUrl(){
	   return getDomainUrl()+getRequest().getRequestURI();
   }
   public boolean isMobile(String str){
		Pattern p = null;
		Matcher m=null;
		boolean bRet=false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
		m=p.matcher(str);
		bRet=m.matches();
		return bRet;
	}
}
