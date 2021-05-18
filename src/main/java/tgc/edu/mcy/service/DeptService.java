package tgc.edu.mcy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.mcy.custom.CommonService;
import tgc.edu.mcy.entity.Dept;
import tgc.edu.mcy.repository.DeptRepository;

@Service
public class DeptService extends CommonService<Dept, Integer> {
	@Autowired
	private DeptRepository deptDAO;

	public List<Dept> findByDeptIsNull() {
		return deptDAO.findByDeptIsNull();
	}

	public List<Dept> findByDeptId(Integer id) {
		return deptDAO.findByDeptId(id);
	}

	public Dept findByName(String name) {
		return deptDAO.findByName(name);
	}
}
