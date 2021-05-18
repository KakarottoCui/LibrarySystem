package tgc.edu.mcy.repository;

import org.springframework.stereotype.Repository;

import tgc.edu.mcy.custom.CommonRepository;
import tgc.edu.mcy.entity.Kind;

@Repository
public interface KindRepository extends CommonRepository<Kind, Integer> {

	public Kind findByType(String type);

}
