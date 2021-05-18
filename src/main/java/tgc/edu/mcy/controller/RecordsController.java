package tgc.edu.mcy.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tgc.edu.mcy.custom.DataGridParam;
import tgc.edu.mcy.entity.Records;
import tgc.edu.mcy.entity.SysUser;
import tgc.edu.mcy.security.UserUtils;
import tgc.edu.mcy.service.RecordsService;

@Controller
@RequestMapping(value="/records")
public class RecordsController {
	@Autowired
	private RecordsService recordsDAO;
	@Autowired
	private UserUtils userUtils;
	
	@RequestMapping(value="/main")
	public String main(String methon, ModelMap map) {
		map.put("methon", methon);
		return "records/main";
	}
	
	@RequestMapping(value="/borrowRecord")
	public String borrowRecord(ModelMap map) {
		SysUser user = userUtils.getUser();
		List<Records> findAll = recordsDAO.findByUserId(user.getId());
		map.put("records", findAll);
		return "records/borrowRecord";
	}
	
	@RequestMapping(value="/borrowRecord_user")
	public String borrowRecord_user(ModelMap map) {
		SysUser user = userUtils.getUser();
		List<Records> findAll = recordsDAO.findByUserId(user.getId());
		map.put("records", findAll);
		return "records/borrowRecord_user";
	}
	
	@RequestMapping(value="/returnBook")
	public String returnBook(ModelMap map) {
		SysUser user = userUtils.getUser();
		List<Records> findAll = recordsDAO.findByUserIdAndState(user.getId(), "未还");
		map.put("records", findAll);
		return "records/returnBook";
	}
	
	@RequestMapping(value="/returnBook_user")
	public String returnBook_user(ModelMap map) {
		SysUser user = userUtils.getUser();
		List<Records> findAll = recordsDAO.findByUserIdAndState(user.getId(), "未还");
		map.put("records", findAll);
		return "records/returnBook_user";
	}
	
	/**
	 * 显示所有借书记录
	 * */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(DataGridParam param, String userName, String name, String state, String methon, String methon1) {
		Pageable pageable = param.buildPageable();
		Specification<Records> specification = buildSpec(param, userName, name, state, methon, methon1);
		Page<Records> page = recordsDAO.findAll(specification, pageable);
		HashMap<String , Object> result = param.getPageResult(page);
		return result;
	}

	private Specification<Records> buildSpec(DataGridParam param, String userName, String name, String state, String methon, String methon1) {
		Specification<Records> specification=new Specification<Records>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Records> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				if(StringUtils.hasText(userName)) {
					rules.add(cb.like(root.get("userName"), "%"+userName+"%"));
				}
				if(StringUtils.hasText(name)) {
					rules.add(cb.like(root.get("name"), "%"+name+"%"));
				}
				if(StringUtils.hasText(state)) {
					rules.add(cb.like(root.get("state"), "%"+state+"%"));
				}
				if(StringUtils.hasText(methon)) {
					rules.add(cb.like(root.get("reamark"), "%"+methon+"%"));
				}
				if(StringUtils.hasText(methon1)) {
					rules.add(cb.like(root.get("state"), "%"+methon1+"%"));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return specification;
	}
}
