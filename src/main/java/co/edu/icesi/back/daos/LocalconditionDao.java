package co.edu.icesi.back.daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.back.model.Localcondition;

@Repository
@Scope("singleton")
public class LocalconditionDao implements Dao<Localcondition> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public LocalconditionDao() {
		
	}
	
	@Override
	public Optional<Localcondition> get(Long id) {
		return Optional.ofNullable(entityManager.find(Localcondition.class, id));
	}

	@Override
	public List<Localcondition> getAll() {
		Query query = entityManager.createQuery("SELECT l FROM Localcondition l");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Localcondition t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	@Override
	@Transactional
	public void update(Localcondition t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	@Override
	@Transactional
	public void deleteById(Long locId) {
		Localcondition loc = get(locId).orElse(null);
		executeInsideTransaction(entityManager -> entityManager.remove(loc));
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

	public List<Localcondition> findAllByThreshold(long thresholdId) {
		Query query = entityManager.createQuery("SELECT l FROM Localcondition l where l.threshold.thresId = :thresholdId");
		query.setParameter("thresholdId", thresholdId);
		return query.getResultList();
	}

	public List<Localcondition> findAllByPrecondition(long preconditionId) {
		Query query = entityManager.createQuery("SELECT l FROM Localcondition l where l.precondition.preconId = :preconditionId");
		query.setParameter("preconditionId", preconditionId);
		return query.getResultList();
	}
	
	public List<Localcondition> findAllByName(String name) {
		Query query = entityManager.createQuery("SELECT l FROM Localcondition l where l.loconColumnname = :name");
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	public List<Localcondition> findAllByType(String type) {
		Query query = entityManager.createQuery("SELECT l FROM Localcondition l where l.loconValuetype = :type");
		query.setParameter("type", type);
		return query.getResultList();
	}
}
