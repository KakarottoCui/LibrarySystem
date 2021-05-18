package tgc.edu.mcy.controller;

import java.util.HashMap;
import java.util.HashSet;

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
import tgc.edu.mcy.entity.Journal;
import tgc.edu.mcy.service.JournalService;

@Controller
@RequestMapping(value="/journal")
public class JournalController {
	@Autowired
	private JournalService journalDAO;
	
	@RequestMapping(value="/main")
	public String main(String methon, ModelMap map) {
		map.put("methon", methon);
		return "journal/main";
	}
	
	/**
	 * 显示所有操作日志
	 * */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(DataGridParam param, String username, String operationName, String reamark, String methon) {
		Pageable pageable = param.buildPageable();
		Specification<Journal> specification = buildSpec(param, username, operationName, reamark, methon);
		Page<Journal> page = journalDAO.findAll(specification, pageable);
		HashMap<String , Object> result = param.getPageResult(page);
		return result;
	}

	private Specification<Journal> buildSpec(DataGridParam param, String username, String operationName, String reamark, String methon) {
		Specification<Journal> specification=new Specification<Journal>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Journal> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				if(StringUtils.hasText(username)) {
					rules.add(cb.like(root.get("username"), "%"+username+"%"));
				}
				if(StringUtils.hasText(operationName)) {
					rules.add(cb.like(root.get("operationName"), "%"+operationName+"%"));
				}
				if(StringUtils.hasText(reamark)) {
					rules.add(cb.like(root.get("reamark"), "%"+reamark+"%"));
				}
				if(StringUtils.hasText(methon)) {
					rules.add(cb.like(root.get("reamark"), "%"+methon+"%"));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return specification;
	}
}
