package co.edu.icesi.front.businessdelegate;

public class BusinessDelegate implements BusinessDelgateI {
    private final static String URL = "https://localhost:8080/api";
    private final static String AUT_URL = URL + "/autotransitions";
    private final static String INST_URL = URL + "/institutions";
    private final static String USER_URL = URL + "/userrs";
    private final static String THR_URL = URL + "/thresholds";
    private final static String PRE_URL = URL + "/preconditions";
    private final static String LOC_URL = URL + "/localconditions";
}
