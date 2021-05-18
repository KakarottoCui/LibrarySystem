package tgc.edu.mcy.controller;

import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tgc.edu.mcy.custom.DataGridParam;
import tgc.edu.mcy.custom.EasyuiResult;
import tgc.edu.mcy.custom.JournalUtil;
import tgc.edu.mcy.entity.Kind;
import tgc.edu.mcy.entity.SysUser;
import tgc.edu.mcy.form.KindForm;
import tgc.edu.mcy.security.UserUtils;
import tgc.edu.mcy.service.KindService;

@Component
@RequestMapping(value="/kind")
public class KindController {
	@Autowired
	private KindService kindDAO;
	@Autowired
	private UserUtils userUtils;
	@Autowired
	private JournalUtil journalUtil;
	
	@RequestMapping(value="/main")
	public String main() {
		return "kind/main";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Integer id, ModelMap map) {
		Kind model = new Kind();
		if(id != null) {
			model = kindDAO.findById(id);
		}
		map.put("model", model);
		return "kind/edit";
	}
	
	/**
	 * 添加图书种类
	 * */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(KindForm form) {
		SysUser user = userUtils.getUser();
		Kind model = new Kind();
		BeanUtils.copyProperties(form, model);
		if(model.getId() == null) {
			journalUtil.save(user.getUsername(), "添加图书种类", "图书管理员");
			model.setNumber(0);
		}
		try {
			kindDAO.save(model);
			return new EasyuiResult("数据保存成功");
		} catch (Exception e) {
			return new EasyuiResult(false, "数据保存失败");
		}
	}
	
	/**
	 * 显示所有图书种类
	 * */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(DataGridParam param, String type) {
		Pageable pageable = param.buildPageable();
		Specification<Kind> specification = buildSpec(param, type);
		Page<Kind> page = kindDAO.findAll(specification, pageable);
		HashMap<String , Object> result = param.getPageResult(page);
		return result;
	}

	private Specification<Kind> buildSpec(DataGridParam param, String type) {
		Specification<Kind> specification=new Specification<Kind>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Kind> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				if(StringUtils.hasText(type)) {
					rules.add(cb.like(root.get("type"), "%"+type+"%"));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return specification;
	}
	
	/**
	 * 判断种类是否已添加
	 * */
	@RequestMapping(value="/type")
	@ResponseBody
	public Boolean username(String type) {
		Kind kind = kindDAO.findByType(type);
		if(kind == null) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 删除图书种类
	 * */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(Integer id) {
		SysUser user = userUtils.getUser();
		journalUtil.save(user.getUsername(), "删除图书种类", "图书管理员");
		kindDAO.deleteById(id);
		return new EasyuiResult("删除成功");
	}
}
