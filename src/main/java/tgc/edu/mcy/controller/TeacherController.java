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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tgc.edu.mcy.custom.DataGridParam;
import tgc.edu.mcy.custom.EasyuiResult;
import tgc.edu.mcy.custom.JournalUtil;
import tgc.edu.mcy.entity.SysRole;
import tgc.edu.mcy.entity.SysUser;
import tgc.edu.mcy.entity.Teacher;
import tgc.edu.mcy.form.TeacherForm;
import tgc.edu.mcy.repository.RoleRepository;
import tgc.edu.mcy.security.UserUtils;
import tgc.edu.mcy.service.SysUserService;
import tgc.edu.mcy.service.TeacherService;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherDAO;
	@Autowired
	private SysUserService sysUserDAO;
	@Autowired
	private UserUtils userUtils;
	@Autowired
	private JournalUtil journalUtil;
	@Autowired
	private RoleRepository roleDAO;
	
	@RequestMapping(value="/main")
	public String main() {
		return "teacher/main";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Integer id, ModelMap map) {
		Teacher model = new Teacher();
		if(id != null) {
			model = teacherDAO.findById(id);
		}
		map.put("model", model);
		return "teacher/edit";
	}
	
	/**
	 * 添加老师
	 * */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(TeacherForm form) {
		SysUser user = userUtils.getUser();
		Teacher model = new Teacher();
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		BeanUtils.copyProperties(form, model);
		SysRole role=roleDAO.findById(4).get();
		model.getRoles().add(role);
		if(model.getId() == null) {
			journalUtil.save(user.getUsername(), "添加老师", "图书管理员");
			model.setNumber(0);
			model.setPassword(encoder.encode("111111"));		
		}
		try {
			teacherDAO.save(model);
			return new EasyuiResult("数据保存成功");
		} catch (Exception e) {
			return new EasyuiResult(false, "数据保存失败");
		}
	}
	
	/**
	 * 重置老师密码
	 * */
	@RequestMapping(value="/reset")
	@ResponseBody
	public Object reset(Integer id) {
		SysUser user = sysUserDAO.findById(id);
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode("111111"));
		sysUserDAO.save(user);
		return new EasyuiResult("数据保存成功");
	}
	
	/**
	 * 删除老师
	 * */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(Integer id) {
		SysUser user = userUtils.getUser();
		journalUtil.save(user.getUsername(), "删除老师", "图书管理员");
		teacherDAO.deleteById(id);
		return new EasyuiResult("删除成功");
	}
	
	/**
	 * 显示所有老师
	 * */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(DataGridParam param, String username, String name) {
		Pageable pageable = param.buildPageable();
		Specification<Teacher> specification = buildSpec(param, username, name);
		Page<Teacher> page = teacherDAO.findAll(specification, pageable);
		HashMap<String , Object> result = param.getPageResult(page);
		return result;
	}

	private Specification<Teacher> buildSpec(DataGridParam param, String username, String name) {
		Specification<Teacher> specification=new Specification<Teacher>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				if(StringUtils.hasText(username)) {
					rules.add(cb.like(root.get("username"), "%"+username+"%"));
				}
				if(StringUtils.hasText(name)) {
					rules.add(cb.like(root.get("name"), "%"+name+"%"));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return specification;
	}
}
