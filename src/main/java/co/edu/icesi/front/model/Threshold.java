package co.edu.icesi.front.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the THRESHOLD database table.
 * 
 */
public class Threshold implements Serializable {
	private static final long serialVersionUID = 1L;

	private long thresId;
	
	/** Esto lo pongo para manejar la relacion por referencia entre clases y no por id
	 * , ya que con el id requiere un proceso adicional para validar la integridad de la base de datos.
	 * Ademas, dejar esto en manos del programador no es buena idea cuando es algo que ya se maneja
	 * a nivel de base de datos relacional.
	 * */
	@NotNull(message = "The threshold must be associated to an institution")
	private Institution institution;
	
	public Institution getInstitution() {
		return institution;
	}
	
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	/** Aqui acaba la adicion del atributo
	 * */

	/*@Column(name="INST_INST_ID")
	private BigDecimal instInstId;*/ //FIXME lo quito para que funcione por referencia de clases y no por id

	@NotBlank(message = "The threshold name must not be left blank") // TALLER
	private String thresName;

	@NotBlank(message = "The threshold value must not be left blank") // TALLER
	private String thresValue;

	@NotBlank(message = "The threshold value type must not be left blank") // TALLER
	private String thresValuetype;

	//bi-directional many-to-one association to Localcondition
	private List<Localcondition> localconditions;

	public Threshold() {
	}

	public long getThresId() {
		return this.thresId;
	}

	public void setThresId(long thresId) {
		this.thresId = thresId;
	}
	/* FIXME lo quito para que funcione con referencia entre clases y no por id
	public BigDecimal getInstInstId() {
		return this.instInstId;
	}

	public void setInstInstId(BigDecimal instInstId) {
		this.instInstId = instInstId;
	}
	*/
	public String getThresName() {
		return this.thresName;
	}

	public void setThresName(String thresName) {
		this.thresName = thresName;
	}

	public String getThresValue() {
		return this.thresValue;
	}

	public void setThresValue(String thresValue) {
		this.thresValue = thresValue;
	}

	public String getThresValuetype() {
		return this.thresValuetype;
	}

	public void setThresValuetype(String thresValuetype) {
		this.thresValuetype = thresValuetype;
	}

	public List<Localcondition> getLocalconditions() {
		return this.localconditions;
	}

	public void setLocalconditions(List<Localcondition> localconditions) {
		this.localconditions = localconditions;
	}

	public Localcondition addLocalcondition(Localcondition localcondition) {
		getLocalconditions().add(localcondition);
		localcondition.setThreshold(this);

		return localcondition;
	}

	public Localcondition removeLocalcondition(Localcondition localcondition) {
		getLocalconditions().remove(localcondition);
		localcondition.setThreshold(null);

		return localcondition;
	}
}