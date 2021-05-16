package co.edu.icesi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Institution;

@Repository
public interface InstitutionRepositoryI extends CrudRepository<Institution, Long>{

}
