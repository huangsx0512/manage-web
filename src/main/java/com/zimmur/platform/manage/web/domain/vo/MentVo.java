package com.zimmur.platform.manage.web.domain.vo;

/**
* <p>Title: MentVo</p>
* <p>Description: 查询权限</p>
* <p>Company: zimmur</p>
* @author huangsx
* @time 2017年9月19日 下午4:53:18
*/
public class MentVo {
	private Integer id;//权限id
	private String name;//权限名称
	private byte isEnabled;//权限是否启用
	private Byte choose;//权限是否被角色选中，0--》没有选中，1--》选中
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
	public byte getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(byte isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Byte getChoose() {
		return choose;
	}
	public void setChoose(Byte choose) {
		this.choose = choose;
	}
	
}
