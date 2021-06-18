package businessDelegate_Test;

import co.edu.icesi.FernandezDanielTaller1Application;
import co.edu.icesi.front.model.*;
import co.edu.icesi.front.businessdelegate.BusinessDelegate;
import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FernandezDanielTaller1Application.class)
@DirtiesContext
@Log4j2
public class BusinessDelegateTest {

    private final static String URL = "http://localhost:8080/api";
    private final static String AUT_URL = URL + "/autotransitions/";
    private final static String INST_URL = URL + "/institutions/";
    private final static String USER_URL = URL + "/userrs/";
    private final static String THR_URL = URL + "/thresholds/";
    private final static String PRE_URL = URL + "/preconditions/";
    private final static String LOC_URL = URL + "/localconditions/";

    @Mock
    private static RestTemplate restTemplate;

    @InjectMocks
    private static BusinessDelgateI bd;

    /*
    @Autowired
    public BusinessDelegateTest(){
       this.bd = new BusinessDelegate();
       bd.setRestTemplate(restTemplate);
    }

     */

    @BeforeAll
    public static void setUp() {
        bd = new BusinessDelegate();
        bd.setRestTemplate(restTemplate);
        log.info("-----> TASK TESTS STARTED <-----");
    }



    // LOCALCONDITION --------------------------------------------------------------------------------------------------

    @Test
    public void findAllLocalconditionsByThreshold_test(){
        Localcondition[] list = new Localcondition[5];

        Threshold thr = new Threshold();


        for (int i = 0; i < list.length; i++) {
            Localcondition lc = new Localcondition();
            lc.setThreshold(thr);
            list[i] = lc;
        }


        when(restTemplate.getForObject(LOC_URL+
                "search/findAllByThreshold?threshold=" + thr.getThresId(), Localcondition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllLocalconditionsByThreshold(thr.getThresId()).size(), 5,
                "Unexpected size");
    }

    @Test
    public void findAllLocalconditionsByPrecondition_test(){
        Localcondition[] list = new Localcondition[5];

        Precondition thr = new Precondition();


        for (int i = 0; i < list.length; i++) {
            Localcondition lc = new Localcondition();
            lc.setPrecondition(thr);
            list[i] = lc;
        }


        when(restTemplate.getForObject(LOC_URL+
                "search/findAllByPrecondition?precondition=" + thr.getPreconId(), Localcondition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllLocalconditionsByPrecondition(thr.getPreconId()).size(), 5,
                "Unexpected size");
    }

    @Test
    public void findAllLocalconditionsByName_test(){
        Localcondition[] list = new Localcondition[5];
        String name = "prueba";
        for (int i = 0; i < list.length; i++) {
            Localcondition lc = new Localcondition();
            lc.setLoconColumnname(name);
            list[i] = lc;
        }


        when(restTemplate.getForObject(LOC_URL+
                "search/findAllByName?name=" + name, Localcondition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllLocalconditionsByName(name).size(), 5, "Unexpected size");
    }

    @Test
    public void findAllLocalconditionsByType_test(){
        Localcondition[] list = new Localcondition[5];
        String type = "prueba";
        for (int i = 0; i < list.length; i++) {
            Localcondition lc = new Localcondition();
            lc.setLoconValuetype(type);
            list[i] = lc;
        }


        when(restTemplate.getForObject(LOC_URL+
                "search/findAllByType?type=" + type, Localcondition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllLocalconditionsByType(type).size(), 5, "Unexpected size");
    }

    @Test
    public void findAllLocalconditions_test(){

        Localcondition[] list = new Localcondition[5];

        for (int i = 0; i < list.length; i++) {
            Localcondition lc = new Localcondition();
            list[i] = lc;
        }

        when(restTemplate.getForObject(LOC_URL,Localcondition[].class))
                .thenReturn(list);

        assertEquals(bd.findAllLocalconditions().size(), 5, "Unexpected size");

    }

    @Test
    public void findLocalconditionById_test(){

        Localcondition lc = new Localcondition();

        when(restTemplate.getForObject(LOC_URL+lc.getLoconId(), Localcondition.class))
                .thenReturn(lc);


        assertEquals(bd.findLocalconditionById(lc.getLoconId()).getLoconId(), lc.getLoconId(),
                "Unexpected id");
    }

    @Test
    public void deleteLocalcondition_test(){

        Localcondition lc = new Localcondition();

        bd.deleteLocalcondition(lc);

        verify(restTemplate).delete(LOC_URL+lc.getLoconId());
    }

    @Test
    public void saveLocalcondition_test(){
        Localcondition loc = new Localcondition();
        HttpEntity<Localcondition> request = new HttpEntity<>(loc);

        when(restTemplate.postForObject(LOC_URL, request, Localcondition.class))
                .thenReturn(loc);

        assertEquals(bd.saveLocalcondition(loc).getLoconId(), loc.getLoconId(), "Unexpected id");
    }

    @Test
    public void editLocalCondition_test(){
        Localcondition lc = new Localcondition();

        bd.editLocalCondition(lc);

        verify(restTemplate).put(LOC_URL, lc, Localcondition.class);
    }


    // THRESHOLD -------------------------------------------------------------------------------------------------------

    @Test
    public void getThreshold_test(){
        Threshold thr = new Threshold();

        when(restTemplate.getForObject(THR_URL+thr.getThresId(), Threshold.class))
                .thenReturn(thr);


        assertEquals(bd.getThreshold(thr.getThresId()).getThresId(), thr.getThresId(),
                "Unexpected id");
    }

    @Test
    public void threshold_findAllByInstitution_test(){
        Threshold[] list = new Threshold[5];

        Institution inst = new Institution();


        for (int i = 0; i < list.length; i++) {
            Threshold thr = new Threshold();
            thr.setInstitution(inst);
            list[i] = thr;
        }


        when(restTemplate.getForObject(THR_URL+
                "search/findAllByInstitution?institution=" + inst.getInstId(), Threshold[].class)).
                thenReturn(list);


        assertEquals(bd.threshold_findAllByInstitution(inst.getInstId()).size(), 5,
                "Unexpected size");
    }

    @Test
    public void threshold_findAllByName_test(){
        Threshold[] list = new Threshold[5];
        String name = "prueba";
        for (int i = 0; i < list.length; i++) {
            Threshold lc = new Threshold();
            lc.setThresName(name);
            list[i] = lc;
        }


        when(restTemplate.getForObject(THR_URL+
                "search/findAllByName?name=" + name, Threshold[].class)).
                thenReturn(list);


        assertEquals(bd.threshold_findAllByName(name).size(), 5, "Unexpected size");
    }

    @Test
    public void threshold_findAllByValue_test(){
        Threshold[] list = new Threshold[5];
        String value = "prueba";
        for (int i = 0; i < list.length; i++) {
            Threshold lc = new Threshold();
            lc.setThresValue(value);
            list[i] = lc;
        }


        when(restTemplate.getForObject(THR_URL+
                "search/findAllByValue?value=" + value, Threshold[].class)).
                thenReturn(list);


        assertEquals(bd.threshold_findAllByValue(value).size(), 5, "Unexpected size");
    }

    @Test
    public void threshold_findAllByType_test(){
        Threshold[] list = new Threshold[5];
        String type = "prueba";
        for (int i = 0; i < list.length; i++) {
            Threshold lc = new Threshold();
            lc.setThresValuetype(type);
            list[i] = lc;
        }


        when(restTemplate.getForObject(THR_URL+
                "search/findAllByType?type=" + type, Threshold[].class)).
                thenReturn(list);


        assertEquals(bd.threshold_findAllByType(type).size(), 5, "Unexpected size");
    }

    @Test
    public void threshold_findAll_test(){
        Threshold[] list = new Threshold[5];

        for (int i = 0; i < list.length; i++) {
            Threshold lc = new Threshold();
            list[i] = lc;
        }

        when(restTemplate.getForObject(THR_URL,Threshold[].class))
                .thenReturn(list);

        assertEquals(bd.threshold_findAll().size(), 5, "Unexpected size");
    }

    @Test
    public void threshold_save_test(){
        Threshold loc = new Threshold();
        HttpEntity<Threshold> request = new HttpEntity<>(loc);

        when(restTemplate.postForObject(THR_URL, request, Threshold.class))
                .thenReturn(loc);

        assertEquals(bd.threshold_save(loc).getThresId(), loc.getThresId(), "Unexpected id");
    }

    @Test
    public void threshold_findById_test(){
        Threshold lc = new Threshold();

        when(restTemplate.getForObject(THR_URL+lc.getThresId(), Threshold.class))
                .thenReturn(lc);


        assertEquals(bd.threshold_findById(lc.getThresId()).getThresId(), lc.getThresId(),
                "Unexpected id");
    }

    @Test
    public void threshold_delete_test(){
        Threshold lc = new Threshold();

        bd. threshold_delete(lc);

        verify(restTemplate).delete(THR_URL+lc.getThresId());
    }

    @Test
    public void editThreshold_test(){
        Threshold lc = new Threshold();

        bd. editThreshold(lc);

        verify(restTemplate).put(THR_URL, lc, Threshold.class);
    }

    // PRECONDITION ----------------------------------------------------------------------------------------------------

    @Test
    public void precondition_get_test(){
        Precondition lc = new Precondition();

        when(restTemplate.getForObject(PRE_URL+lc.getPreconId(), Precondition.class))
                .thenReturn(lc);


        assertEquals(bd.precondition_get(lc.getPreconId()).getPreconId(), lc.getPreconId(),
                "Unexpected id");
    }

    @Test
    public void precondition_findAllByAutotransition_test(){
        Precondition[] list = new Precondition[5];

        Autotransition auth = new Autotransition();


        for (int i = 0; i < list.length; i++) {
            Precondition thr = new Precondition();
            thr.setAutotransition(auth);
            list[i] = thr;
        }


        when(restTemplate.getForObject(PRE_URL+
                "search/findAllByAutotransition?autotransition=" + auth.getAutotranId(), Precondition[].class)).
                thenReturn(list);


        assertEquals(bd.precondition_findAllByAutotransition(auth.getAutotranId()).size(), 5,
                "Unexpected size");
    }

    @Test
    public void precondition_findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne_test(){

        Precondition[] list = new Precondition[5];

        for (int i = 0; i < list.length; i++) {
            Precondition thr = new Precondition();
            list[i] = thr;
        }

        when(restTemplate.getForObject(PRE_URL+
                "search/findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne",
                Precondition[].class))
                .thenReturn(list);

        assertEquals(bd.precondition_findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne
                        ().size(), 5,
                "Unexpected size");
    }

    @Test
    public void precondition_findAll_test(){
        Precondition[] list = new Precondition[5];

        for (int i = 0; i < list.length; i++) {
            Precondition lc = new Precondition();
            list[i] = lc;
        }

        when(restTemplate.getForObject(PRE_URL,Precondition[].class))
                .thenReturn(list);

        assertEquals(bd.precondition_findAll().size(), 5, "Unexpected size");
    }

    @Test
    public void precondition_save_test(){
        Precondition loc = new Precondition();
        HttpEntity<Precondition> request = new HttpEntity<>(loc);

        when(restTemplate.postForObject(PRE_URL, request, Precondition.class))
                .thenReturn(loc);

        assertEquals(bd.precondition_save(loc).getPreconId(), loc.getPreconId(), "Unexpected id");
    }

    @Test
    public void precondition_delete_test(){
        Precondition lc = new Precondition();

        bd.precondition_delete(lc);

        verify(restTemplate).delete(PRE_URL+lc.getPreconId());
    }

    @Test
    public void precondition_findById_test(){
        Precondition lc = new Precondition();

        when(restTemplate.getForObject(PRE_URL+lc.getPreconId(), Precondition.class))
                .thenReturn(lc);


        assertEquals(bd.precondition_findById(lc.getPreconId()).getPreconId(), lc.getPreconId(),
                "Unexpected id");
    }

    @Test
    public void editPrecondition_test(){
        Precondition lc = new Precondition();

        bd.editPrecondition(lc);

        verify(restTemplate).put(PRE_URL, lc, Precondition.class);
    }

    // AUTOTRANSITION --------------------------------------------------------------------------------------------------

    @Test
    public void deleteAutotransition_test(){
        Autotransition lc = new Autotransition();

        bd.deleteAutotransition(lc);

        verify(restTemplate).delete(AUT_URL+lc.getAutotranId());
    }

    @Test
    public void findAutotransitionById_test(){
        Autotransition lc = new Autotransition();

        when(restTemplate.getForObject(AUT_URL+lc.getAutotranId(), Autotransition.class))
                .thenReturn(lc);


        assertEquals(bd.findAutotransitionById(lc.getAutotranId()).getAutotranId(), lc.getAutotranId(),
                "Unexpected id");
    }

    @Test
    public void findAllAutotransitionsByInstitutionInstId_test(){
        Autotransition[] list = new Autotransition[5];

        Institution inst = new Institution();


        for (int i = 0; i < list.length; i++) {
            Autotransition thr = new Autotransition();
            thr.setInstitution(inst);
            list[i] = thr;
        }


        when(restTemplate.getForObject(AUT_URL+
                "search/findAllByInstitutionInstId?institution=" + inst.getInstId(), Autotransition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllAutotransitionsByInstitutionInstId(inst.getInstId()).size(), 5,
                "Unexpected size");
    }

    @Test
    public void findAllAutotransitionsByName_test(){
        Autotransition[] list = new Autotransition[5];
        String name = "prueba";
        for (int i = 0; i < list.length; i++) {
            Autotransition lc = new Autotransition();
            lc.setAutotranName(name);
            list[i] = lc;
        }


        when(restTemplate.getForObject(AUT_URL+
                "search/findAllByName?name=" + name, Autotransition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllAutotransitionsByName(name).size(), 5, "Unexpected size");
    }

    @Test
    public void findAllAutotransitionsByActive_test(){
        Autotransition[] list = new Autotransition[5];
        String active = "prueba";
        for (int i = 0; i < list.length; i++) {
            Autotransition lc = new Autotransition();
            lc.setAutotranIsactive(active);
            list[i] = lc;
        }


        when(restTemplate.getForObject(AUT_URL+
                "search/findAllByActive?active=" + active, Autotransition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllAutotransitionsByActive(active).size(), 5, "Unexpected size");
    }

    @Test
    public void findAllAutotransitionsByLogicalOperand_test(){
        Autotransition[] list = new Autotransition[5];
        String operand = "prueba";
        for (int i = 0; i < list.length; i++) {
            Autotransition lc = new Autotransition();
            lc.setAutotranLogicaloperand(operand);
            list[i] = lc;
        }


        when(restTemplate.getForObject(AUT_URL+
                "search/findAllByLogicalOperand?operand=" + operand, Autotransition[].class)).
                thenReturn(list);


        assertEquals(bd.findAllAutotransitionsByLogicalOperand(operand).size(), 5, "Unexpected size");
    }

    @Test
    public void findAllAutotransitions_test(){
        Autotransition[] list = new Autotransition[5];

        for (int i = 0; i < list.length; i++) {
            Autotransition lc = new Autotransition();
            list[i] = lc;
        }

        when(restTemplate.getForObject(AUT_URL,Autotransition[].class))
                .thenReturn(list);

        assertEquals(bd.findAllAutotransitions().size(), 5, "Unexpected size");
    }

    @Test
    public void saveAutotransition_test(){
        Autotransition loc = new Autotransition();
        HttpEntity<Autotransition> request = new HttpEntity<>(loc);

        when(restTemplate.postForObject(AUT_URL, request, Autotransition.class))
                .thenReturn(loc);

        assertEquals(bd.saveAutotransition(loc).getAutotranId(), loc.getAutotranId(), "Unexpected id");
    }

    @Test
    public void editAutotransition_test(){
        Autotransition lc = new Autotransition();

        bd.editAutotransition(lc);

        verify(restTemplate).put(AUT_URL, lc, Autotransition.class);
    }

    @AfterAll
    public static void finish() {
        log.info("-----> TASK TESTS FINISHED <-----");
    }

}
