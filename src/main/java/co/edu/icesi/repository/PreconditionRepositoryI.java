package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Precondition;

@Repository
public interface PreconditionRepositoryI extends CrudRepository<Precondition, Long> {

	List<Precondition> findAllByAutotransition(Autotransition autotransition);

}
