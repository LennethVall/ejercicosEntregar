package clases;

import java.time.LocalDate;
import java.util.HashMap;

public class Cliente extends Persona {
	
	
	private LocalDate fecha;
	private HashMap<String, Entrenamiento> entrenamientos;
	
public Cliente (String DNI, String nombre, LocalDate fecha, HashMap entrenamientos) {
	
	super(DNI, nombre);
	this.fecha = fecha;
	this.entrenamientos = entrenamientos;
}
	


/**
 * @return the fecha
 */
public LocalDate getFecha() {
	return fecha;
}

/**
 * @param fecha the fecha to set
 */
public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

/**
 * @return the entrenamientos
 */
public HashMap<String, Entrenamiento> getEntrenamientos() {
	return entrenamientos;
}

/**
 * @param entrenamientos the entrenamientos to set
 */
public void setEntrenamientos(HashMap<String, Entrenamiento> entrenamientos) {
	this.entrenamientos = entrenamientos;
}
public void visualizar () {
	return super.toString() + fecha + entrenamientos + getFecha() + getEntrenamientos();
}
}
