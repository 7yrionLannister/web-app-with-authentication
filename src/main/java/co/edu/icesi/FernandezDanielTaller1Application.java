package co.edu.icesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import co.edu.icesi.back.model.Autotransition;
import co.edu.icesi.back.model.Institution;
import co.edu.icesi.back.model.Localcondition;
import co.edu.icesi.back.model.Precondition;
import co.edu.icesi.back.model.Threshold;
import co.edu.icesi.back.model.UserType;
import co.edu.icesi.back.model.Userr;
import co.edu.icesi.back.service.AutotransitionService;
import co.edu.icesi.back.service.InstitutionService;
import co.edu.icesi.back.service.LocalconditionService;
import co.edu.icesi.back.service.PreconditionService;
import co.edu.icesi.back.service.ThresholdService;
import co.edu.icesi.back.service.UserrService;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"co.edu.icesi"})
//@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class FernandezDanielTaller1Application {
	
	private static InstitutionService is;
	private static long i1;
	private static long i2;
	private static UserrService us;
	private static AutotransitionService as;
	private static long a1;
	private static long a2;
	private static PreconditionService ps;
	private static long p1;
	private static long p2;
	private static ThresholdService ts;
	private static long t1;
	private static long t2;
	private static LocalconditionService ls;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FernandezDanielTaller1Application.class, args);
		
		us = context.getBean(UserrService.class);
		as = context.getBean(AutotransitionService.class);
		is = context.getBean(InstitutionService.class);
		ps = context.getBean(PreconditionService.class);
		ts = context.getBean(ThresholdService.class);
		ls = context.getBean(LocalconditionService.class);
		
		addDummyInstitutions(context);
		addDummyUserrs(context);
		addDummyAutotransitions(context);
		addDummyPreconditions(context);
		addDummyThresholds(context);
		addDummyLocalconditions(context);
	}
	
	public static void addDummyInstitutions(ConfigurableApplicationContext context) {
		Institution inst = new Institution();
		inst.setInstName("inst1");
		inst.setInstAcademicserverurl("inst1.edu.co");
		inst.setInstAcadloginurl("banner.inst1.edu.co");
		inst.setInstAcadloginusername("username1");
		inst.setInstAcadloginpassword("password1");
		inst.setInstAcadprogrammedcoursesurl("inst1.edu.co/courses");
		i1 = is.save(inst).getInstId();
		inst = new Institution();
		inst.setInstName("inst2");
		inst.setInstAcademicserverurl("inst2.edu.co");
		inst.setInstAcadextradataurl("isnt2extra.edu.co");
		inst.setInstAcadloginurl("banner.inst2.edu.co");
		inst.setInstAcadloginusername("username2");
		inst.setInstAcadloginpassword("password2");
		inst.setInstAcadprogrammedcoursesurl("inst2.edu.co/courses");
		i2 = is.save(inst).getInstId();
	}
	
	public static void addDummyUserrs(ConfigurableApplicationContext context) {
		Userr user = new Userr();
		user.setUserName("admin");
		user.setUserPassword("{noop}admin");
		user.setInstitution(is.findById(i1).get());
		user.setUserType(UserType.ADMINISTRATOR);
		us.save(user);
		user = new Userr();
		user.setUserName("ope");
		user.setUserPassword("{noop}ope");
		user.setInstitution(is.findById(i2).get());
		user.setUserType(UserType.OPERATOR);
		us.save(user);
	}
	
	public static void addDummyAutotransitions(ConfigurableApplicationContext context) {
		Autotransition aut = new Autotransition();
		aut.setAutotranIsactive("N");
		aut.setAutotranLogicaloperand("AND");
		aut.setAutotranName("aut1");
		aut.setInstitution(is.findById(i1).get());
		a1 = as.save(aut).getAutotranId();
		aut = new Autotransition();
		aut.setAutotranIsactive("Y");
		aut.setAutotranLogicaloperand("OR");
		aut.setAutotranName("aut2");
		aut.setInstitution(is.findById(i2).get());
		a2 = as.save(aut).getAutotranId();
	}
	
	public static void addDummyPreconditions(ConfigurableApplicationContext context) {
		Precondition pc = new Precondition();
		pc.setAutotransition(as.findById(a1).get());
		pc.setPreconLogicaloperand("AND");
		p1 = ps.save(pc).getPreconId();
		pc = new Precondition();
		pc.setAutotransition(as.findById(a2).get());
		pc.setPreconLogicaloperand("OR");
		p2 = ps.save(pc).getPreconId();
	}
	
	public static void addDummyThresholds(ConfigurableApplicationContext context) {
		Threshold th = new Threshold();
		th.setInstitution(is.findById(i1).get());
		th.setThresName("th1");
		th.setThresValue("1");
		th.setThresValuetype("thvt1");
		t1 = ts.save(th).getThresId();
		th = new Threshold();
		th.setInstitution(is.findById(i2).get());
		th.setThresName("th2");
		th.setThresValue("2");
		th.setThresValuetype("thvt2");
		t2 = ts.save(th).getThresId();
	}
	
	public static void addDummyLocalconditions(ConfigurableApplicationContext context) {
		Localcondition lc = new Localcondition();
		lc.setPrecondition(ps.findById(p1).get());
		lc.setThreshold(ts.findById(t1).get());
		lc.setLoconOperator("<");
		lc.setLoconValuetype("lcvt1");
		ls.save(lc);
		lc = new Localcondition();
		lc.setPrecondition(ps.findById(p2).get());
		lc.setThreshold(ts.findById(t2).get());
		lc.setLoconOperator(">");
		lc.setLoconValuetype("lcvt2");
		ls.save(lc);
		lc = new Localcondition();
		lc.setPrecondition(ps.findById(p2).get());
		lc.setThreshold(ts.findById(t2).get());
		lc.setLoconOperator(">");
		lc.setLoconValuetype("lcvt2");
		ls.save(lc);
	}

}
