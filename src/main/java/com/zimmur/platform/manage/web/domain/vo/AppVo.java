package com.zimmur.platform.manage.web.domain.vo;

/**
* <p>Title: AppVo</p>
* <p>Description:应用信息快速检索返回封装的数据 </p>
* <p>Company: zimmur</p>
* @author huangsx
* @time 2017年9月15日 下午4:49:15
*/
public class AppVo {
	private Integer id;
    private String code;
    private String name;
    private String appKey;
    private Byte isIp;
    private String ips;
    private Byte isEnabled;
	private String role;  //应用角色
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public Byte getIsIp() {
		return isIp;
	}
	public void setIsIp(Byte isIp) {
		this.isIp = isIp;
	}
	public String getIps() {
		return ips;
	}
	public void setIps(String ips) {
		this.ips = ips;
	}
	public Byte getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Byte isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
