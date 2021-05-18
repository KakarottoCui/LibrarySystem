package tgc.edu.mcy.repository;

import org.springframework.stereotype.Repository;

import tgc.edu.mcy.custom.CommonRepository;
import tgc.edu.mcy.entity.SysAdmin;

@Repository
public interface SysAdminRepository extends CommonRepository<SysAdmin, Integer> {

}
