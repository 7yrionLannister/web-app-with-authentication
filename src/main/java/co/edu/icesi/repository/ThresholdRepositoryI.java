package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Institution;
import co.edu.icesi.model.Threshold;

@Repository
public interface ThresholdRepositoryI extends CrudRepository<Threshold, Long>{

	List<Threshold> findAllByInstitution(Institution fromInstitutionController);

}
