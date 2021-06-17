package co.edu.icesi.front.businessdelegate;

import co.edu.icesi.front.model.*;

import java.util.List;

public interface BusinessDelgateI {
    Object findAllInstitutions();

    Autotransition findAutotransitionById(long id);

    void deleteAutotransition(Autotransition aut);

    List<Autotransition> findAllAutotransitionsByInstitutionInstId(Long institutionId);

    List<Autotransition> findAllAutotransitionsByName(String name);

    List<Autotransition> findAllAutotransitionsByActive(String active);

    List<Autotransition> findAllAutotransitionsByLogicalOperand(String logop);

    List<Autotransition> findAllAutotransitions();

    void saveAutotransition(Autotransition aut);

    Institution findInstitutionById(Long id);

    void saveInstitution(Institution inst);

    void deleteInstitution(Institution inst);

    List<Localcondition> findAllLocalconditionsByThreshold(Long threshold);

    List<Localcondition> findAllLocalconditionsByPrecondition(Long precondition);

    List<Localcondition> findAllLocalconditionsByName(String name);

    List<Localcondition> findAllLocalconditionsByType(String type);

    List<Localcondition> findAllLocalconditions();

    List<Precondition> findAllPreconditions();

    List<Threshold> findAllThresholds();

    Localcondition findLocalconditionById(long id);

    void deleteLocalcondition(Localcondition loc);

    void saveLocalcondition(Localcondition loc);
}
