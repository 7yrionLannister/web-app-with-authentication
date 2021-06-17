package co.edu.icesi.front.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the USERR database table.
 * 
 */
public class Userr implements Serializable {
	private static final long serialVersionUID = 1L;

	private long userId;
	
	/** Esto lo pongo para manejar la relacion por referencia entre clases y no por id
	 * , ya que con el id requiere un proceso adicional para validar la integridad de la base de datos.
	 * Ademas, dejar esto en manos del programador no es buena idea cuando es algo que ya se maneja
	 * a nivel de base de datos relacional.
	 * 
	 * Tambien adiciono el tipo de usuario aqui, porque de lo contrario hay que ver por una cadena de
	 * hasta tres clases. :v
	 * */
	@NotNull(message = "The user must be associated to an institution") // TALLER
	private Institution institution;
	
	public Institution getInstitution() {
		return institution;
	}
	
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	@NotNull(message = "The user must be of a type") // TALLER
	private UserType userType;
	
	public UserType getUserType() {
		return userType;
	}
	
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	/** Aqui acaba la adicion de atributos
	 * */

	/*@Column(name="INST_INST_ID")
	private BigDecimal instInstId;*/ //FIXME lo quito para que funcione por referencia de clases y no por id

	@NotBlank(message = "The username must not be blank") // TALLER
	private String userName;

	@NotBlank(message = "The password must be set") // TALLER
	private String userPassword;

	public Userr() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	/* FIXME lo quito para que funcione con referencia entre clases y no por id
	public BigDecimal getInstInstId() {
		return this.instInstId;
	}

	public void setInstInstId(BigDecimal instInstId) {
		this.instInstId = instInstId;
	}
	*/
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}