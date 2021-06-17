package co.edu.icesi.front.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the PRECONDITION database table.
 * 
 */
public class Precondition implements Serializable {
	private static final long serialVersionUID = 1L;

	private long preconId;

	@NotBlank(message = "The logical operand must not be left blank") // TALLER
	private String preconLogicaloperand;

	//bi-directional many-to-one association to Localcondition
	private List<Localcondition> localconditions;

	//bi-directional many-to-one association to Autotransition
	@NotNull(message = "The precondition must be associated to an autotransition") // ENUNCIADO
	private Autotransition autotransition;

	public Precondition() {
	}

	public long getPreconId() {
		return this.preconId;
	}

	public void setPreconId(long preconId) {
		this.preconId = preconId;
	}

	public String getPreconLogicaloperand() {
		return this.preconLogicaloperand;
	}

	public void setPreconLogicaloperand(String preconLogicaloperand) {
		this.preconLogicaloperand = preconLogicaloperand;
	}

	public List<Localcondition> getLocalconditions() {
		return this.localconditions;
	}

	public void setLocalconditions(List<Localcondition> localconditions) {
		this.localconditions = localconditions;
	}

	public Localcondition addLocalcondition(Localcondition localcondition) {
		getLocalconditions().add(localcondition);
		localcondition.setPrecondition(this);

		return localcondition;
	}

	public Localcondition removeLocalcondition(Localcondition localcondition) {
		getLocalconditions().remove(localcondition);
		localcondition.setPrecondition(null);

		return localcondition;
	}

	public Autotransition getAutotransition() {
		return this.autotransition;
	}

	public void setAutotransition(Autotransition autotransition) {
		this.autotransition = autotransition;
	}
}