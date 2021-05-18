package tgc.edu.mcy.form;

import tgc.edu.mcy.entity.Kind;

public class BookForm {
	private Integer id;
	private String isbn;
 	private String name;
 	private String press;
 	private String author;
 	private Integer number;
 	private Integer loanNumber;
 	private Kind kind;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(Integer loanNumber) {
		this.loanNumber = loanNumber;
	}
	public Kind getKind() {
		return kind;
	}
	public void setKind(Kind kind) {
		this.kind = kind;
	}
}
