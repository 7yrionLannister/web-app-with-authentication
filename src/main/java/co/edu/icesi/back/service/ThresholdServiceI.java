package co.edu.icesi.back.service;

import java.util.Optional;

import co.edu.icesi.back.model.Threshold;

public interface ThresholdServiceI {

	<S extends Threshold> S save(S threshold);

	<S extends Threshold> Iterable<S> saveAll(Iterable<S> thrs);

	Optional<Threshold> findById(Long id);

	boolean existsById(Long id);

	Iterable<Threshold> findAll();

	Iterable<Threshold> findAllById(Iterable<Long> ids);

	long count();

	void deleteById(Long id);

	void delete(Threshold threshold);

	void deleteAll(Iterable<? extends Threshold> thrs);

	void deleteAll();

	void editThreshold(Threshold t);

}
