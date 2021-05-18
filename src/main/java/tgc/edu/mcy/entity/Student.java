package tgc.edu.mcy.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;

/**
 * 学生表
 * */
@Entity
public class Student extends SysUser {
	private String phone;   //电话
	private String sex;    //性别
	private Dept dept;   //班级id
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@ManyToOne
	@CreatedBy
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
}
