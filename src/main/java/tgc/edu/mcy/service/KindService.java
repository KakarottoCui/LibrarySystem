package tgc.edu.mcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.mcy.custom.CommonService;
import tgc.edu.mcy.entity.Kind;
import tgc.edu.mcy.repository.KindRepository;

@Service
public class KindService extends CommonService<Kind, Integer> {
	@Autowired
	private KindRepository kindDAO;

	public Kind findByType(String type) {
		return kindDAO.findByType(type);
	}
}
