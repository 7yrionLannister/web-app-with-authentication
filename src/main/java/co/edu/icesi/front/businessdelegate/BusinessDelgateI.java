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
    void saveLocalcondition(Localcondition loc);

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

    List<Threshold> findAllThresholds();


    // INSTITUTION

    List<Institution> institution_findAll();

    List<Institution> findAllInstitutions();
    Institution findInstitutionById(Long id);
    void saveInstitution(Institution inst);
    void deleteInstitution(Institution inst);

    //PRECONDITION

    Precondition precondition_get(long id);
    List<Precondition> precondition_findAllByAutotransition(long autotransition);
    List<Precondition> precondition_findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne();
    List<Precondition> precondition_findAll();
    Precondition precondition_save(Precondition pre);
    void precondition_delete(Precondition pre);
    Precondition precondition_findById(long id);

    List<Precondition> findAllPreconditions();

    // AUTOTRANSITION

    List<Autotransition> autotransition_findAll();

    void deleteAutotransition(Autotransition aut);
    Autotransition findAutotransitionById(long id);
    List<Autotransition> findAllAutotransitionsByInstitutionInstId(Long institutionId);
    List<Autotransition> findAllAutotransitionsByName(String name);
    List<Autotransition> findAllAutotransitionsByActive(String active);
    List<Autotransition> findAllAutotransitionsByLogicalOperand(String logop);
    List<Autotransition> findAllAutotransitions();
    void saveAutotransition(Autotransition aut);

    // USER

    Userr user_findById(long id);
    void user_delete(Userr user);
    Userr user_save(Userr user);
    List<Userr> user_findAll();
    List<Institution> user_findAllByInstitution(Institution institution);
}
