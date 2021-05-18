package tgc.edu.mcy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tgc.edu.mcy.custom.EasyuiResult;
import tgc.edu.mcy.custom.JournalUtil;
import tgc.edu.mcy.entity.Dept;
import tgc.edu.mcy.entity.SysUser;
import tgc.edu.mcy.entity.Teacher;
import tgc.edu.mcy.form.DeptForm;
import tgc.edu.mcy.security.UserUtils;
import tgc.edu.mcy.service.DeptService;
import tgc.edu.mcy.service.TeacherService;

@Controller
@RequestMapping(value="/dept")
public class DeptController {
	@Autowired
	private DeptService deptDAO;
	@Autowired
	private TeacherService teacherDAO;
	@Autowired
	private UserUtils userUtils;
	@Autowired
	private JournalUtil journalUtil;
	
	@RequestMapping(value="/main")
	public String main() {
		return "dept/main";
	}
	
	/**
	 * 树形菜单
	 * */
	@RequestMapping("/dept_list")
	@ResponseBody
	public Object dept_list(Integer id) {
		List<Dept> list;
		if(id == null) {
			list = deptDAO.findByDeptIsNull();
		}else {
			list = deptDAO.findByDeptId(id);
		}
		List<HashMap<String, Object>> result = new ArrayList<>();
		return fun(list, result);
	}
	
	/**
	 * 改变子节点text
	 * */
	private Object fun(List<Dept> list, List<HashMap<String, Object>> result) {
		for (Dept dept : list) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", dept.getId());
			map.put("text", dept.getName());
			List<HashMap<String, Object>> result1 = new ArrayList<>();
			List<Dept> children = dept.getChildren();
			map.put("children", fun(children, result1));
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 判断是否是子节点
	 * */	
	@RequestMapping(value="/node")
	@ResponseBody
	public Boolean node(Integer id) {
		List<Dept> deptId = deptDAO.findByDeptId(id);
		if(deptId.size() > 0) {
			return false;
		}else {
			return true;
		}
	}

	@RequestMapping(value="/edit")
	public String edit(Integer parentId, Integer id, ModelMap map) {
		Dept model = new Dept();
		if(id != null) {
			model = deptDAO.findById(id);
			map.put("name", model.getTeacher().getName());
		}
		if(parentId != null) {
			model.setDept(deptDAO.findById(parentId));
		}
		List<Teacher> teacher = teacherDAO.findAll();
		map.put("teacher", teacher);
		map.put("model", model);
		return "dept/edit";
	}
	
	/**
	 * 显示最上一层父节点
	 * */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list() {
		List<Dept> list = deptDAO.findByDeptIsNull();
		return list;
	}
	
	/**
	 * 删除
	 * */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(Integer id) {
		try {
			SysUser user = userUtils.getUser();
			journalUtil.save(user.getUsername(), "删除学院或班级", "图书管理员");
			deptDAO.deleteById(id);
			return new EasyuiResult("数据删除成功");
		} catch (Exception e) {
			return new EasyuiResult(false, "数据删除失败");
		}
	}
	
	/**
	 * combotree数据
	 * */
	@RequestMapping(value="/combotree")
	@ResponseBody
	public Object combotree(Integer id, Integer nodeId) {
		List<HashMap<String, Object>> result = new ArrayList<>();
		List<Dept> root;
		if(id == null) {
			root = deptDAO.findByDeptIsNull();
		}else {
			root = deptDAO.findByDeptId(id);
		}
		for(Dept dept: root) {
			if(nodeId != null && dept.getId() == nodeId) {
				continue;
			}
			HashMap<String, Object> node = new HashMap<>();
			node.put("id", dept.getId());
			node.put("text", dept.getName());
			if(deptDAO.findByDeptId(dept.getId()).size() > 0) {
				node.put("state", "closed");
				node.put("children", combotree(dept.getId(), nodeId));
			}
			result.add(node);
		}
		return result;
	}
	
	/**
	 * 判断学院或班级是否已添加
	 * */
	@RequestMapping(value="/name")
	@ResponseBody
	public Boolean username(String name) {
		Dept dept = deptDAO.findByName(name);
		if(dept == null) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 添加
	 * */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(DeptForm form) {
		try {
			Dept model = new Dept();
			Integer id = form.getId();
			if(id != null) {
				model = deptDAO.findById(id);
			}else {
				SysUser user = userUtils.getUser();
				journalUtil.save(user.getUsername(), "添加学院或班级", "图书管理员");				
			}
			Integer parentId = form.getParentId();
			if(parentId != null) {
				model.setDept(deptDAO.findById(parentId));
			}
			BeanUtils.copyProperties(form, model,"id","dept");
			deptDAO.save(model);
			return new EasyuiResult("数据保存成功");
		} catch (Exception e) {
			return new EasyuiResult(false, "数据保存失败");
		}
	}
	
}
