package co.edu.icesi.back.repository;

import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.back.model.Institution;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InstitutionRepositoryI extends CrudRepository<Institution, Long>{

}
