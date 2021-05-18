package tgc.edu.mcy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;

/**
 * 图书表
 * */
@Entity
public class Book {
	private Integer id;
	private String isbn;    //ISBN
 	private String name;	//书名
 	private String press;   //出版社
 	private String author;  //作者
 	private String uuid;    //UUID
 	private String filename;  //图片名称
 	private Integer number;   //数量
 	private Integer loanNumber;   //借出数量
 	private Kind kind;    //种类
 	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=64,unique=true)
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@ManyToOne
	@CreatedBy
	public Kind getKind() {
		return kind;
	}
	public void setKind(Kind kind) {
		this.kind = kind;
	}
	public Integer getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(Integer loanNumber) {
		this.loanNumber = loanNumber;
	}
}
