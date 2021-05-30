package co.edu.icesi.daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Precondition;


@Repository
@Scope("singleton")
public class PreconditionDao implements Dao<Precondition> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Optional<Precondition> get(Long id) {
		return Optional.ofNullable(entityManager.find(Precondition.class, id));
	}

	@Override
	public List<Precondition> getAll() {
		Query query = entityManager.createQuery("SELECT p FROM Precondition p");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Precondition t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	@Override
	@Transactional
	public void update(Precondition t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	@Override
	@Transactional
	public void delete(Precondition t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
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

	public List<Precondition> findAllByAutotransition(Long autotranId) {
		Query query = entityManager.createQuery("SELECT p FROM Precondition p WHERE p.autotransition.autotranId = :autotranId");
		query.setParameter("autotranId", autotranId);
		return query.getResultList();
	}
	
	public List<Precondition> findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne() {
		String q = "SELECT COUNT(*) FROM (SELECT t FROM Threshold t WHERE t.thresValue > 1 GROUP BY localconditions)";
		//Query query = entityManager.createQuery("SELECT p FROM Precondition p WHERE p.localconditions.threshold.thresValue > 1");
		Query query = entityManager.createQuery(q);
		return query.getResultList();
	}
}
