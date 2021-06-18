package co.edu.icesi.front.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SYMPTOMPOLL database table.
 * 
 */
public class Symptompoll implements Serializable {
	private static final long serialVersionUID = 1L;

	private long sympollId;

	/*@Column(name="INST_INST_ID")
	private BigDecimal instInstId;*/
	/** Esto lo pongo para manejar la relacion por referencia entre clases y no por id
	 * , ya que con el id requiere un proceso adicional para validar la integridad de la base de datos.
	 * Ademas, dejar esto en manos del programador no es buena idea cuando es algo que ya se maneja
	 * a nivel de base de datos relacional.
	 * */
	@NotNull(message = "The poll must be associated to an institution") // ENUNCIADO
	private Institution institution;

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	/** Aqui acaba la adicion del atributo
	 * */
	private Date sympollEnddate;

	private String sympollName;

	private Date sympollStartdate;

	//bi-directional many-to-one association to Symptomquestion
	private List<Symptomquestion> symptomquestions;

	public Symptompoll() {
	}

	public long getSympollId() {
		return this.sympollId;
	}

	public void setSympollId(long sympollId) {
		this.sympollId = sympollId;
	}
/*
	public BigDecimal getInstInstId() {
		return this.instInstId;
	}

	public void setInstInstId(BigDecimal instInstId) {
		this.instInstId = instInstId;
	}
*/
	public Date getSympollEnddate() {
		return this.sympollEnddate;
	}

	public void setSympollEnddate(Date sympollEnddate) {
		this.sympollEnddate = sympollEnddate;
	}

	public String getSympollName() {
		return this.sympollName;
	}

	public void setSympollName(String sympollName) {
		this.sympollName = sympollName;
	}

	public Date getSympollStartdate() {
		return this.sympollStartdate;
	}

	public void setSympollStartdate(Date sympollStartdate) {
		this.sympollStartdate = sympollStartdate;
	}

	public List<Symptomquestion> getSymptomquestions() {
		return this.symptomquestions;
	}

	public void setSymptomquestions(List<Symptomquestion> symptomquestions) {
		this.symptomquestions = symptomquestions;
	}

	public Symptomquestion addSymptomquestion(Symptomquestion symptomquestion) {
		getSymptomquestions().add(symptomquestion);
		symptomquestion.setSymptompoll(this);

		return symptomquestion;
	}

	public Symptomquestion removeSymptomquestion(Symptomquestion symptomquestion) {
		getSymptomquestions().remove(symptomquestion);
		symptomquestion.setSymptompoll(null);

		return symptomquestion;
	}
}