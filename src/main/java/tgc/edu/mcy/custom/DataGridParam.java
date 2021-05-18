package tgc.edu.mcy.custom;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public class DataGridParam {
	private Integer page;
	private Integer rows;
	private String sort;
	private String order;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public Pageable buildPageable() {
		if(sort==null) {
			return PageRequest.of(page-1, rows);
		}else {
			return PageRequest.of(page-1, rows, Direction.fromString(order), sort);
		}
	}
	
	public static HashMap<String, Object> getPageResult(Page page) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("total", page.getTotalElements());   //数据库中数据的总页面数
		result.put("page", page.getNumber()+1);   //当前页面数量
		result.put("rows", page.getContent());
		result.put("sort", page.getSort());
		return result;
	}
}
