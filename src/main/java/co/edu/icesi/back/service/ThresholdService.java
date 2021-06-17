package co.edu.icesi.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.back.model.Threshold;
//import co.edu.icesi.back.repository.ThresholdRepositoryI; // Workshop2
import co.edu.icesi.back.daos.ThresholdDao; // Workshop3

@Service
public class ThresholdService implements ThresholdServiceI {
	//private ThresholdRepositoryI thresholdRepository; // Workshop2
	private ThresholdDao thresholdDao; // Workshop3
	
	//public ThresholdService(ThresholdRepositoryI thresholdRepository) { // Workshop2
	public ThresholdService(ThresholdDao thresholdDao) { // Workshop3
		//this.thresholdRepository = thresholdRepository; // Worshop2
		this.thresholdDao = thresholdDao; // Worshop3
	}
	
	@Override
	public <S extends Threshold> S save(S threshold) {
		//thresholdRepository.save(threshold); // Workshop2
		thresholdDao.save(threshold); // Workshop3
		return threshold;
	}

	
	@Override
	public <S extends Threshold> Iterable<S> saveAll(Iterable<S> thrs) {
		for(Threshold thr : thrs) {
			save(thr);
		}
		return thrs;
	}

	
	@Override
	public Optional<Threshold> findById(Long id) {
		//return thresholdRepository.findById(id); // Workshop2
		return thresholdDao.get(id); // Workshop3
	}

	
	@Override
	public boolean existsById(Long id) {
		//return thresholdRepository.existsById(id); // Workshop2
		return thresholdDao.get(id).isPresent(); // Workshop3
	}

	
	@Override
	public Iterable<Threshold> findAll() {
		//return thresholdRepository.findAll(); // Workshop2
		return thresholdDao.getAll(); // Workshop3
	}

	
	@Override
	public Iterable<Threshold> findAllById(Iterable<Long> ids) {
		//return thresholdRepository.findAllById(ids); // Workshop2
		return null; // Workshop3
	}

	
	@Override
	public long count() {
		//return thresholdRepository.count(); // Workshop2
		return thresholdDao.getAll().size(); // Workshop3
	}

	
	@Override
	public void deleteById(Long id) {
		//thresholdRepository.deleteById(id); // Workshop2
		thresholdDao.deleteById(id); // Workshop3
	}

	
	@Override
	public void delete(Threshold threshold) {
		//thresholdRepository.delete(threshold); // Workshop2
		deleteById(threshold.getThresId()); // Workshop3
	}

	
	@Override
	public void deleteAll(Iterable<? extends Threshold> thrs) {
		//thresholdRepository.deleteAll(thrs); // Workshop2
		// Workshop3
		for(Threshold t : thrs) {
			delete(t);
		}
	}

	
	@Override
	public void deleteAll() {
		//thresholdRepository.deleteAll(); // Workshop2
		deleteAll(findAll()); // Workshop3
	}

	@Override
	public void editThreshold(Threshold t) {
		thresholdDao.update(t);
	}
}
