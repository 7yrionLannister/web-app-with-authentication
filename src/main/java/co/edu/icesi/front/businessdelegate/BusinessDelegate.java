package co.edu.icesi.front.businessdelegate;

import co.edu.icesi.front.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class BusinessDelegate implements BusinessDelgateI {

    private final static String URL = "http://localhost:8080/api";
    private final static String AUT_URL = URL + "/autotransitions/";
    private final static String INST_URL = URL + "/institutions/";
    private final static String USER_URL = URL + "/userrs/";
    private final static String THR_URL = URL + "/thresholds/";
    private final static String PRE_URL = URL + "/preconditions/";
    private final static String LOC_URL = URL + "/localconditions/";

    private RestTemplate restTemplate;

    public BusinessDelegate(){
        this.restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }

    // Localcondition --------------------------------------------------------------------------------------------------

    @Override
    public List<Localcondition> findAllLocalconditionsByThreshold(Long threshold) {
        return null;
    }

    @Override
    public List<Localcondition> findAllLocalconditionsByPrecondition(Long precondition) {
        return null;
    }

    @Override
    public List<Localcondition> findAllLocalconditionsByName(String name) {
        return null;
    }

    @Override
    public List<Localcondition> findAllLocalconditionsByType(String type) {
        return null;
    }

    @Override
    public List<Localcondition> findAllLocalconditions() {

        Localcondition[] array = restTemplate.getForObject(LOC_URL, Localcondition[].class);

        return Arrays.asList(array);
    }

    @Override
    public Localcondition findLocalconditionById(long id) {
        return restTemplate.getForObject(LOC_URL+id, Localcondition.class);
    }

    @Override
    public void deleteLocalcondition(Localcondition loc) {
        restTemplate.delete(LOC_URL+loc.getLoconId());
    }

    @Override
    public Localcondition saveLocalcondition(Localcondition loc) {
        HttpEntity<Localcondition> request = new HttpEntity<>(loc);
        return restTemplate.postForObject(LOC_URL, request, Localcondition.class);
    }

    @Override
    public void editLocalCondition(Localcondition loc){
        restTemplate.put(LOC_URL, loc, Localcondition.class);
    }

    // THRESHOLD -------------------------------------------------------------------------------------------------------

    @Override
    public Threshold getThreshold(long id) {
        return restTemplate.getForObject(THR_URL+id, Threshold.class);
    }

    @Override
    public List<Threshold> threshold_findAllByInstitution(long instId) {
        return null;
    }

    @Override
    public List<Threshold> threshold_findAllByName(String name) {
        return null;
    }

    @Override
    public List<Threshold> threshold_findAllByValue(String value) {
        return null;
    }

    @Override
    public List<Threshold> threshold_findAllByType(String type) {
        return null;
    }

    @Override
    public List<Threshold> threshold_findAll() {
        Threshold[] array = restTemplate.getForObject(THR_URL, Threshold[].class);
        return Arrays.asList(array);
    }

    @Override
    public Threshold threshold_save(Threshold threshold) {
        HttpEntity<Threshold> request = new HttpEntity<>(threshold);
        return restTemplate.postForObject(THR_URL, request, Threshold.class);
    }

    @Override
    public Threshold threshold_findById(long id) {
        return restTemplate.getForObject(THR_URL+id, Threshold.class);
    }

    @Override
    public void threshold_delete(Threshold thr) {
        restTemplate.delete(LOC_URL+thr.getThresId());
    }

    @Override
    public void editThreshold(Threshold loc){
        restTemplate.put(THR_URL, loc, Threshold.class);
    }

    // INSTITUTION -----------------------------------------------------------------------------------------------------


    @Override
    public List<Institution> findAllInstitutions() {
        Institution[] array = restTemplate.getForObject(INST_URL, Institution[].class);
        return Arrays.asList(array);
    }

    @Override
    public Institution findInstitutionById(Long id) {
        return restTemplate.getForObject(INST_URL+id, Institution.class);
    }

    @Override
    public Institution saveInstitution(Institution inst) {
        HttpEntity<Institution> request = new HttpEntity<>(inst);
        return restTemplate.postForObject(INST_URL, request, Institution.class);
    }

    @Override
    public void deleteInstitution(Institution inst) {
        restTemplate.delete(INST_URL+inst.getInstId());
    }

    @Override
    public void editInstitution(Institution loc){
        restTemplate.put(INST_URL, loc, Institution.class);
    }

    //PRECONDITION -----------------------------------------------------------------------------------------------------

    @Override
    public Precondition precondition_get(long id) {
        return restTemplate.getForObject(PRE_URL+id, Precondition.class);
    }

    @Override
    public List<Precondition> precondition_findAllByAutotransition(long autotransition) {
        return null;
    }

    @Override
    public List<Precondition> precondition_findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne() {
        return null;
    }

    @Override
    public List<Precondition> precondition_findAll() {
        Precondition[] array = restTemplate.getForObject(PRE_URL, Precondition[].class);

        return Arrays.asList(array);
    }

    @Override
    public Precondition precondition_save(Precondition pre) {
        HttpEntity<Precondition> request = new HttpEntity<>(pre);
        return restTemplate.postForObject(PRE_URL, request, Precondition.class);
    }

    @Override
    public void precondition_delete(Precondition pre) {
        restTemplate.delete(PRE_URL+pre.getPreconId());
    }

    @Override
    public Precondition precondition_findById(long id) {
        return restTemplate.getForObject(PRE_URL+id, Precondition.class);
    }

    @Override
    public void editPrecondition(Precondition loc){
        restTemplate.put(PRE_URL, loc, Precondition.class);
    }


    //AUTOTRANSITION ---------------------------------------------------------------------------------------------------

    @Override
    public void deleteAutotransition(Autotransition aut) {
        restTemplate.delete(AUT_URL+aut.getAutotranId());
    }

    @Override
    public Autotransition findAutotransitionById(long id) {
        return restTemplate.getForObject(AUT_URL+id, Autotransition.class);
    }

    @Override
    public List<Autotransition> findAllAutotransitionsByInstitutionInstId(Long institutionId) {
        return null;
    }

    @Override
    public List<Autotransition> findAllAutotransitionsByName(String name) {
        return null;
    }

    @Override
    public List<Autotransition> findAllAutotransitionsByActive(String active) {
        return null;
    }

    @Override
    public List<Autotransition> findAllAutotransitionsByLogicalOperand(String logop) {
        return null;
    }

    @Override
    public List<Autotransition> findAllAutotransitions() {
        Autotransition[] array = restTemplate.getForObject(AUT_URL, Autotransition[].class);

        return Arrays.asList(array);
    }

    @Override
    public Autotransition saveAutotransition(Autotransition aut) {
        HttpEntity<Autotransition> request = new HttpEntity<>(aut);
        return restTemplate.postForObject(AUT_URL, request, Autotransition.class);
    }

    @Override
    public void editAutotransition(Autotransition loc){
        restTemplate.put(AUT_URL, loc, Autotransition.class);
    }


    //USER -------------------------------------------------------------------------------------------------------------

    @Override
    public Userr user_findById(long id) {
        return restTemplate.getForObject(USER_URL+id, Userr.class);
    }

    @Override
    public void user_delete(Userr user) {
        restTemplate.delete(USER_URL+user.getUserId());
    }

    @Override
    public Userr user_save(Userr user) {
        HttpEntity<Userr> request = new HttpEntity<>(user);
        return restTemplate.postForObject(USER_URL, request, Userr.class);
    }

    @Override
    public List<Userr> user_findAll() {
        Userr[] array = restTemplate.getForObject(USER_URL, Userr[].class);

        return Arrays.asList(array);
    }

    @Override
    public List<Institution> user_findAllByInstitution(Institution institution) {
        return null;
    }

    @Override
    public void editUser(Userr loc){
        restTemplate.put(USER_URL, loc, Userr.class);
    }

    @Override
    public Symptom saveSymptom(Symptom symptom) {
        HttpEntity<Symptom> request = new HttpEntity<>(symptom);
        return restTemplate.postForObject(AUT_URL, request, Symptom.class);
    }
}
