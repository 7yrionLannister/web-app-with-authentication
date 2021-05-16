package co.edu.icesi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.Institution;
import co.edu.icesi.model.Userr;

public interface UserRepositoryI extends CrudRepository<Userr, Long> {

	Userr findByUserName(String username);
	List<Userr> findAllByInstitution(Institution fromInstitutionController);

}
