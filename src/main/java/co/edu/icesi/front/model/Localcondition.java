package co.edu.icesi.front.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the LOCALCONDITION database table.
 * 
 */
public class Localcondition implements Serializable {
	private static final long serialVersionUID = 1L;

	private long loconId;

	private String loconColumnname;

	private String loconKeycolumn;

	@NotBlank(message = "The local condition must have an operator") // TALLER
	private String loconOperator;

	private String loconQuerystring;

	private String loconTablename;

	@NotBlank(message = "The value type must not be left blank") // TALLER
	private String loconValuetype;

	//bi-directional many-to-one association to Precondition
	@NotNull(message = "The local condition must be associated to a precondition") // ENUNCIADO
	private Precondition precondition;

	//bi-directional many-to-one association to Threshold
	@NotNull(message = "The local condition must be associated to a threshold") // ENUNCIADO
	private Threshold threshold;

	public Localcondition() {
	}

	public long getLoconId() {
		return this.loconId;
	}

	public void setLoconId(long loconId) {
		this.loconId = loconId;
	}

	public String getLoconColumnname() {
		return this.loconColumnname;
	}

	public void setLoconColumnname(String loconColumnname) {
		this.loconColumnname = loconColumnname;
	}

	public String getLoconKeycolumn() {
		return this.loconKeycolumn;
	}

	public void setLoconKeycolumn(String loconKeycolumn) {
		this.loconKeycolumn = loconKeycolumn;
	}

	public String getLoconOperator() {
		return this.loconOperator;
	}

	public void setLoconOperator(String loconOperator) {
		this.loconOperator = loconOperator;
	}

	public String getLoconQuerystring() {
		return this.loconQuerystring;
	}

	public void setLoconQuerystring(String loconQuerystring) {
		this.loconQuerystring = loconQuerystring;
	}

	public String getLoconTablename() {
		return this.loconTablename;
	}

	public void setLoconTablename(String loconTablename) {
		this.loconTablename = loconTablename;
	}

	public String getLoconValuetype() {
		return this.loconValuetype;
	}

	public void setLoconValuetype(String loconValuetype) {
		this.loconValuetype = loconValuetype;
	}

	public Precondition getPrecondition() {
		return this.precondition;
	}

	public void setPrecondition(Precondition precondition) {
		this.precondition = precondition;
	}

	public Threshold getThreshold() {
		return this.threshold;
	}

	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}
}