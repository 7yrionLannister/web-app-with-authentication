package co.edu.icesi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Localcondition;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.model.Threshold;
import co.edu.icesi.repository.LocalconditionRepositoryI;

@Service
public class LocalconditionService implements LocalconditionServiceI {

	private LocalconditionRepositoryI localconditionRepository;
	
	public LocalconditionService(LocalconditionRepositoryI localconditionRepository) {
		this.localconditionRepository = localconditionRepository;
	}
	
	@Override
	public <S extends Localcondition> S save(S localconotransition) {
		localconditionRepository.save(localconotransition);
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
		return localconditionRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return localconditionRepository.existsById(id);
	}

	@Override
	public Iterable<Localcondition> findAll() {
		return localconditionRepository.findAll();
	}

	@Override
	public Iterable<Localcondition> findAllById(Iterable<Long> ids) {
		return localconditionRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return localconditionRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		localconditionRepository.deleteById(id);
	}

	@Override
	public void delete(Localcondition localconotransition) {
		localconditionRepository.delete(localconotransition);
	}

	@Override
	public void deleteAll(Iterable<? extends Localcondition> localcons) {
		localconditionRepository.deleteAll(localcons);
	}

	@Override
	public void deleteAll() {
		localconditionRepository.deleteAll();
	}

	@Override
	public void editLocalcondition(long id, String colname, String keycol, String operator, String query, String table, String type, Precondition pre, Threshold th) {
		
		if(colname.contains(" ") || keycol.contains(" ") || !((operator.length() == 2 || operator.length() == 1) && operator.replace(">", "").replace("<", "").replace("=", "").isEmpty())) {
			throw new IllegalArgumentException("One of the arguments is not valid");
		}
		
		Localcondition localcon = findById(id).get();
		localcon.setLoconColumnname(colname);
		localcon.setLoconKeycolumn(keycol);
		localcon.setLoconOperator(operator);
		localcon.setLoconQuerystring(query);
		localcon.setLoconTablename(table);
		localcon.setLoconValuetype(type);
		localcon.setPrecondition(pre);
		localcon.setThreshold(th);
		save(localcon); // Agrego esto porque sino en las pruebas de integracion el cambio no se hace y es raro, SPRING ES RARO
	}
}
