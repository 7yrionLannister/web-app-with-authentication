package co.edu.icesi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Userr;
import co.edu.icesi.repository.UserRepositoryI;

@Service
public class UserrService implements UserrServiceI {

	private UserRepositoryI userrRepository;
	
	public UserrService(UserRepositoryI userrRepository) {
		this.userrRepository = userrRepository;
	}
	
	@Override
	public <S extends Userr> S save(S instotransition) {
		return userrRepository.save(instotransition);
	}
	
	@Override
	public <S extends Userr> Iterable<S> saveAll(Iterable<S> insts) {
		for(Userr inst : insts) {
			save(inst);
		}
		return insts;
	}

	@Override
	public Optional<Userr> findById(long id) {
		return userrRepository.findById(id);
	}
	
	@Override
	public boolean existsById(Long id) {
		return userrRepository.existsById(id);
	}

	@Override
	public Iterable<Userr> findAll() {
		return userrRepository.findAll();
	}

	@Override
	public Iterable<Userr> findAllById(Iterable<Long> ids) {
		return userrRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return userrRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		userrRepository.deleteById(id);
	}

	@Override
	public void delete(Userr instotransition) {
		userrRepository.delete(instotransition);
	}

	@Override
	public void deleteAll(Iterable<? extends Userr> insts) {
		userrRepository.deleteAll(insts);
	}

	@Override
	public void deleteAll() {
		userrRepository.deleteAll();
	}
}
