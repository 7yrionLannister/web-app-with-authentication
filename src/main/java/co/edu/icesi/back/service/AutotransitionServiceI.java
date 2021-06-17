package co.edu.icesi.back.service;

import java.util.Optional;

import co.edu.icesi.back.model.Autotransition;

public interface AutotransitionServiceI {
	public <S extends Autotransition> S save(S autotransition);
	public <S extends Autotransition> Iterable<S> saveAll(Iterable<S> auts);
	public Optional<Autotransition> findById(Long id);
	public boolean existsById(Long id);
	public Iterable<Autotransition> findAll();
	public Iterable<Autotransition> findAllById(Iterable<Long> ids);
	public long count();
	public void deleteById(Long id);
	public void delete(Autotransition autotransition);
	public void deleteAll(Iterable<? extends Autotransition> auts);
	public void deleteAll();
	void editAutotransition(Autotransition a);
}
