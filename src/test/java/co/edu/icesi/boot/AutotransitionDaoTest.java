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
import co.edu.icesi.back.daos.AutotransitionDao;
import co.edu.icesi.back.model.Autotransition;
import co.edu.icesi.back.model.Institution;
import co.edu.icesi.back.repository.InstitutionRepositoryI;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FernandezDanielTaller1Application.class})
@DirtiesContext // Hace que el contexto no se cachee y las otras pruebas usen su propio contexto
public class AutotransitionDaoTest {
	private AutotransitionDao autDao;
	private InstitutionRepositoryI instRepo;
	private Autotransition aut;
	private Institution inst;
	
	@Autowired
	public AutotransitionDaoTest(AutotransitionDao autDao, InstitutionRepositoryI instRepo) {
		this.autDao = autDao;
		this.instRepo = instRepo;
	}
	
	@Test
	@Order(1)
	public void saveTest() {
		aut = new Autotransition();
		inst = new Institution();
		inst.setInstAcademicserverurl("url");
		inst.setInstAcadprogrammedcoursesurl("url");
		String instName = "inst1";
		inst.setInstName(instName);
		inst = instRepo.save(inst);
		aut.setInstitution(inst);
		aut.setAutotranName(""); // illegal
		String logicalOperand = "AND";
		aut.setAutotranLogicaloperand(logicalOperand);
		String isActive = "Y";
		aut.setAutotranIsactive(isActive);
		assertThrows(TransactionSystemException.class, () -> autDao.save(aut), "The autotransition has illegal attributes, commiting should not be succesfull");

		aut.setAutotranId(0); // no regresa a cero despues de la excepcion, entonces lo pongo manualmente
		String nonEmptyNameAut = "non_empty_name_aut";
		aut.setAutotranName(nonEmptyNameAut);
		autDao.save(aut);
		
		List<Autotransition> auts = autDao.getAll();
		
		assertTrue(auts.size() == 1);
		assertTrue(autDao.findAllByActive(isActive).size() == 1);
		assertTrue(autDao.findAllByInstitutionInstId(inst.getInstId()).size() == 1);
		
		Autotransition foundAut = autDao.findAllByName(nonEmptyNameAut).get(0);
		Autotransition expectedAut = auts.get(0);
		
		assertEquals(expectedAut.getAutotranId(), foundAut.getAutotranId());
		
		assertEquals(expectedAut.getAutotranName(), foundAut.getAutotranName());
		assertEquals(expectedAut.getAutotranName(), nonEmptyNameAut);
		
		assertEquals(expectedAut.getAutotranIsactive(), foundAut.getAutotranIsactive());
		assertEquals(expectedAut.getAutotranIsactive(), isActive);
		
		assertEquals(expectedAut.getAutotranLogicaloperand(), foundAut.getAutotranLogicaloperand());
		assertEquals(expectedAut.getAutotranLogicaloperand(), logicalOperand);
		
		assertEquals(expectedAut.getInstitution().getInstName(), foundAut.getInstitution().getInstName());
		assertEquals(expectedAut.getInstitution().getInstName(), inst.getInstName());
		assertEquals(expectedAut.getInstitution().getInstName(), instName);
		
		assertThrows(InvalidDataAccessApiUsageException.class, () -> autDao.save(expectedAut), "Bad api usage: you cannot update through save(Autotransition) method, use update(Autotransition) instead");
		aut = expectedAut;
	}

	@Test
	@Order(2)
	public void updateTest() {
		aut.setAutotranName(""); // illegal
		String logicalOperand = "OR";
		aut.setAutotranLogicaloperand(logicalOperand);
		String isActive = "N";
		aut.setAutotranIsactive(isActive);
		assertThrows(TransactionSystemException.class, () -> autDao.update(aut), "The modified autotransition has illegal attributes, commiting should not be succesfull");
		
		String nonEmptyNameAut = "non_empty_name_aut_MODIFIED";
		aut.setAutotranName(nonEmptyNameAut);
		autDao.update(aut);
		
		List<Autotransition> auts = autDao.getAll();
		
		assertEquals(1, auts.size());
		assertTrue(autDao.findAllByActive(isActive).size() == 1);
		
		Autotransition foundAut = autDao.findAllByName(nonEmptyNameAut).get(0);
		Autotransition expectedAut = auts.get(0);
		
		assertEquals(expectedAut.getAutotranId(), foundAut.getAutotranId());
		
		assertEquals(expectedAut.getAutotranName(), foundAut.getAutotranName());
		assertEquals(expectedAut.getAutotranName(), nonEmptyNameAut);
		
		assertEquals(expectedAut.getAutotranIsactive(), foundAut.getAutotranIsactive());
		assertEquals(expectedAut.getAutotranIsactive(), isActive);
		
		assertEquals(expectedAut.getAutotranLogicaloperand(), foundAut.getAutotranLogicaloperand());
		assertEquals(expectedAut.getAutotranLogicaloperand(), logicalOperand);
		
		aut = expectedAut;
	}
	
	@Test
	@Order(3)
	public void queryTest() {
		insertStuffForTesting();
		List<Autotransition> auts = autDao.getAll();
		assertEquals(5, auts.size());
		List<Autotransition> active = autDao.findAllByActive("Y");
		assertEquals(2, active.size());
		List<Autotransition> inactive = autDao.findAllByActive("N");
		assertEquals(3, inactive.size());
		List<Autotransition> ands = autDao.findAllByLogicalOperand("AND");
		assertEquals(2, ands.size());
		List<Autotransition> ors = autDao.findAllByLogicalOperand("OR");
		assertEquals(3, ors.size());
		List<Autotransition> nms = autDao.findAllByName("AUT");
		assertEquals(2, nms.size());
		List<Autotransition> insts = autDao.findAllByInstitutionInstId(inst.getInstId());
		assertEquals(4, insts.size());
	}
	
	@Test
	@Order(4)
	public void deleteTest() {
		assertTrue(autDao.findById(aut.getAutotranId()).isPresent());
		autDao.deleteById(aut.getAutotranId());
		assertFalse(autDao.findById(aut.getAutotranId()).isPresent());
	}
	
	private void insertStuffForTesting() {
		inst = new Institution();
		inst.setInstAcademicserverurl("url2");
		inst.setInstAcadprogrammedcoursesurl("url2");
		String instName = "inst2";
		inst.setInstName(instName);
		inst = instRepo.save(inst);
		
		Autotransition a = new Autotransition();
		a.setInstitution(inst);
		a.setAutotranName("2");
		a.setAutotranLogicaloperand("AND");
		a.setAutotranIsactive("Y");
		autDao.save(a);
		
		a = new Autotransition();
		a.setInstitution(inst);
		a.setAutotranName("3");
		a.setAutotranLogicaloperand("OR");
		a.setAutotranIsactive("N");
		autDao.save(a);
		
		a = new Autotransition();
		a.setInstitution(inst);
		a.setAutotranName("AUT");
		a.setAutotranLogicaloperand("OR");
		a.setAutotranIsactive("Y");
		autDao.save(a);
		
		a = new Autotransition();
		a.setInstitution(inst);
		a.setAutotranName("AUT");
		a.setAutotranLogicaloperand("AND");
		a.setAutotranIsactive("N");
		autDao.save(a);
	}
}
