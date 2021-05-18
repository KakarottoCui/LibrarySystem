package tgc.edu.mcy.entity;

import javax.persistence.Entity;

/**
 * 图书管理员
 * */
@Entity
public class SysAdmin extends SysUser {
	private String remark;   //备注
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
