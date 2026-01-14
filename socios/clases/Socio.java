package clases;

import java.time.LocalDate;

public class Socio {
	
	private static int contadorSocios = 1;
	private int numeroSocio;
	private String nombre;
	private String apellidos;
	private String dni;
	private LocalDate fechaNacimiento;
	private LocalDate fechaAlta;
	private boolean activo;
	
	
	public Socio (String nombre, String apellidos, String dni, LocalDate fechaNacimiento, LocalDate fechaAlta) {
		this.numeroSocio = contadorSocios++;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaAlta = fechaAlta;
		this.activo = true;
	}
	
		
	
	/**
	 * @return the contadorSocios
	 */
	public static int getContadorSocios() {
		return contadorSocios;
	}




	/**
	 * @return the numeroSocio
	 */
	public int getNumeroSocio() {
		return numeroSocio;
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
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}



	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}



	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	/**
	 * @return the fechaAlta
	 */
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}



	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}



	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}



	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	@Override
	public String toString() {
		return "Socio [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", fechaNacimiento="
				+ fechaNacimiento + ", fechaAlta=" + fechaAlta + ", getNombre()=" + getNombre() + ", getApellidos()="
				+ getApellidos() + ", getDni()=" + getDni() + ", getFechaNacimiento()=" + getFechaNacimiento()
				+ ", getFechaAlta()=" + getFechaAlta() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
