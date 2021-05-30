package co.edu.icesi.daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.Convert;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Autotransition;


@Repository
@Scope("singleton")
public class AutotransitionDao implements Dao<Autotransition> {
	
	@PersistenceContext
	private EntityManager entityManager;

	public AutotransitionDao() {

	}

	@Override
	public Optional<Autotransition> get(Long id) {
		return Optional.ofNullable(entityManager.find(Autotransition.class, id));
	}
	
	public Optional<Autotransition> findById(Long id) {
		return Optional.ofNullable(entityManager.find(Autotransition.class, id));
	}

	@Override
	public List<Autotransition> getAll() {
		Query query = entityManager.createQuery("SELECT a FROM Autotransition a");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Autotransition aut) {
		executeInsideTransaction(entityManager -> entityManager.persist(aut));
	}

	@Override
	@Transactional
	public void update(Autotransition aut) {
		executeInsideTransaction(entityManager -> entityManager.merge(aut));
	}

	@Override
	@Transactional
	public void deleteById(Long autId) {
		Autotransition aut = get(autId).orElse(null);
		executeInsideTransaction(entityManager -> entityManager.remove(aut));
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		//EntityTransaction tx = entityManager.getTransaction();
		try {
			//tx.begin();
			action.accept(entityManager);
			//tx.commit(); 
		}
		catch (RuntimeException e) {
			//tx.rollback();
			throw e;
		}
	}

	public List<Autotransition> findAllByInstitutionInstId(Long institutionId) {
		Query query = entityManager.createQuery("SELECT a FROM Autotransition a WHERE a.institution.instId = :institutionId");
		query.setParameter("institutionId", institutionId);
		return query.getResultList();
	}
	
	public List<Autotransition> findAllByName(String name) {
		Query query = entityManager.createQuery("SELECT a FROM Autotransition a WHERE a.autotranName = :name");
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	public List<Autotransition> findAllByActive(String active) {
		Query query = entityManager.createQuery("SELECT a FROM Autotransition a WHERE a.autotranIsactive = :active");
		query.setParameter("active", active);
		return query.getResultList();
	}
	
	public List<Autotransition> findAllByLogicalOperand(String operand) {
		Query query = entityManager.createQuery("SELECT a FROM Autotransition a WHERE a.autotranLogicaloperand = :operand");
		query.setParameter("operand", operand);
		return query.getResultList();
	}
}
