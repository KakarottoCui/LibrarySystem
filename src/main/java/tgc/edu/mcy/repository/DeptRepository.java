package tgc.edu.mcy.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import tgc.edu.mcy.custom.CommonRepository;
import tgc.edu.mcy.entity.Dept;

@Repository
public interface DeptRepository extends CommonRepository<Dept, Integer> {

	public List<Dept> findByDeptIsNull();

	public List<Dept> findByDeptId(Integer id);

	public Dept findByName(String name);

}
