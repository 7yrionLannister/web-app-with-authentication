package co.edu.icesi.front.businessdelegate;

import co.edu.icesi.front.model.*;

import java.util.List;

public interface BusinessDelgateI {

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


    // INSTITUTION

    List<Institution> institution_findAll();


    //PRECONDITION

    Precondition precondition_get(long id);
    List<Precondition> precondition_findAllByAutotransition(long autotransition);
    List<Precondition> precondition_findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne();
    List<Precondition> precondition_findAll();
    Precondition precondition_save(Precondition pre);
    void precondition_delete(Precondition pre);
    Precondition precondition_findById(long id);


    // AUTOTRANSITION

    List<Autotransition> autotransition_findAll();


    // USER

    Userr user_findById(long id);
    void user_delete(Userr user);
    Userr user_save(Userr user);
    List<Userr> user_findAll();
    List<Institution> user_findAllByInstitution(Institution institution);


}
