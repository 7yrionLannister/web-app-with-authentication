package co.edu.icesi.back.repository;

import co.edu.icesi.back.model.Symptompoll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SymptompollRepositoryI extends CrudRepository<Symptompoll, Long> {
}
