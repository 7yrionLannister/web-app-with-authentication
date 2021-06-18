package co.edu.icesi.back.repository;

import co.edu.icesi.back.model.Symptom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface SymptomRepositoryI extends CrudRepository<Symptom, Long> {
}
