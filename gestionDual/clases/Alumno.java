package clases;

import java.util.Date;
import java.util.HashMap;

public class Alumno extends Agente{

	private Date fecha_nac;
	private String curso;
	private String grupo;
	
	
public Alumno (String codigo, String dni, String nombre, String email,Date fecha_nac, String curso, String grupo) {
	
	super(codigo, dni, nombre, email);
	this.fecha_nac=fecha_nac;
	this.curso=curso;
	this.grupo=grupo;
}


/**
 * @return the fecha_nac
 */
public Date getFecha_nac() {
	return fecha_nac;
}


/**
 * @param fecha_nac the fecha_nac to set
 */
public void setFecha_nac(Date fecha_nac) {
	this.fecha_nac = fecha_nac;
}


/**
 * @return the curso
 */
public String getCurso() {
	return curso;
}


/**
 * @param curso the curso to set
 */
public void setCurso(String curso) {
	this.curso = curso;
}


/**
 * @return the grupo
 */
public String getGrupo() {
	return grupo;
}


/**
 * @param grupo the grupo to set
 */
public void setGrupo(String grupo) {
	this.grupo = grupo;
}


@Override
public String toString() {
	return "Alumno [fecha_nac=" + fecha_nac + ", curso=" + curso + ", grupo=" + grupo + ", getFecha_nac()="
			+ getFecha_nac() + ", getCurso()=" + getCurso() + ", getGrupo()=" + getGrupo() + ", getCodigo()="
			+ getCodigo() + ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getEmail()=" + getEmail()
			+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
}

}