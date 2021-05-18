package tgc.edu.mcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.mcy.custom.CommonService;
import tgc.edu.mcy.entity.Student;
import tgc.edu.mcy.repository.StudentRepository;

@Service
public class StudentService extends CommonService<Student, Integer>{
	@Autowired
	private StudentRepository studentDAO;
}
