package tgc.edu.mcy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.mcy.custom.CommonService;
import tgc.edu.mcy.entity.Records;
import tgc.edu.mcy.repository.RecordsRepository;

@Service
public class RecordsService extends CommonService<Records, Integer> {
	@Autowired
	private RecordsRepository recordsDAO;

	public List<Records> findByUserId(Integer id) {
		return recordsDAO.findByUserId(id);
	}

	public List<Records> findByUserIdAndState(Integer id, String string) {
		return recordsDAO.findByUserIdAndState(id, string);
	}
}
