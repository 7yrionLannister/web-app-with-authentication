package co.edu.icesi.front.model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the SYMPTOM database table.
 * 
 */
public class Symptom implements Serializable {
	private static final long serialVersionUID = 1L;

	private long sympId;

	private String sympIsactive;

	private String sympName;

	//bi-directional many-to-one association to Symptomquestion
	private List<Symptomquestion> symptomquestions;

	public Symptom() {
	}

	public long getSympId() {
		return this.sympId;
	}

	public void setSympId(long sympId) {
		this.sympId = sympId;
	}

	public String getSympIsactive() {
		return this.sympIsactive;
	}

	public void setSympIsactive(String sympIsactive) {
		this.sympIsactive = sympIsactive;
	}

	public String getSympName() {
		return this.sympName;
	}

	public void setSympName(String sympName) {
		this.sympName = sympName;
	}

	public List<Symptomquestion> getSymptomquestions() {
		return this.symptomquestions;
	}

	public void setSymptomquestions(List<Symptomquestion> symptomquestions) {
		this.symptomquestions = symptomquestions;
	}
}