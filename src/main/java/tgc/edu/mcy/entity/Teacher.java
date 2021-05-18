package tgc.edu.mcy.entity;

import javax.persistence.Entity;

/**
 * 老师表
 * */
@Entity
public class Teacher extends SysUser {
	private String remark;   //备注
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
