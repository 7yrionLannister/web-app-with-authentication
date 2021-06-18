package co.edu.icesi.front.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;

import co.edu.icesi.back.model.Symptompoll;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the INSTITUTION database table.
 * 
 */
public class Institution implements Serializable {
	private static final long serialVersionUID = 1L;

	private long instId;
	
	/** Esto lo pongo para manejar la relacion por referencia entre clases y no por id
	 * , ya que con el id requiere un proceso adicional para validar la integridad de la base de datos.
	 * Ademas, dejar esto en manos del programador no es buena idea cuando es algo que ya se maneja
	 * a nivel de base de datos relacional.
	 * */
	@JsonIgnore
	private List<Autotransition> autotransitions;
	
	public List<Autotransition> getAutotransitions() {
		return autotransitions;
	}
	
	public void setAutotransitions(List<Autotransition> autotransitions) {
		this.autotransitions = autotransitions;
	}
	
	@JsonIgnore
	private List<Userr> userrs;
	
	public List<Userr> getUserrs() {
		return userrs;
	}
	
	public void setUserrs(List<Userr> userrs) {
		this.userrs = userrs;
	}
	
	@JsonIgnore
	private List<Threshold> thresholds;
	
	public List<Threshold> getThresholds() {
		return thresholds;
	}
	
	public void setThresholds(List<Threshold> thresholds) {
		this.thresholds = thresholds;
	}

	public List<Symptompoll> getPolls() {
		return polls;
	}

	public void setPolls(List<Symptompoll> polls) {
		this.polls = polls;
	}

	/** Aqui acaba la adicion de atributos
	 * */

	@JsonIgnore
	private List<Symptompoll> polls;

	@NotBlank(message = "The institution server URL must not be blank") // TALLER
	private String instAcademicserverurl;

	private String instAcadextradataurl;

	private String instAcadloginpassword;

	private String instAcadloginurl;

	private String instAcadloginusername;

	private String instAcadpersoninfodocurl;

	private String instAcadpersoninfoidurl;

	private String instAcadphysicalspacesurl;

	@NotBlank(message = "There must be a link to the programmed courses in the institution")
	private String instAcadprogrammedcoursesurl;

	private String instLdapbasedn;

	private String instLdappassword;

	private String instLdapurl;

	private String instLdapusername;

	private String instLdapusersearchbase;

	private String instLdapusersearchfilter;

	@NotBlank(message = "The institution name must not be blank") // TALLER
	private String instName;

	public Institution() {
	}

	public long getInstId() {
		return this.instId;
	}

	public void setInstId(long instId) {
		this.instId = instId;
	}

	public String getInstAcademicserverurl() {
		return this.instAcademicserverurl;
	}

	public void setInstAcademicserverurl(String instAcademicserverurl) {
		this.instAcademicserverurl = instAcademicserverurl;
	}

	public String getInstAcadextradataurl() {
		return this.instAcadextradataurl;
	}

	public void setInstAcadextradataurl(String instAcadextradataurl) {
		this.instAcadextradataurl = instAcadextradataurl;
	}

	public String getInstAcadloginpassword() {
		return this.instAcadloginpassword;
	}

	public void setInstAcadloginpassword(String instAcadloginpassword) {
		this.instAcadloginpassword = instAcadloginpassword;
	}

	public String getInstAcadloginurl() {
		return this.instAcadloginurl;
	}

	public void setInstAcadloginurl(String instAcadloginurl) {
		this.instAcadloginurl = instAcadloginurl;
	}

	public String getInstAcadloginusername() {
		return this.instAcadloginusername;
	}

	public void setInstAcadloginusername(String instAcadloginusername) {
		this.instAcadloginusername = instAcadloginusername;
	}

	public String getInstAcadpersoninfodocurl() {
		return this.instAcadpersoninfodocurl;
	}

	public void setInstAcadpersoninfodocurl(String instAcadpersoninfodocurl) {
		this.instAcadpersoninfodocurl = instAcadpersoninfodocurl;
	}

	public String getInstAcadpersoninfoidurl() {
		return this.instAcadpersoninfoidurl;
	}

	public void setInstAcadpersoninfoidurl(String instAcadpersoninfoidurl) {
		this.instAcadpersoninfoidurl = instAcadpersoninfoidurl;
	}

	public String getInstAcadphysicalspacesurl() {
		return this.instAcadphysicalspacesurl;
	}

	public void setInstAcadphysicalspacesurl(String instAcadphysicalspacesurl) {
		this.instAcadphysicalspacesurl = instAcadphysicalspacesurl;
	}

	public String getInstAcadprogrammedcoursesurl() {
		return this.instAcadprogrammedcoursesurl;
	}

	public void setInstAcadprogrammedcoursesurl(String instAcadprogrammedcoursesurl) {
		this.instAcadprogrammedcoursesurl = instAcadprogrammedcoursesurl;
	}

	public String getInstLdapbasedn() {
		return this.instLdapbasedn;
	}

	public void setInstLdapbasedn(String instLdapbasedn) {
		this.instLdapbasedn = instLdapbasedn;
	}

	public String getInstLdappassword() {
		return this.instLdappassword;
	}

	public void setInstLdappassword(String instLdappassword) {
		this.instLdappassword = instLdappassword;
	}

	public String getInstLdapurl() {
		return this.instLdapurl;
	}

	public void setInstLdapurl(String instLdapurl) {
		this.instLdapurl = instLdapurl;
	}

	public String getInstLdapusername() {
		return this.instLdapusername;
	}

	public void setInstLdapusername(String instLdapusername) {
		this.instLdapusername = instLdapusername;
	}

	public String getInstLdapusersearchbase() {
		return this.instLdapusersearchbase;
	}

	public void setInstLdapusersearchbase(String instLdapusersearchbase) {
		this.instLdapusersearchbase = instLdapusersearchbase;
	}

	public String getInstLdapusersearchfilter() {
		return this.instLdapusersearchfilter;
	}

	public void setInstLdapusersearchfilter(String instLdapusersearchfilter) {
		this.instLdapusersearchfilter = instLdapusersearchfilter;
	}

	public String getInstName() {
		return this.instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}
}