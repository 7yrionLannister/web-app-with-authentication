package co.edu.icesi.back.service;

import java.util.Optional;

import co.edu.icesi.back.model.Localcondition;

public interface LocalconditionServiceI {
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

	void editLocalcondition(Localcondition l);

}
