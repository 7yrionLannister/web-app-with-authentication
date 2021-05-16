package co.edu.icesi.service;

import java.util.Optional;

import co.edu.icesi.model.Userr;

public interface UserrServiceI {

	<S extends Userr> S save(S instotransition);

	<S extends Userr> Iterable<S> saveAll(Iterable<S> insts);

	Optional<Userr> findById(long id);

	boolean existsById(Long id);

	Iterable<Userr> findAll();

	Iterable<Userr> findAllById(Iterable<Long> ids);

	long count();

	void deleteById(Long id);

	void delete(Userr instotransition);

	void deleteAll(Iterable<? extends Userr> insts);

	void deleteAll();

}
