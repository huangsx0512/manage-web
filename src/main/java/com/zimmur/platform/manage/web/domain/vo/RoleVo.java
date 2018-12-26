package com.zimmur.platform.manage.web.domain.vo;

/**
* <p>Title: RoleVo</p>
* <p>Description: </p>
* <p>Company: zimmur</p>
* @author huangsx
* @time 2017年9月18日 下午4:53:18
*/
public class RoleVo {
	private Integer id;
	private String name;
	private byte isEnabled;//角色是否启用
	private Byte choose;//角色是否被应用选中，0--》没有选中，1--》选中
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
