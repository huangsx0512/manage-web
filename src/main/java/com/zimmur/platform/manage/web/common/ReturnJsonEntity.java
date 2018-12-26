package com.zimmur.platform.manage.web.common;


import java.util.HashMap;

public class ReturnJsonEntity {	
	private String code;
	private String msg;
	private Object data;
	
	public ReturnJsonEntity(){
		this.code="";
		this.msg="";
	}
	public ReturnJsonEntity(StatusCodeEnum codeEnum){
		this.code=codeEnum.getCode();
		this.msg=codeEnum.getMsg();
	}
	public void init(StatusCodeEnum codeEnum){
		this.code=codeEnum.getCode();
		this.msg=codeEnum.getMsg();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data==null?new HashMap():data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "ReturnJsonEntity [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}	
		
}
