package co.edu.icesi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Precondition;
//import co.edu.icesi.repository.PreconditionRepositoryI;
import co.edu.icesi.daos.PreconditionDao;

@Service
public class PreconditionService implements PreconditionServiceI {


	//private PreconditionRepositoryI preconditionRepository; // Workshop2
	private PreconditionDao preconditionDao; // Workshop3
	
	//public PreconditionService(PreconditionRepositoryI preconditionRepository) { // Workshop2
	public PreconditionService(PreconditionDao preconditionDao) { // Workshop3
		//this.preconditionRepository = preconditionRepository; // Workshop2
		this.preconditionDao = preconditionDao; // Workshop3
	}
	
	@Override
	public <S extends Precondition> S save(S preotransition) {
		//preconditionRepository.save(preotransition); // Workshop2
		preconditionDao.save(preotransition); // Workshop3
		return preotransition;
	}

	@Override
	public <S extends Precondition> Iterable<S> saveAll(Iterable<S> pres) {
		for(Precondition pre : pres) {
			save(pre);
		}
		return pres;
	}

	
	@Override
	public Optional<Precondition> findById(Long id) {
		//return preconditionRepository.findById(id); // Workshop2
		return preconditionDao.get(id); // Workshop3
	}

	
	@Override
	public boolean existsById(Long id) {
		//return preconditionRepository.existsById(id); // Workshop2
		return findById(id).isPresent(); // Workshop3
	}

	@Override
	public Iterable<Precondition> findAll() {
		//return preconditionRepository.findAll(); // Workshop2
		return preconditionDao.getAll(); // Workshop3
	}

	
	@Override
	public Iterable<Precondition> findAllById(Iterable<Long> ids) {
		//return preconditionRepository.findAllById(ids); // Workshop2
		return null; // Workshop3
	}

	
	@Override
	public long count() {
		//return preconditionRepository.count(); // Workshop2
		return preconditionDao.getAll().size(); // Workshop3
	}

	
	@Override
	public void deleteById(Long id) {
		//preconditionRepository.deleteById(id); //Workshop2
		preconditionDao.deleteById(id); //Workshop3
	}

	
	@Override
	public void delete(Precondition precondition) {
		//preconditionRepository.delete(preotransition); // Workshop2
		deleteById(precondition.getPreconId()); // Workshop3
	}

	
	@Override
	public void deleteAll(Iterable<? extends Precondition> pres) {
		//preconditionRepository.deleteAll(pres); //Workshop2
		//Workshop3
		for(Precondition p : pres) {
			delete(p);
		}
	}

	@Override
	public void deleteAll() {
		//preconditionRepository.deleteAll(); // Workshop2
		// Workshop3
		deleteAll(preconditionDao.getAll());
	}

	@Override
	public void editPrecondition(Precondition p) {
		preconditionDao.update(p);
	}
}
