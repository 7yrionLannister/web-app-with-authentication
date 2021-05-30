package co.edu.icesi.daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Threshold;

@Repository
@Scope("singleton")
public class ThresholdDao implements Dao<Threshold> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public ThresholdDao() {
		
	}
	
	@Override
	public Optional<Threshold> get(Long id) {
		return Optional.ofNullable(entityManager.find(Threshold.class, id));
	}

	@Override
	public List<Threshold> getAll() {
		Query query = entityManager.createQuery("SELECT t FROM Threshold t");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Threshold t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	@Override
	@Transactional
	public void update(Threshold t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	@Override
	@Transactional
	public void deleteById(Long thrId) {
		Threshold thr = get(thrId).orElse(null);
		executeInsideTransaction(entityManager -> entityManager.remove(thr));
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

	public List<Threshold> findAllByInstitution(long instId) {
		Query query = entityManager.createQuery("SELECT t FROM Threshold t WHERE t.institution.instId = :instId");
		query.setParameter("instId", instId);
		return query.getResultList();
	}
	
	public List<Threshold> findAllByName(String name) {
		Query query = entityManager.createQuery("SELECT t FROM Threshold t WHERE t.thresName = :name");
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	public List<Threshold> findAllByValue(String value) {
		Query query = entityManager.createQuery("SELECT t FROM Threshold t WHERE t.thresValue = :value");
		query.setParameter("value", value);
		return query.getResultList();
	}
	
	public List<Threshold> findAllByType(String type) {
		Query query = entityManager.createQuery("SELECT t FROM Threshold t WHERE t.thresValuetype = :type");
		query.setParameter("type", type);
		return query.getResultList();
	}
}
