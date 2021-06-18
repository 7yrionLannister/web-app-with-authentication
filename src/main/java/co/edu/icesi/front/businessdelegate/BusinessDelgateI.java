package co.edu.icesi.front.businessdelegate;

import co.edu.icesi.front.model.*;

import java.util.List;

public interface BusinessDelgateI {

    // LOCALCONDITION

    List<Localcondition> findAllLocalconditionsByThreshold(Long threshold);
    List<Localcondition> findAllLocalconditionsByPrecondition(Long precondition);
    List<Localcondition> findAllLocalconditionsByName(String name);
    List<Localcondition> findAllLocalconditionsByType(String type);
    List<Localcondition> findAllLocalconditions();
    Localcondition findLocalconditionById(long id);
    void deleteLocalcondition(Localcondition loc);
    Localcondition saveLocalcondition(Localcondition loc);
    void editLocalCondition(Localcondition loc);

    //THRESHOLD

    Threshold getThreshold(long id);
    List<Threshold> threshold_findAllByInstitution(long instId);
    List<Threshold> threshold_findAllByName(String name);
    List<Threshold> threshold_findAllByValue(String value);
    List<Threshold> threshold_findAllByType(String type);
    List<Threshold> threshold_findAll();
    Threshold threshold_save(Threshold threshold);
    Threshold threshold_findById(long id);
    void threshold_delete(Threshold thr);
    void editThreshold(Threshold loc);


    // INSTITUTION

    List<Institution> findAllInstitutions();
    Institution findInstitutionById(Long id);
    Institution saveInstitution(Institution inst);
    void deleteInstitution(Institution inst);
    void editInstitution(Institution loc);


    //PRECONDITION

    Precondition precondition_get(long id);
    List<Precondition> precondition_findAllByAutotransition(long autotransition);
    List<Precondition> precondition_findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne();
    List<Precondition> precondition_findAll();
    Precondition precondition_save(Precondition pre);
    void precondition_delete(Precondition pre);
    Precondition precondition_findById(long id);
    void editPrecondition(Precondition loc);


    // AUTOTRANSITION

    void deleteAutotransition(Autotransition aut);
    Autotransition findAutotransitionById(long id);
    List<Autotransition> findAllAutotransitionsByInstitutionInstId(Long institutionId);
    List<Autotransition> findAllAutotransitionsByName(String name);
    List<Autotransition> findAllAutotransitionsByActive(String active);
    List<Autotransition> findAllAutotransitionsByLogicalOperand(String logop);
    List<Autotransition> findAllAutotransitions();
    Autotransition saveAutotransition(Autotransition aut);
    void editAutotransition(Autotransition loc);

    // USER

    Userr user_findById(long id);
    void user_delete(Userr user);
    Userr user_save(Userr user);
    List<Userr> user_findAll();
    List<Userr> user_findAllByInstitution(Institution institution);
    void editUser(Userr loc);

    // SYMPTOM

    Symptom saveSymptom(Symptom symptom);
    List<Symptom> findAllSymptom();
}
