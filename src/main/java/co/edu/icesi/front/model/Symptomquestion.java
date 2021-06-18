package co.edu.icesi.front.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SYMPTOMQUESTION database table.
 * 
 */
public class Symptomquestion implements Serializable {
	private static final long serialVersionUID = 1L;

	private long sympquesId;

	private String sympquesActivationanswer;

	private String sympquesIsactive;

	private String sympquesName;

	private BigDecimal sympquesWeight;

	//bi-directional many-to-one association to Symptom
	private Symptom symptom;

	//bi-directional many-to-one association to Symptompoll
	private Symptompoll symptompoll;

	public Symptomquestion() {
	}

	public long getSympquesId() {
		return this.sympquesId;
	}

	public void setSympquesId(long sympquesId) {
		this.sympquesId = sympquesId;
	}

	public String getSympquesActivationanswer() {
		return this.sympquesActivationanswer;
	}

	public void setSympquesActivationanswer(String sympquesActivationanswer) {
		this.sympquesActivationanswer = sympquesActivationanswer;
	}

	public String getSympquesIsactive() {
		return this.sympquesIsactive;
	}

	public void setSympquesIsactive(String sympquesIsactive) {
		this.sympquesIsactive = sympquesIsactive;
	}

	public String getSympquesName() {
		return this.sympquesName;
	}

	public void setSympquesName(String sympquesName) {
		this.sympquesName = sympquesName;
	}

	public BigDecimal getSympquesWeight() {
		return this.sympquesWeight;
	}

	public void setSympquesWeight(BigDecimal sympquesWeight) {
		this.sympquesWeight = sympquesWeight;
	}

	public Symptom getSymptom() {
		return this.symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Symptompoll getSymptompoll() {
		return this.symptompoll;
	}

	public void setSymptompoll(Symptompoll symptompoll) {
		this.symptompoll = symptompoll;
	}
}