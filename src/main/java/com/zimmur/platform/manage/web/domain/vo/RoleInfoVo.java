package com.zimmur.platform.manage.web.domain.vo;

/**
* <p>Title: RoleInfoVo</p>
* <p>Description:角色信息快速检索返回封装的数据 </p>
* <p>Company: zimmur</p>
* @author huangsx
* @time 2017年9月19日 下午4:49:15
*/
public class RoleInfoVo {
	private Integer id;
    private String name;
    private Byte isEnabled;
	private String ment;  //角色权限
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Byte isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getMent() {
		return ment;
	}
	public void setMent(String ment) {
		this.ment = ment;
	}
}
