package tgc.edu.mcy.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import tgc.edu.mcy.custom.CommonRepository;
import tgc.edu.mcy.entity.Records;

@Repository
public interface RecordsRepository extends CommonRepository<Records, Integer> {

	public List<Records> findByUserId(Integer id);

	public List<Records> findByUserIdAndState(Integer id, String string);

}
