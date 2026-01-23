package clases;

import java.util.HashMap;

public class Empresa {
	
		
		
		private String cif;
		private String nombre;
		private String poblacion;
		private String telefono;
		private Float valoracion;
		private HashMap<String, String>Agente;
		
	public Empresa (String cif, String nombre, String poblacion, String telefono, float valoracion, HashMap Agente) {
		
		
		this.cif=cif;
		this.nombre=nombre;
		this.poblacion=poblacion;
		this.telefono=telefono;
		this.valoracion=valoracion;
		this.Agente=Agente;
	}

	/**
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @param cif the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
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
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the valoracion
	 */
	public Float getValoracion() {
		return valoracion;
	}

	/**
	 * @param valoracion the valoracion to set
	 */
	public void setValoracion(Float valoracion) {
		this.valoracion = valoracion;
	}

	/**
	 * @return the agente
	 */
	public HashMap<String, String> getAgente() {
		return Agente;
	}

	/**
	 * @param agente the agente to set
	 */
	public void setAgente(HashMap<String, String> agente) {
		Agente = agente;
	}

	@Override
	public String toString() {
		return "Empresa [cif=" + cif + ", nombre=" + nombre + ", poblacion=" + poblacion + ", telefono=" + telefono
				+ ", valoracion=" + valoracion + ", Agente=" + Agente + ", getCif()=" + getCif() + ", getNombre()="
				+ getNombre() + ", getPoblacion()=" + getPoblacion() + ", getTelefono()=" + getTelefono()
				+ ", getValoracion()=" + getValoracion() + ", getAgente()=" + getAgente() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
		
}
