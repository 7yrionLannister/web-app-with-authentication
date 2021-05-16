package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Localcondition;
import co.edu.icesi.model.Precondition;

@Repository
public interface LocalconditionRepositoryI extends CrudRepository<Localcondition, Long>{

	List<Localcondition> findAllByPrecondition(Precondition precondition);

}
