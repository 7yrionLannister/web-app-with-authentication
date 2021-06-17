package co.edu.icesi.back.repository;

import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.back.model.Epidemstatus;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EpidemstatusRepositoryI extends CrudRepository<Epidemstatus, Long>{

}
