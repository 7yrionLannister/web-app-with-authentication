package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Institution;

@Repository
public interface AutotransitionRepositoryI extends CrudRepository<Autotransition, Long>{
	List<Autotransition> findAllByInstitution(Institution institution);
}
