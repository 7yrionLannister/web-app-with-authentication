package co.edu.icesi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Threshold;
import co.edu.icesi.repository.ThresholdRepositoryI;

@Service
public class ThresholdService implements ThresholdServiceI {
	private ThresholdRepositoryI thresholdRepository;
	
	public ThresholdService(ThresholdRepositoryI thresholdRepository) {
		this.thresholdRepository = thresholdRepository;
	}
	
	@Override
	public <S extends Threshold> S save(S threshold) {
		thresholdRepository.save(threshold);
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
		return thresholdRepository.findById(id);
	}

	
	@Override
	public boolean existsById(Long id) {
		return thresholdRepository.existsById(id);
	}

	
	@Override
	public Iterable<Threshold> findAll() {
		return thresholdRepository.findAll();
	}

	
	@Override
	public Iterable<Threshold> findAllById(Iterable<Long> ids) {
		return thresholdRepository.findAllById(ids);
	}

	
	@Override
	public long count() {
		return thresholdRepository.count();
	}

	
	@Override
	public void deleteById(Long id) {
		thresholdRepository.deleteById(id);
	}

	
	@Override
	public void delete(Threshold threshold) {
		thresholdRepository.delete(threshold);
	}

	
	@Override
	public void deleteAll(Iterable<? extends Threshold> thrs) {
		thresholdRepository.deleteAll(thrs);
	}

	
	@Override
	public void deleteAll() {
		thresholdRepository.deleteAll();
	}

	@Override
	public void editThreshold(long id, String name, String value, String vtype) {
		if(name.isEmpty() || value.isEmpty()) {
			throw new IllegalArgumentException("One of the arguments is not valid");
		}
		
		Threshold thr = findById(id).get();
		thr.setThresName(name);
		thr.setThresValue(value);
		thr.setThresValuetype(vtype);
		save(thr); // Agrego esto porque sino en las pruebas de integracion el cambio no se hace y es raro, SPRING ES RARO
	}
}
