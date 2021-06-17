package co.edu.icesi.back.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.edu.icesi.back.model.Institution;
import co.edu.icesi.back.model.Userr;

@RepositoryRestResource
public interface UserRepositoryI extends CrudRepository<Userr, Long> {

	Userr findByUserName(String username);
	List<Userr> findAllByInstitution(Institution fromInstitutionController);

}
