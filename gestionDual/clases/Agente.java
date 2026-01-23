package clases;

public abstract class Agente {

	private String codigo;
	private String dni;
	private String nombre;
	private String email;
	
public Agente (String codigo, String dni, String nombre, String email) {
	this.codigo=codigo;
	this.dni=dni;
	this.nombre=nombre;
	this.email=email;
}

/**
 * @return the codigo
 */
public String getCodigo() {
	return codigo;
}

/**
 * @param codigo the codigo to set
 */
public void setCodigo(String codigo) {
	this.codigo = codigo;
}

/**
 * @return the dni
 */
public String getDni() {
	return dni;
}

/**
 * @param dni the dni to set
 */
public void setDni(String dni) {
	this.dni = dni;
}

/**
 * @return the nombre
 */
public String getNombre() {
	return nombre;
}

/**
 * @param nombre the nombre to set
 */
public void setNombre(String nombre) {
	this.nombre = nombre;
}

/**
 * @return the email
 */
public String getEmail() {
	return email;
}

/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "Agente [codigo=" + codigo + ", dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", getCodigo()="
			+ getCodigo() + ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getEmail()=" + getEmail()
			+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}
	
}
