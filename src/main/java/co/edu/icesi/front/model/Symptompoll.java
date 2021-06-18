package co.edu.icesi.front.model;

import co.edu.icesi.back.model.Epidemevent;
import co.edu.icesi.back.model.Institution;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SYMPTOMPOLL database table.
 * 
 */
@Entity
@NamedQuery(name="Symptompoll.findAll", query="SELECT s FROM Symptompoll s")
public class Symptompoll implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYMPTOMPOLL_SYMPOLLID_GENERATOR", sequenceName="SYMPTOMPOLL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYMPTOMPOLL_SYMPOLLID_GENERATOR")
	@Column(name="SYMPOLL_ID")
	private long sympollId;

	/*@Column(name="INST_INST_ID")
	private BigDecimal instInstId;*/
	/** Esto lo pongo para manejar la relacion por referencia entre clases y no por id
	 * , ya que con el id requiere un proceso adicional para validar la integridad de la base de datos.
	 * Ademas, dejar esto en manos del programador no es buena idea cuando es algo que ya se maneja
	 * a nivel de base de datos relacional.
	 * */
	@ManyToOne
	@JoinColumn(name="INST_INST_ID")
	@NotNull(message = "The poll must be associated to an institution") // ENUNCIADO
	private co.edu.icesi.back.model.Institution institution;

	public co.edu.icesi.back.model.Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	/** Aqui acaba la adicion del atributo
	 * */

	@Temporal(TemporalType.DATE)
	@Column(name="SYMPOLL_ENDDATE")
	private Date sympollEnddate;

	@Column(name="SYMPOLL_NAME")
	private String sympollName;

	@Temporal(TemporalType.DATE)
	@Column(name="SYMPOLL_STARTDATE")
	private Date sympollStartdate;

	//bi-directional many-to-one association to Epidemevent
	@ManyToOne
	@JoinColumn(name="EPIEVE_EPIEVE_ID")
	private Epidemevent epidemevent;

	//bi-directional many-to-one association to Symptomquestion
	@OneToMany(mappedBy="symptompoll")
	private List<co.edu.icesi.front.model.Symptomquestion> symptomquestions;

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

	public Epidemevent getEpidemevent() {
		return this.epidemevent;
	}

	public void setEpidemevent(Epidemevent epidemevent) {
		this.epidemevent = epidemevent;
	}

	public List<co.edu.icesi.front.model.Symptomquestion> getSymptomquestions() {
		return this.symptomquestions;
	}

	public void setSymptomquestions(List<co.edu.icesi.front.model.Symptomquestion> symptomquestions) {
		this.symptomquestions = symptomquestions;
	}

	public co.edu.icesi.front.model.Symptomquestion addSymptomquestion(co.edu.icesi.front.model.Symptomquestion symptomquestion) {
		getSymptomquestions().add(symptomquestion);
		symptomquestion.setSymptompoll(this);

		return symptomquestion;
	}

	public co.edu.icesi.front.model.Symptomquestion removeSymptomquestion(Symptomquestion symptomquestion) {
		getSymptomquestions().remove(symptomquestion);
		symptomquestion.setSymptompoll(null);

		return symptomquestion;
	}

}