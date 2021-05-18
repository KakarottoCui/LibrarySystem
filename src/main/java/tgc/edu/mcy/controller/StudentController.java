package tgc.edu.mcy.controller;

import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;

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
import tgc.edu.mcy.entity.Student;
import tgc.edu.mcy.entity.SysRole;
import tgc.edu.mcy.entity.SysUser;
import tgc.edu.mcy.form.StudentForm;
import tgc.edu.mcy.repository.RoleRepository;
import tgc.edu.mcy.security.UserUtils;
import tgc.edu.mcy.service.DeptService;
import tgc.edu.mcy.service.StudentService;
import tgc.edu.mcy.service.SysUserService;

@Controller
@RequestMapping(value="/student")
public class StudentController {
	@Autowired
	private StudentService studentDAO;
	@Autowired
	private JournalUtil journalUtil;
	@Autowired
	private RoleRepository roleDAO;
	@Autowired
	private UserUtils userUtils;
	@Autowired
	private DeptService deptDAO;
	@Autowired
	private SysUserService sysUserDAO;
	
	@RequestMapping(value="/studentAll")
	public String Student(Integer id, ModelMap map) {
		map.put("id", id);
		return "student/studentAll";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Integer id, ModelMap map) {
		Student student = new Student();
		if(id != null) {
			student = studentDAO.findById(id);
			map.put("id", student.getDept().getId());
		}
		map.put("model", student);
		return "student/edit";
	}
	
	/**
	 * 显示班级学生
	 * */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(DataGridParam param, HttpSession session, String username, String name, String phone, Integer id) {
		session.removeAttribute("dept_id");
		session.setAttribute("dept_id", id);
		Pageable pageable = param.buildPageable();
		Specification<Student> specification = buildSpec(param, username, name, phone, id);
		Page<Student> page = studentDAO.findAll(specification, pageable);
		HashMap<String , Object> result = param.getPageResult(page);
		return result;
	}
	
	private Specification<Student> buildSpec(DataGridParam param, String username, String name, String phone, Integer id) {
		Specification<Student> specification=new Specification<Student>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				if(StringUtils.hasText(username)) {
					rules.add(cb.like(root.get("username"), "%"+username+"%"));
				}
				if(StringUtils.hasText(name)) {
					rules.add(cb.like(root.get("name"), "%"+name+"%"));
				}
				if(StringUtils.hasText(phone)) {
					rules.add(cb.like(root.get("phone"), "%"+phone+"%"));
				}
				rules.add(cb.equal(root.get("dept").get("id"), id));
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return specification;
	}
	
	/**
	 * 添加学生
	 * */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(StudentForm form, HttpSession session) {
		SysUser user = userUtils.getUser();
		Student model = new Student();
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		BeanUtils.copyProperties(form, model);
		SysRole role=roleDAO.findById(3).get();
		model.getRoles().add(role);
		if(model.getId() == null) {
			journalUtil.save(user.getUsername(), "添加学生", "图书管理员");
			model.setNumber(0);
			model.setPassword(encoder.encode("111111"));
			Integer id = (Integer) session.getAttribute("dept_id");
			model.setDept(deptDAO.findById(id));
		}
		try {
			studentDAO.save(model);
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
		journalUtil.save(user.getUsername(), "删除学生", "图书管理员");
		studentDAO.deleteById(id);
		return new EasyuiResult("删除成功");
	}
}
