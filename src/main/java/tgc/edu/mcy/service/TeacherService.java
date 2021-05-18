package tgc.edu.mcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.mcy.custom.CommonService;
import tgc.edu.mcy.entity.Teacher;
import tgc.edu.mcy.repository.TeacherRepository;

@Service
public class TeacherService extends CommonService<Teacher, Integer> {
	@Autowired
	private TeacherRepository teacherDAO;
}
