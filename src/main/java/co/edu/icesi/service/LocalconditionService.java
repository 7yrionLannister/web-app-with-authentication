package co.edu.icesi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Localcondition;
//import co.edu.icesi.repository.LocalconditionRepositoryI; // Workshop2
import co.edu.icesi.daos.LocalconditionDao; // Workshop3

@Service
public class LocalconditionService implements LocalconditionServiceI {

	//private LocalconditionRepositoryI localconditionRepository; // Workshop2
	private LocalconditionDao localconditionDao; // Workshop3
	
	//public LocalconditionService(LocalconditionRepositoryI localconditionRepository) { // Workshop2
	public LocalconditionService(LocalconditionDao localconditionDao) { // Workshop3
		//this.localconditionRepository = localconditionRepository; // Workshop2
		this.localconditionDao = localconditionDao; // Workshop3
	}
	
	@Override
	public <S extends Localcondition> S save(S localconotransition) {
		//localconditionRepository.save(localconotransition); // Workshop2
		localconditionDao.save(localconotransition); // Workshop3
		return localconotransition;
	}

	@Override
	public <S extends Localcondition> Iterable<S> saveAll(Iterable<S> localcons) {
		for(Localcondition localcon : localcons) {
			save(localcon);
		}
		return localcons;
	}

	@Override
	public Optional<Localcondition> findById(Long id) {
		//return localconditionRepository.findById(id); // Workshop2
		return localconditionDao.get(id); // Workshop3
	}

	@Override
	public boolean existsById(Long id) {
		//return localconditionRepository.existsById(id); // Workshop2
		return localconditionDao.get(id).isPresent(); // Workshop3
	}

	@Override
	public Iterable<Localcondition> findAll() {
		//return localconditionRepository.findAll(); // Workshop2
		return localconditionDao.getAll(); // Workshop3
	}

	@Override
	public Iterable<Localcondition> findAllById(Iterable<Long> ids) {
		//return localconditionRepository.findAllById(ids); // Workshop2
		return null; // Workshop3
	}

	@Override
	public long count() {
		//return localconditionRepository.count(); // Workshop2
		return localconditionDao.getAll().size(); // Workshop3
	}

	@Override
	public void deleteById(Long id) {
		//localconditionRepository.deleteById(id); // Workshop2
		localconditionDao.delete(findById(id).orElse(null)); // Workshop3
	}

	@Override
	public void delete(Localcondition localconotransition) {
		//localconditionRepository.delete(localconotransition); // Workshop2
		localconditionDao.delete(localconotransition); // Workshop3
	}

	@Override
	public void deleteAll(Iterable<? extends Localcondition> localcons) {
		//localconditionRepository.deleteAll(localcons); // Workshop2
		// Workshop3
		for(Localcondition l : localcons) {
			delete(l);
		}
	}

	@Override
	public void deleteAll() {
		//localconditionRepository.deleteAll(); // Workshop2
		deleteAll(findAll()); // Workshop3
	}

	@Override
	public void editLocalcondition(Localcondition l) {
		localconditionDao.update(l);
	}
}
