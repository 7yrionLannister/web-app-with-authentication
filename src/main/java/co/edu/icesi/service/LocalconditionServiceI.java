package co.edu.icesi.service;

import java.util.Optional;

import co.edu.icesi.model.Localcondition;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.model.Threshold;

public interface LocalconditionServiceI {

	void editLocalcondition(long id, String colname, String keycol, String operator, String query, String table,
			String type, Precondition pre, Threshold th);

	void deleteAll(Iterable<? extends Localcondition> localcons);

	void deleteAll();

	void delete(Localcondition localconotransition);

	void deleteById(Long id);

	long count();

	Iterable<Localcondition> findAllById(Iterable<Long> ids);

	Iterable<Localcondition> findAll();

	boolean existsById(Long id);

	Optional<Localcondition> findById(Long id);

	<S extends Localcondition> Iterable<S> saveAll(Iterable<S> localcons);

	<S extends Localcondition> S save(S localconotransition);

}
