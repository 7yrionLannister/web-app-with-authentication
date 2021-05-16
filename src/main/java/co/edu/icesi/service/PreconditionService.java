package co.edu.icesi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Precondition;
import co.edu.icesi.repository.PreconditionRepositoryI;

@Service
public class PreconditionService implements PreconditionServiceI {


	private PreconditionRepositoryI preconditionRepository;
	
	public PreconditionService(PreconditionRepositoryI preconditionRepository) {
		this.preconditionRepository = preconditionRepository;
	}
	
	@Override
	public <S extends Precondition> S save(S preotransition) {
		preconditionRepository.save(preotransition);
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
		return preconditionRepository.findById(id);
	}

	
	@Override
	public boolean existsById(Long id) {
		return preconditionRepository.existsById(id);
	}

	@Override
	public Iterable<Precondition> findAll() {
		return preconditionRepository.findAll();
	}

	
	@Override
	public Iterable<Precondition> findAllById(Iterable<Long> ids) {
		return preconditionRepository.findAllById(ids);
	}

	
	@Override
	public long count() {
		return preconditionRepository.count();
	}

	
	@Override
	public void deleteById(Long id) {
		preconditionRepository.deleteById(id);
	}

	
	@Override
	public void delete(Precondition preotransition) {
		preconditionRepository.delete(preotransition);
	}

	
	@Override
	public void deleteAll(Iterable<? extends Precondition> pres) {
		preconditionRepository.deleteAll(pres);
	}

	@Override
	public void deleteAll() {
		preconditionRepository.deleteAll();
	}

	@Override
	public void editPrecondition(long id, String logicalOperand) {
		if(!logicalOperand.equals("AND") && !logicalOperand.equals("OR")) {
			throw new IllegalArgumentException("One of the arguments is not valid");
		}
		Precondition pre = findById(id).get();
		pre.setPreconLogicaloperand(logicalOperand);
		save(pre); // Agrego esto porque sino en las pruebas de integracion el cambio no se hace y es raro, SPRING ES RARO
	}
}
