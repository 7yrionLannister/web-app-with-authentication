package co.edu.icesi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Epidemstatus;

@Repository
public interface EpidemstatusRepositoryI extends CrudRepository<Epidemstatus, Long>{

}
