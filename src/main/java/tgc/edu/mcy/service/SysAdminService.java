package tgc.edu.mcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.mcy.custom.CommonService;
import tgc.edu.mcy.entity.SysAdmin;
import tgc.edu.mcy.repository.SysAdminRepository;

@Service
public class SysAdminService extends CommonService<SysAdmin, Integer> {
	@Autowired
	private SysAdminRepository sysAdminDAO;
}
