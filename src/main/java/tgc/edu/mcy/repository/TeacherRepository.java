package tgc.edu.mcy.repository;

import org.springframework.stereotype.Repository;

import tgc.edu.mcy.custom.CommonRepository;
import tgc.edu.mcy.entity.Teacher;

@Repository
public interface TeacherRepository extends CommonRepository<Teacher, Integer> {

}
