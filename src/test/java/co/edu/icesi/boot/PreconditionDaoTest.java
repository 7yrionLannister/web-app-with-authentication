package co.edu.icesi.boot;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import co.edu.icesi.FernandezDanielTaller1Application;
import co.edu.icesi.daos.PreconditionDao;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.repository.InstitutionRepositoryI;
import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Institution;
import co.edu.icesi.daos.AutotransitionDao;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FernandezDanielTaller1Application.class})
public class PreconditionDaoTest {
	private PreconditionDao preDao;
	private AutotransitionDao autDao;
	private InstitutionRepositoryI instRepo;
	private Institution inst;
	private Precondition pre;
	private Autotransition aut;

	@Autowired
	public PreconditionDaoTest(PreconditionDao preDao, AutotransitionDao autDao, InstitutionRepositoryI instRepo) {
		this.preDao = preDao;
		this.autDao = autDao;
		this.instRepo = instRepo;
	}

	@Test
	@Order(1)
	public void saveTest() {
		pre = new Precondition();
		
		inst = new Institution();
		inst.setInstAcademicserverurl("url");
		inst.setInstAcadprogrammedcoursesurl("url");
		String instName = "inst1";
		inst.setInstName(instName);
		inst = instRepo.save(inst);
		aut = new Autotransition();
		aut.setAutotranName("aut");
		aut.setAutotranIsactive("Y");
		aut.setAutotranLogicaloperand("AND");
		aut.setInstitution(inst);
		autDao.save(aut);

		pre.setAutotransition(aut);
		pre.setPreconLogicaloperand(""); // illegal
		pre.setAutotransition(aut);
		assertThrows(TransactionSystemException.class, () -> preDao.save(pre), "The preocondition has illegal attributes, commiting should not be succesfull");

		pre.setPreconId(0); // no regresa a cero despues de la excepcion, entonces lo pongo manualmente
		String logicalOperand = "AND";
		pre.setPreconLogicaloperand(logicalOperand);
		preDao.save(pre);

		List<Precondition> pres = preDao.getAll();

		assertEquals(1, pres.size());
		assertEquals(1, preDao.findAllByAutotransition(aut.getAutotranId()).size());
		//assertTrue(preDao.findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne().isEmpty()); // TODO mejorar prueba

		Precondition foundPre = preDao.get(pre.getPreconId()).get();
		Precondition expectedPre = pres.get(0);

		assertEquals(expectedPre.getPreconId(), foundPre.getPreconId());

		assertEquals(expectedPre.getPreconLogicaloperand(), foundPre.getPreconLogicaloperand());
		assertEquals(expectedPre.getPreconLogicaloperand(), logicalOperand);

		assertEquals(expectedPre.getAutotransition().getAutotranId(), foundPre.getAutotransition().getAutotranId());
		assertEquals(expectedPre.getAutotransition().getAutotranId(), aut.getAutotranId());

		assertEquals(expectedPre.getAutotransition().getAutotranName(), foundPre.getAutotransition().getAutotranName());
		assertEquals(expectedPre.getAutotransition().getAutotranName(), aut.getAutotranName());

		assertThrows(InvalidDataAccessApiUsageException.class, () -> preDao.save(expectedPre), "Bad api usage: you cannot update through save(Precondition) method, use update(Precondition) instead");
		pre = expectedPre;
	}

	@Test
	@Order(2)
	public void updateTest() {
		pre.setPreconLogicaloperand(""); // illegal
		assertThrows(TransactionSystemException.class, () -> preDao.update(pre), "The modified precondition has illegal attributes, commiting should not be succesfull");

		String logicalOperand = "OR";
		pre.setPreconLogicaloperand(logicalOperand);
		preDao.update(pre);

		List<Precondition> pres = preDao.getAll();

		assertEquals(1, pres.size());
		//assertTrue(preDao.findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne().isEmpty()); // TODO mejorar prueba

		Precondition foundPre = preDao.get(pre.getPreconId()).get();
		Precondition expectedPre = pres.get(0);

		assertEquals(expectedPre.getPreconId(), foundPre.getPreconId());

		assertEquals(expectedPre.getPreconLogicaloperand(), foundPre.getPreconLogicaloperand());
		assertEquals(expectedPre.getPreconLogicaloperand(), logicalOperand);

		pre = expectedPre;
	}

	@Test
	@Order(3)
	public void queryTest() {
		insertStuffForTesting();
		List<Precondition> pres = preDao.getAll();
		assertEquals(3, pres.size());
		List<Precondition> byAut = preDao.findAllByAutotransition(aut.getAutotranId());
		assertEquals(byAut.size(), 1);
	}

	@Test
	@Order(4)
	public void deleteTest() {
		assertTrue(preDao.get(pre.getPreconId()).isPresent());
		preDao.deleteById(pre.getPreconId());
		assertFalse(preDao.get(pre.getPreconId()).isPresent());
	}

	private void insertStuffForTesting() {
		Autotransition at = new Autotransition();
		at.setAutotranName("Aut2");
		at.setAutotranIsactive("Y");
		at.setAutotranLogicaloperand("AND");
		at.setInstitution(inst);
		autDao.save(at);

		Precondition a = new Precondition();
		a.setAutotransition(at);
		a.setPreconLogicaloperand("AND");
		preDao.save(a);

		a = new Precondition();
		a.setAutotransition(at);
		a.setPreconLogicaloperand("OR");
		preDao.save(a);

		pre = a;
	}
}
