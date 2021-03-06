package co.edu.icesi.back.service;

import java.util.Optional;

import co.edu.icesi.back.model.Precondition;

public interface PreconditionServiceI {

	<S extends Precondition> S save(S preotransition);

	<S extends Precondition> Iterable<S> saveAll(Iterable<S> pres);

	Optional<Precondition> findById(Long id);

	boolean existsById(Long id);

	Iterable<Precondition> findAllById(Iterable<Long> ids);

	long count();

	void deleteById(Long id);

	void delete(Precondition preotransition);

	void deleteAll(Iterable<? extends Precondition> pres);

	void deleteAll();

	Iterable<Precondition> findAll();

	void editPrecondition(Precondition p);

}
