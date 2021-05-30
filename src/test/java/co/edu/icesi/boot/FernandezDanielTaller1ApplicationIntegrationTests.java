package co.edu.icesi.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
//import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
/*import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;*/
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import co.edu.icesi.FernandezDanielTaller1Application;
import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Institution;
import co.edu.icesi.model.Localcondition;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.model.Threshold;
/*import co.edu.icesi.repository.AutotransitionRepositoryI;
import co.edu.icesi.repository.InstitutionRepositoryI;
import co.edu.icesi.repository.LocalconditionRepositoryI;
import co.edu.icesi.repository.PreconditionRepositoryI;
import co.edu.icesi.repository.ThresholdRepositoryI;*/
import co.edu.icesi.service.AutotransitionService;
import co.edu.icesi.service.InstitutionService;
import co.edu.icesi.service.LocalconditionService;
import co.edu.icesi.service.PreconditionService;
import co.edu.icesi.service.ThresholdService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FernandezDanielTaller1Application.class)
public class FernandezDanielTaller1ApplicationIntegrationTests {
	/*@Mock
	private AutotransitionRepositoryI autotransitionRepositoryMock;
	@InjectMocks*/
	private AutotransitionService autotransitionService;
	private Autotransition autotransitionId;
	
	/*@Mock
	private InstitutionRepositoryI institutionRepositoryMock;
	@InjectMocks*/
	private InstitutionService institutionService;
	private Institution institutionId;
	
	/*@Mock
	private PreconditionRepositoryI preconditionRepositoryMock;
	@InjectMocks*/
	private PreconditionService preconditionService;
	private Precondition preconditionId;
	
	/*@Mock
	private ThresholdRepositoryI thresholdRepositoryMock;
	@InjectMocks*/
	private ThresholdService thresholdService;
	private Threshold thresholdId;
	
	/*@Mock
	private LocalconditionRepositoryI localconditionRepositoryMock;
	@InjectMocks*/
	private LocalconditionService localconditionService;
	private Localcondition localconditionId;
	
	@Autowired
	public FernandezDanielTaller1ApplicationIntegrationTests(AutotransitionService autotransitionService, InstitutionService institutionService, PreconditionService preconditionService, ThresholdService thresholdService, LocalconditionService localconditionService) {
		this.autotransitionService = autotransitionService;
		this.institutionService = institutionService;
		this.preconditionService = preconditionService;
		this.thresholdService = thresholdService;
		this.localconditionService = localconditionService;
	}
	
	@BeforeAll
	public void setup() {
		Institution inst = new Institution();
		inst.setInstAcademicserverurl("url");
		inst.setInstAcadprogrammedcoursesurl("url");
		inst.setInstName("inst1");
		institutionId = institutionService.save(inst);
		//when(institutionRepositoryMock.findById(0l)).thenReturn(Optional.of(inst));
	}
	
	// PUNTO 5
	
	// PUNTO A
	
	@Test
	@Order(1)
	public void saveAutotransitionTest() {
		Autotransition aut = new Autotransition();
		aut.setPreconditions(new ArrayList<>()); // no se inicializa en el constructor, asi que uso el setter
		
		//when(autotransitionRepositoryMock.findById(0l)).thenReturn(Optional.of(aut));
		Institution inst = institutionService.findById(institutionId.getInstId()).get();
		
		aut.setInstitution(inst);
		String nonEmptyNameAut = "non_empty_name_aut";
		aut.setAutotranName(nonEmptyNameAut);
		String logicalOperand = "AND";
		aut.setAutotranLogicaloperand(logicalOperand);
		String isActive = "Y";
		aut.setAutotranIsactive(isActive);
		
		autotransitionId = autotransitionService.save(aut);
		
		// enunciado
		Autotransition found = autotransitionService.findById(autotransitionId.getAutotranId()).get(); 
		assertFalse(found.getAutotranName().isEmpty());
		assertEquals(nonEmptyNameAut, found.getAutotranName());
		assertEquals(logicalOperand, found.getAutotranLogicaloperand());
		assertEquals(isActive, found.getAutotranIsactive());
		/*
		verify(autotransitionRepositoryMock).save(aut);
		verify(autotransitionRepositoryMock).findById(0l);
		verify(institutionRepositoryMock).findById(0l);*/
	}
	
	@Test
	@Order(2)
	public void editAutotransitionTest() {
		String aStringToTest = "AStringToTest";
		autotransitionId.setAutotranName(aStringToTest);
		autotransitionId.setAutotranIsactive(null);
		autotransitionId.setAutotranLogicaloperand("");
		assertThrows(TransactionSystemException.class, () -> autotransitionService.editAutotransition(autotransitionId));
		
		String logicalOperator = "OR";
		String isActive = "N";
		autotransitionId.setAutotranIsactive("N");
		autotransitionId.setAutotranName(aStringToTest);
		autotransitionId.setAutotranLogicaloperand(logicalOperator);
		autotransitionService.editAutotransition(autotransitionId);
		Autotransition aut = autotransitionService.findById(autotransitionId.getAutotranId()).get();
		assertEquals(isActive, aut.getAutotranIsactive());
		assertEquals(logicalOperator, aut.getAutotranLogicaloperand());
		assertEquals(aStringToTest, aut.getAutotranName());
		
		//verify(autotransitionRepositoryMock, VerificationModeFactory.times(3)).findById(0l); // uno en el metodo con @Order(1) y dos aqui (uno por el edit y otro para obtenerlo aqui)
	}
	
	// PUNTO B
	
	@Test
	@Order(3)
	public void savePreconditionTest() {
		// se debe validar que exista la autotransicion antes de guardarla. En caso de que no, ocurrira una excepcion de tipo NoSuchElementException
		autotransitionService.findById(autotransitionId.getAutotranId()).get();
		Precondition pre = new Precondition();
		//when(preconditionRepositoryMock.findById(0l)).thenReturn(Optional.of(pre));
		
		String logicalOperand = "AND";
		pre.setPreconLogicaloperand(logicalOperand);
		pre.setAutotransition(autotransitionId);
		assertEquals(logicalOperand, pre.getPreconLogicaloperand());
		
		preconditionId = preconditionService.save(pre);
		
		
		// enunciado
		Precondition found = preconditionService.findById(preconditionId.getPreconId()).get(); 
		assertEquals(logicalOperand, found.getPreconLogicaloperand());
		/*
		verify(preconditionRepositoryMock).save(pre);
		verify(preconditionRepositoryMock).findById(0l);
		verify(autotransitionRepositoryMock, VerificationModeFactory.times(4)).findById(0l); // uno mas que en el metodo con @Order(2)*/
	}

	@Test
	@Order(4)
	public void editPreconditionTest() {
		preconditionId.setPreconLogicaloperand("");
		assertThrows(TransactionSystemException.class, () -> preconditionService.editPrecondition(preconditionId));
		
		String logicalOperand = "OR";
		preconditionId.setPreconLogicaloperand(logicalOperand);
		preconditionService.editPrecondition(preconditionId);
		Precondition pre = preconditionService.findById(preconditionId.getPreconId()).get();
		
		assertEquals(logicalOperand, pre.getPreconLogicaloperand());
		
		//verify(preconditionRepositoryMock, VerificationModeFactory.times(3)).findById(0l);
	}
	
	// PUNTO C
	
	@Test
	@Order(5)
	public void saveThresholdTest() {
		Threshold th = new Threshold();
		Institution inst = institutionService.findById(institutionId.getInstId()).get();
		th.setInstitution(inst);
		//when(thresholdRepositoryMock.findById(0l)).thenReturn(Optional.of(th));
		
		String thName = "thName";
		th.setThresName(thName);
		String thValue = "thValue";
		th.setThresValue(thValue);
		String thType = "String";
		th.setThresValuetype(thType);
		
		thresholdId = thresholdService.save(th);
		
		// enunciado
		Threshold found = thresholdService.findById(thresholdId.getThresId()).get();
		assertFalse(found.getThresName().isEmpty());
		assertEquals(thName, found.getThresName());
		assertFalse(found.getThresValue().isEmpty());
		assertEquals(thValue, found.getThresValue());
		assertEquals(thType, found.getThresValuetype());
		/*
		verify(thresholdRepositoryMock).save(th);
		verify(thresholdRepositoryMock).findById(0l);
		verify(institutionRepositoryMock, VerificationModeFactory.times(2)).findById(0l);*/
	}
	
	@Test
	@Order(6)
	public void editThresholdTest() {
		String aStringToTest = "AStringToTest";
		thresholdId.setThresName(aStringToTest);
		thresholdId.setThresValue(""); // illegal
		thresholdId.setThresValue(""); // illegal
		assertThrows(TransactionSystemException.class, () -> thresholdService.editThreshold(thresholdId));
		thresholdId.setThresName(aStringToTest);
		thresholdId.setThresValue(aStringToTest);
		thresholdId.setThresValuetype(aStringToTest);
		thresholdService.editThreshold(thresholdId);
		Threshold th = thresholdService.findById(thresholdId.getThresId()).get();
		assertEquals(aStringToTest, th.getThresName());
		assertEquals(aStringToTest, th.getThresValue());
		assertEquals(aStringToTest, th.getThresValuetype());
		
		//verify(thresholdRepositoryMock, VerificationModeFactory.times(3)).findById(0l);
	}
	
	// PUNTO D
	
	@Test
	@Order(7)
	public void saveLocalconditionTest() {
		Localcondition localcondition = new Localcondition();
		//when(localconditionRepositoryMock.findById(0l)).thenReturn(Optional.of(localcondition));
		
		Precondition pre = preconditionService.findById(preconditionId.getPreconId()).get();
		localcondition.setPrecondition(pre);
		Threshold th = thresholdService.findById(thresholdId.getThresId()).get();
		localcondition.setThreshold(th);
		String table = "ATable";
		localcondition.setLoconTablename(table);
		String colKey = "colKey";
		localcondition.setLoconKeycolumn(colKey);
		String operator = "==";
		localcondition.setLoconOperator(operator);
		String valueType = "tipo";
		localcondition.setLoconValuetype(valueType);
		
		localconditionId = localconditionService.save(localcondition);
		
		//  enunciado
		Localcondition found = localconditionService.findById(localconditionId.getLoconId()).get();
		assertFalse(found.getLoconTablename().contains(" "));
		assertEquals(table, found.getLoconTablename());
		assertFalse(found.getLoconKeycolumn().contains(" "));
		assertEquals(colKey, found.getLoconKeycolumn());
		assertFalse(found.getLoconOperator().contains(" "));
		assertEquals(operator, found.getLoconOperator());
		assertEquals(valueType, found.getLoconValuetype());
		/*
		verify(localconditionRepositoryMock).save(localcondition);
		verify(localconditionRepositoryMock).findById(0l);
		verify(thresholdRepositoryMock, VerificationModeFactory.times(4)).findById(0l);
		verify(preconditionRepositoryMock, VerificationModeFactory.times(4)).findById(0l);*/
	}
	
	@Test
	@Order(8)
	public void editLocalconditionTest() {
		String aStringToTest = "AStringToTest";
		Precondition pre = preconditionService.findById(preconditionId.getPreconId()).get();
		Threshold th = thresholdService.findById(thresholdId.getThresId()).get();
		
		localconditionId.setLoconColumnname(aStringToTest);
		localconditionId.setLoconQuerystring(""); // illegal
		localconditionId.setLoconKeycolumn(aStringToTest);
		localconditionId.setLoconOperator(""); // illegal
		localconditionId.setLoconValuetype(aStringToTest);
		localconditionId.setPrecondition(pre);
		localconditionId.setThreshold(th);
		localconditionId.setLoconTablename(aStringToTest);
		assertThrows(TransactionSystemException.class, () -> localconditionService.editLocalcondition(localconditionId));
		
		String operator = ">=";
		localconditionId.setLoconColumnname(aStringToTest);
		localconditionId.setLoconQuerystring(aStringToTest);
		localconditionId.setLoconKeycolumn(aStringToTest);
		localconditionId.setLoconOperator(operator);
		localconditionId.setLoconValuetype(aStringToTest);
		localconditionId.setPrecondition(pre);
		localconditionId.setThreshold(th);
		localconditionId.setLoconTablename(aStringToTest);
		localconditionService.editLocalcondition(localconditionId);
		
		Localcondition loc = localconditionService.findById(localconditionId.getLoconId()).get();
		assertEquals(aStringToTest, loc.getLoconColumnname());
		assertEquals(aStringToTest, loc.getLoconKeycolumn());
		assertEquals(operator, loc.getLoconOperator());
		assertEquals(aStringToTest, loc.getLoconQuerystring());
		assertEquals(aStringToTest, loc.getLoconTablename());
		assertEquals(aStringToTest, loc.getLoconValuetype());
		assertEquals(pre.getPreconId(), loc.getPrecondition().getPreconId());
		assertEquals(pre.getPreconLogicaloperand(), loc.getPrecondition().getPreconLogicaloperand());
		assertEquals(th.getThresId(), loc.getThreshold().getThresId());
		assertEquals(th.getThresName(), loc.getThreshold().getThresName());
		assertEquals(th.getThresValue(), loc.getThreshold().getThresValue());
		assertEquals(th.getThresValuetype(), loc.getThreshold().getThresValuetype());
		
		//verify(localconditionRepositoryMock, VerificationModeFactory.times(3)).findById(0l);
	}
}
