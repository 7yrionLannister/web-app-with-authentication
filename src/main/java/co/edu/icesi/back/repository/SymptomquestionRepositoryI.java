package co.edu.icesi.back.repository;

import co.edu.icesi.back.model.Symptomquestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SymptomquestionRepositoryI extends CrudRepository<Symptomquestion, Long> {
}
