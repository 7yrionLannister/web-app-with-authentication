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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import co.edu.icesi.FernandezDanielTaller1Application;
import co.edu.icesi.daos.ThresholdDao;
import co.edu.icesi.model.Threshold;
import co.edu.icesi.model.Institution;
import co.edu.icesi.repository.InstitutionRepositoryI;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FernandezDanielTaller1Application.class})
@DirtiesContext // Hace que el contexto no se cachee y las otras pruebas usen su propio contexto
public class ThresholdDaoTest {
	private ThresholdDao thrDao;
	private InstitutionRepositoryI instRepo;
	private Threshold thr;
	private Institution inst;
	
	@Autowired
	public ThresholdDaoTest(ThresholdDao thrDao, InstitutionRepositoryI instRepo) {
		this.thrDao = thrDao;
		this.instRepo = instRepo;
	}
	
	@Test
	@Order(1)
	public void saveTest() {		
		thr = new Threshold();
		inst = new Institution();
		inst.setInstAcademicserverurl("url");
		inst.setInstAcadprogrammedcoursesurl("url");
		String instName = "inst1";
		inst.setInstName(instName);
		inst = instRepo.save(inst);
		thr.setInstitution(inst);
		thr.setThresName(""); // illegal
		String value = "value1";
		thr.setThresValue(value);
		String type = "type1";
		thr.setThresValuetype(type);
		assertThrows(TransactionSystemException.class, () -> thrDao.save(thr), "The throtransition has illegal attributes, commiting should not be succesfull");

		thr.setThresId(0); // no regresa a cero despues de la excepcion, entonces lo pongo manualmente
		String nonEmptyName = "non_empty_name_thr";
		thr.setThresName(nonEmptyName);
		thrDao.save(thr);
		
		List<Threshold> thrs = thrDao.getAll();
		
		assertEquals(1, thrs.size());
		assertEquals(1, thrDao.findAllByInstitution(inst.getInstId()).size());
		assertEquals(1, thrDao.findAllByName(thr.getThresName()).size());
		assertEquals(1, thrDao.findAllByValue(thr.getThresValue()).size());
		assertEquals(1, thrDao.findAllByType(thr.getThresValuetype()).size());
		
		Threshold foundAut = thrDao.findAllByName(nonEmptyName).get(0);
		Threshold expectedAut = thrs.get(0);
		
		assertEquals(expectedAut.getThresId(), foundAut.getThresId());
		
		assertEquals(expectedAut.getThresName(), foundAut.getThresName());
		assertEquals(expectedAut.getThresName(), nonEmptyName);
		
		assertEquals(expectedAut.getInstitution().getInstId(), foundAut.getInstitution().getInstId());
		assertEquals(expectedAut.getInstitution().getInstId(), inst.getInstId());
		
		assertEquals(expectedAut.getThresValue(), foundAut.getThresValue());
		assertEquals(expectedAut.getThresValue(), value);
		
		assertEquals(expectedAut.getThresValuetype(), foundAut.getThresValuetype());
		assertEquals(expectedAut.getThresValuetype(), type);
		
		assertEquals(expectedAut.getInstitution().getInstName(), foundAut.getInstitution().getInstName());
		assertEquals(expectedAut.getInstitution().getInstName(), inst.getInstName());
		assertEquals(expectedAut.getInstitution().getInstName(), instName);
		
		assertThrows(InvalidDataAccessApiUsageException.class, () -> thrDao.save(expectedAut), "Bad api usage: you cannot update through save(Threshold) method, use update(Threshold) instead");
		thr = expectedAut;
	}

	@Test
	@Order(2)
	public void updateTest() {
		thr.setThresName(""); // illegal
		String value = "val1";
		thr.setThresValue(value);
		String type = "type1";
		thr.setThresValuetype(type);
		assertThrows(TransactionSystemException.class, () -> thrDao.update(thr), "The modified throtransition has illegal attributes, commiting should not be succesfull");
		
		String nonEmptyName = "non_empty_name_thr_MODIFIED";
		thr.setThresName(nonEmptyName);
		thrDao.update(thr);
		
		List<Threshold> thrs = thrDao.getAll();
		
		assertEquals(1, thrs.size());
		assertEquals(1, thrDao.findAllByValue(value).size());
		
		Threshold foundAut = thrDao.findAllByName(nonEmptyName).get(0);
		Threshold expectedAut = thrs.get(0);
		
		assertEquals(expectedAut.getThresId(), foundAut.getThresId());
		
		assertEquals(expectedAut.getInstitution().getInstId(), foundAut.getInstitution().getInstId());
		assertEquals(expectedAut.getInstitution().getInstId(), inst.getInstId());
		
		assertEquals(expectedAut.getInstitution().getInstName(), foundAut.getInstitution().getInstName());
		assertEquals(expectedAut.getInstitution().getInstName(), inst.getInstName());
		
		assertEquals(expectedAut.getThresName(), foundAut.getThresName());
		assertEquals(expectedAut.getThresName(), nonEmptyName);
		
		assertEquals(expectedAut.getThresValue(), foundAut.getThresValue());
		assertEquals(expectedAut.getThresValue(), value);
		
		assertEquals(expectedAut.getThresValuetype(), foundAut.getThresValuetype());
		assertEquals(expectedAut.getThresValuetype(), type);
		
		thr = expectedAut;
	}
	
	@Test
	@Order(3)
	public void queryTest() {
		insertStuffForTesting();
		List<Threshold> thrs = thrDao.getAll();
		assertEquals(3, thrs.size());
		List<Threshold> byInst = thrDao.findAllByInstitution(inst.getInstId());
		assertEquals(2, byInst.size());
		List<Threshold> byName = thrDao.findAllByName(thr.getThresName());
		assertEquals(1, byName.size());
		List<Threshold> byType = thrDao.findAllByType(thr.getThresValuetype());
		assertEquals(1, byType.size());
		List<Threshold> byVal = thrDao.findAllByValue(thr.getThresValue());
		assertEquals(2, byVal.size());
	}
	
	@Test
	@Order(4)
	public void deleteTest() {
		assertTrue(thrDao.get(thr.getThresId()).isPresent());
		thrDao.deleteById(thr.getThresId());
		assertFalse(thrDao.get(thr.getThresId()).isPresent());
	}
	
	private void insertStuffForTesting() {
		inst = new Institution();
		inst.setInstAcademicserverurl("url2");
		inst.setInstAcadprogrammedcoursesurl("url2");
		String instName = "inst2";
		inst.setInstName(instName);
		inst = instRepo.save(inst);
		
		Threshold a = new Threshold();
		a.setInstitution(inst);
		a.setThresName("ThrXYZ");
		String value = "valueeee2";
		a.setThresValue(value);
		String type = "typeeeee1";
		a.setThresValuetype(type);
		thrDao.save(a);
		
		a = new Threshold();
		a.setInstitution(inst);
		a.setThresName("ThrABC");
		value = "valueeee2";
		a.setThresValue(value);
		type = "typeeeee3";
		a.setThresValuetype(type);
		thrDao.save(a);
		thr = a;
	}
}
