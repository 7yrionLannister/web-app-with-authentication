package co.edu.icesi.front.businessdelegate;

import co.edu.icesi.front.model.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BusinessDelegate implements BusinessDelgateI {

    private final static String URL = "https://localhost:8080/api";
    private final static String AUT_URL = URL + "/autotransitions";
    private final static String INST_URL = URL + "/institutions";
    private final static String USER_URL = URL + "/userrs";
    private final static String THR_URL = URL + "/thresholds";
    private final static String PRE_URL = URL + "/preconditions";
    private final static String LOC_URL = URL + "/localconditions";

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
        return null;
    }

    @Override
    public Localcondition findLocalconditionById(long id) {
        return null;
    }

    @Override
    public void deleteLocalcondition(Localcondition loc) {

    }

    @Override
    public void saveLocalcondition(Localcondition loc) {

    }

    @Override
    public Threshold getThreshold(long id) {
        return null;
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
        return null;
    }

    @Override
    public Threshold threshold_save(Threshold threshold) {
        return null;
    }

    @Override
    public Threshold threshold_findById(long id) {
        return null;
    }

    @Override
    public void threshold_delete(Threshold thr) {

    }

    @Override
    public List<Institution> institution_findAll() {
        return null;
    }

    @Override
    public List<Institution> findAllInstitutions() {
        return null;
    }

    @Override
    public Institution findInstitutionById(Long id) {
        return null;
    }

    @Override
    public void saveInstitution(Institution inst) {

    }

    @Override
    public void deleteInstitution(Institution inst) {

    }

    @Override
    public Precondition precondition_get(long id) {
        return null;
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
        return null;
    }

    @Override
    public Precondition precondition_save(Precondition pre) {
        return null;
    }

    @Override
    public void precondition_delete(Precondition pre) {

    }

    @Override
    public Precondition precondition_findById(long id) {
        return null;
    }

    @Override
    public void deleteAutotransition(Autotransition aut) {

    }

    @Override
    public Autotransition findAutotransitionById(long id) {
        return null;
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
        return null;
    }

    @Override
    public void saveAutotransition(Autotransition aut) {

    }

    @Override
    public Userr user_findById(long id) {
        return null;
    }

    @Override
    public void user_delete(Userr user) {

    }

    @Override
    public Userr user_save(Userr user) {
        return null;
    }

    @Override
    public List<Userr> user_findAll() {
        return null;
    }

    @Override
    public List<Institution> user_findAllByInstitution(Institution institution) {
        return null;
    }
}
