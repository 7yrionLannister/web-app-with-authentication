package co.edu.icesi.front.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the AUTOTRANSITION database table.
 * 
 */
public class Autotransition implements Serializable {
	private static final long serialVersionUID = 1L;
	private long autotranId;
	
	/** Esto lo pongo para manejar la relacion por referencia entre clases y no por id
	 * , ya que con el id requiere un proceso adicional para validar la integridad de la base de datos.
	 * Ademas, dejar esto en manos del programador no es buena idea cuando es algo que ya se maneja
	 * a nivel de base de datos relacional.
	 * */
	@NotNull(message = "The autotransition must be associated to an institution") // ENUNCIADO
	private Institution institution;
	
	public Institution getInstitution() {
		return institution;
	}
	
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	/** Aqui acaba la adicion del atributo
	 * */

	@NotNull(message = "Select a valid value for the active flag (Y/N)") // TALLER
	private String autotranIsactive;
	
	@NotBlank(message = "The logical operand must not be left blank") // TALLER
	private String autotranLogicaloperand;

	@NotBlank(message = "The autotransition name must not be left blank") // TALLER
	private String autotranName;

	/*@Column(name="INST_INST_ID")
	private BigDecimal instInstId;*/ //FIXME lo quito para que funcione por referencia de clases y no por id

	//bi-directional many-to-one association to Precondition
	@JsonIgnore
	private List<Precondition> preconditions;

	public Autotransition() {
		setPreconditions(new ArrayList<>());
	}

	public long getAutotranId() {
		return this.autotranId;
	}

	public void setAutotranId(long autotranId) {
		this.autotranId = autotranId;
	}

	public String getAutotranIsactive() {
		return this.autotranIsactive;
	}

	public void setAutotranIsactive(String autotranIsactive) {
		this.autotranIsactive = autotranIsactive;
	}

	public String getAutotranLogicaloperand() {
		return this.autotranLogicaloperand;
	}

	public void setAutotranLogicaloperand(String autotranLogicaloperand) {
		this.autotranLogicaloperand = autotranLogicaloperand;
	}

	public String getAutotranName() {
		return this.autotranName;
	}

	public void setAutotranName(String autotranName) {
		this.autotranName = autotranName;
	}
	/* FIXME lo quito para que funcione con referencia entre clases y no por id
	public BigDecimal getInstInstId() {
		return this.instInstId;
	}

	public void setInstInstId(BigDecimal instInstId) {
		this.instInstId = instInstId;
	}
	*/
	
	public List<Precondition> getPreconditions() {
		return this.preconditions;
	}

	public void setPreconditions(List<Precondition> preconditions) {
		this.preconditions = preconditions;
	}

	public Precondition addPrecondition(Precondition precondition) {
		getPreconditions().add(precondition);
		precondition.setAutotransition(this);

		return precondition;
	}

	public Precondition removePrecondition(Precondition precondition) {
		getPreconditions().remove(precondition);
		precondition.setAutotransition(null);

		return precondition;
	}

}