package co.edu.icesi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Autotransition;
//import co.edu.icesi.repository.AutotransitionRepositoryI; // Workshop2
import co.edu.icesi.daos.AutotransitionDao; // Workshop3


@Service
public class AutotransitionService implements AutotransitionServiceI {

	//private AutotransitionRepositoryI autotransitionRepository; // Workshop2
	private AutotransitionDao autotransitionDao; // Workshop3

	//public AutotransitionService(AutotransitionRepositoryI autotransitionRepository) { //Workshop2
	public AutotransitionService(AutotransitionDao autotransitionDao) { //Workshop3
		//this.autotransitionRepository = autotransitionRepository; // Workshop2
		this.autotransitionDao = autotransitionDao; // Workshop3
	}
	
	@Override
	public <S extends Autotransition> S save(S autotransition) {
		//autotransitionRepository.save(autotransition); // Workshop2
		autotransitionDao.save(autotransition); // Workshop3
		return autotransition;
	}

	@Override
	public <S extends Autotransition> Iterable<S> saveAll(Iterable<S> auts) {
		for(Autotransition aut : auts) {
			save(aut);
		}
		return auts;
	}

	@Override
	public Optional<Autotransition> findById(Long id) {
		//return autotransitionRepository.findById(id); // Workshop2
		return autotransitionDao.get(id); // Workshop3
	}

	@Override
	public boolean existsById(Long id) {
		//return autotransitionRepository.existsById(id); // Workshop2
		return autotransitionDao.get(id).isPresent(); // Workshop3
	}

	@Override
	public Iterable<Autotransition> findAll() {
		//return autotransitionRepository.findAll(); // Workshop2
		return autotransitionDao.getAll(); // Workshop3
	}

	@Override
	public Iterable<Autotransition> findAllById(Iterable<Long> ids) {
		//return autotransitionRepository.findAllById(ids); // Workshop2
		return null; // Workshop3
	}

	@Override
	public long count() {
		//return autotransitionRepository.count(); // Workshop2
		return autotransitionDao.getAll().size(); // Workshop3
	}

	@Override
	public void deleteById(Long id) {
		//autotransitionRepository.deleteById(id); // Workshop2
		autotransitionDao.delete(autotransitionDao.get(id).orElse(null)); // Workshop3
	}

	@Override
	public void delete(Autotransition autotransition) {
		//autotransitionRepository.delete(autotransition); // Workshop2
		autotransitionDao.delete(autotransition); // Workshop3
	}

	@Override
	public void deleteAll(Iterable<? extends Autotransition> auts) {
		//autotransitionRepository.deleteAll(auts); // Workshop2
		// Workshop3
		for(Autotransition a: auts) {
			delete(a);
		}
	}

	@Override
	public void deleteAll() {
		//autotransitionDao.deleteAll(); // Workshop2
		deleteAll(autotransitionDao.getAll()); // Workshop3
	}

	@Override
	public void editAutotransition(Autotransition a) {
		autotransitionDao.update(a); // Workshop3
	}
}
