package com.zimmur.platform.manage.web.common;

public enum StatusCodeEnum {
	//app请求返回码
	CODE000000("000000","操作成功"),
	CODE100000("100000","出现未知异常，操作失败"),
	CODE100100("100100","删除类型失败——该类型下存在商品"),
	CODE100400("100400","该账号已存在，创建账号失败"),
	CODE100401("100401","该账号已存在，修改账号信息失败"),
	CODE200000("200000","您已经被踢出，请重新登录！"),
	CODE200401("200401","没有操作权限"),
	CODE200402("200402","请重新登录"),
	CODE200403("200403","账号已禁用"),
	CODE200404("200404","用户名或密码错误");
	
	private final String code;
	private final String msg;
	
	private StatusCodeEnum(String code,String msg){
		this.code=code;
		this.msg=msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public static void main(String[] args) {
		StatusCodeEnum enum1 = StatusCodeEnum.CODE000000;
		System.out.println(enum1.getCode() + " "+ enum1.getMsg());
	}
}
