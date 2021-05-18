package tgc.edu.mcy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;

/**
 * 借书记录
 * */
@Entity
public class Records {
	private Integer id;
	private String startTime;   //借书时间
	private String returnTime;    //还书时间
	private String endTime;    //应还书时间
	private SysUser user;   //借书人
	private Book book;   //书
	private String state;      //状态
	private String reamark;    //备注
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getReamark() {
		return reamark;
	}
	public void setReamark(String reamark) {
		this.reamark = reamark;
	}
	@ManyToOne
	@CreatedBy
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@ManyToOne
	@CreatedBy
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
}
