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
import co.edu.icesi.daos.AutotransitionDao;
import co.edu.icesi.daos.LocalconditionDao;
import co.edu.icesi.daos.PreconditionDao;
import co.edu.icesi.daos.ThresholdDao;
import co.edu.icesi.model.Localcondition;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.model.Threshold;
import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Institution;
import co.edu.icesi.repository.InstitutionRepositoryI;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FernandezDanielTaller1Application.class})
public class LocalconditionDaoTest {
	private LocalconditionDao locDao;
	private ThresholdDao thrDao;
	private InstitutionRepositoryI instRepo;
	private Localcondition loc;
	private Institution inst;
	private Precondition pre;
	private PreconditionDao preDao;
	private Autotransition aut;
	private AutotransitionDao autDao;
	private Threshold thr;

	@Autowired
	public LocalconditionDaoTest(LocalconditionDao locDao, InstitutionRepositoryI instRepo, ThresholdDao thrDao, AutotransitionDao autDao, PreconditionDao preDao) {
		this.locDao = locDao;
		this.instRepo = instRepo;
		this.thrDao = thrDao;
		this.autDao = autDao;
		this.preDao = preDao;
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
		String logicalOperand = "AND";
		pre.setPreconLogicaloperand(logicalOperand);
		preDao.save(pre);

		loc = new Localcondition();
		String value = "v1";
		loc.setLoconValuetype(value);
		loc.setPrecondition(pre);
		
		thr = new Threshold();
		inst = new Institution();
		inst.setInstAcademicserverurl("url");
		inst.setInstAcadprogrammedcoursesurl("url");
		instName = "inst1";
		inst.setInstName(instName);
		inst = instRepo.save(inst);
		thr.setInstitution(inst);
		value = "value1";
		thr.setThresValue(value);
		String type = "type1";
		thr.setThresValuetype(type);
		String nonEmptyName = "non_empty_name_thr";
		thr.setThresName(nonEmptyName);
		thrDao.save(thr);
		
		loc.setThreshold(thr);
		String table = "ATable";
		loc.setLoconTablename(table);
		String colKey = "colKey";
		loc.setLoconKeycolumn(colKey);
		loc.setLoconOperator(""); // illegal
		String valueType = "tipo";
		loc.setLoconValuetype(valueType);
		String name = "name";
		loc.setLoconColumnname(name);

		assertThrows(TransactionSystemException.class, () -> locDao.save(loc), "The locotransition has illegal attributes, commiting should not be succesfull");

		loc.setLoconId(0); // no regresa a cero despues de la excepcion, entonces lo pongo manualmente
		String operator = "==";
		loc.setLoconOperator(operator);
		locDao.save(loc);

		List<Localcondition> locs = locDao.getAll();

		assertEquals(1, locs.size());
		assertEquals(1, locDao.findAllByPrecondition(pre.getPreconId()).size());
		assertEquals(1, locDao.findAllByName(loc.getLoconColumnname()).size());
		assertEquals(1, locDao.findAllByThreshold(thr.getThresId()).size());
		assertEquals(1, locDao.findAllByType(loc.getLoconValuetype()).size());

		Localcondition foundLoc = locDao.findAllByName(loc.getLoconColumnname()).get(0);
		Localcondition expectedLoc = locs.get(0);

		assertEquals(expectedLoc.getLoconId(), foundLoc.getLoconId());

		assertEquals(expectedLoc.getLoconColumnname(), foundLoc.getLoconColumnname());

		assertEquals(expectedLoc.getLoconValuetype(), foundLoc.getLoconValuetype());

		assertEquals(expectedLoc.getLoconOperator(), foundLoc.getLoconOperator());
		assertEquals(expectedLoc.getLoconOperator(), operator);

		assertEquals(expectedLoc.getPrecondition().getPreconId(), foundLoc.getPrecondition().getPreconId());
		assertEquals(expectedLoc.getPrecondition().getPreconId(), pre.getPreconId());

		assertThrows(InvalidDataAccessApiUsageException.class, () -> locDao.save(expectedLoc), "Bad api usage: you cannot update locough save(Localcondition) method, use update(Localcondition) instead");
		loc = expectedLoc;
	}

	@Test
	@Order(2)
	public void updateTest() {
		String value = "vvvv1";
		loc.setLoconValuetype(value);
		loc.setPrecondition(pre);
		Threshold th = thrDao.get(thr.getThresId()).get();
		loc.setThreshold(th);
		String table = "ATable";
		loc.setLoconTablename(table);
		String colKey = "colKey";
		loc.setLoconKeycolumn(colKey);
		loc.setLoconOperator(""); // illegal
		String valueType = "tipo";
		loc.setLoconValuetype(valueType);
		String name = "name1";
		loc.setLoconColumnname(name);
		assertThrows(TransactionSystemException.class, () -> locDao.update(loc), "The modified locotransition has illegal attributes, commiting should not be succesfull");

		String operator = "==";
		loc.setLoconOperator(operator);
		locDao.update(loc);

		List<Localcondition> locs = locDao.getAll();

		assertEquals(1, locs.size());
		assertEquals(1, locDao.findAllByPrecondition(pre.getPreconId()).size());
		assertEquals(1, locDao.findAllByName(loc.getLoconColumnname()).size());
		assertEquals(1, locDao.findAllByThreshold(thr.getThresId()).size());
		assertEquals(1, locDao.findAllByType(loc.getLoconValuetype()).size());

		Localcondition foundLoc = locDao.findAllByName(loc.getLoconColumnname()).get(0);
		Localcondition expectedLoc = locs.get(0);

		assertEquals(expectedLoc.getLoconId(), foundLoc.getLoconId());

		assertEquals(expectedLoc.getLoconColumnname(), foundLoc.getLoconColumnname());

		assertEquals(expectedLoc.getLoconValuetype(), foundLoc.getLoconValuetype());

		assertEquals(expectedLoc.getLoconOperator(), foundLoc.getLoconOperator());
		assertEquals(expectedLoc.getLoconOperator(), operator);

		assertEquals(expectedLoc.getPrecondition().getPreconId(), foundLoc.getPrecondition().getPreconId());
		assertEquals(expectedLoc.getPrecondition().getPreconId(), pre.getPreconId());

		loc = expectedLoc;
	}

	@Test
	@Order(3)
	public void queryTest() {
		insertStuffForTesting();
		List<Localcondition> locs = locDao.getAll();
		assertEquals(3, locs.size());
		List<Localcondition> byPre = locDao.findAllByPrecondition(loc.getPrecondition().getPreconId());
		assertEquals(3, byPre.size());
		List<Localcondition> byName = locDao.findAllByName(loc.getLoconColumnname());
		assertEquals(1, byName.size());
		List<Localcondition> byType = locDao.findAllByType(loc.getLoconValuetype());
		assertEquals(1, byType.size());
		List<Localcondition> byThr = locDao.findAllByThreshold(loc.getThreshold().getThresId());
		assertEquals(3, byThr.size());
	}

	@Test
	@Order(4)
	public void deleteTest() {
		assertTrue(locDao.get(loc.getLoconId()).isPresent());
		locDao.deleteById(loc.getLoconId());
		assertFalse(locDao.get(loc.getLoconId()).isPresent());
	}

	private void insertStuffForTesting() {
		loc = new Localcondition();
		String value = "v1";
		loc.setLoconValuetype(value);
		loc.setPrecondition(pre);
		loc.setThreshold(thr);
		String table = "ATable";
		loc.setLoconTablename(table);
		String colKey = "colKey";
		loc.setLoconKeycolumn(colKey);
		String valueType = "tipo";
		loc.setLoconValuetype(valueType);
		String name = "name";
		loc.setLoconColumnname(name);
		String operator = "==";
		loc.setLoconOperator(operator);
		locDao.save(loc);
		
		loc = new Localcondition();
		value = "v2";
		loc.setLoconValuetype(value);
		loc.setPrecondition(pre);
		loc.setThreshold(thr);
		table = "ATable2";
		loc.setLoconTablename(table);
		colKey = "colKey";
		loc.setLoconKeycolumn(colKey);
		valueType = "tipo2";
		loc.setLoconValuetype(valueType);
		name = "name2";
		loc.setLoconColumnname(name);
		operator = ">";
		loc.setLoconOperator(operator);
		locDao.save(loc);
	}
}
