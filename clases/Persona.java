package clases;

public abstract class Persona implements serializable {
	protected String DNI;
	protected String nombre;
	
public Persona (String DNI, String nombre) {
	this.DNI = DNI;
	this.nombre = nombre;
}
public void visualizar() {
	
}
/**
 * @return the dNI
 */
public String getDNI() {
	return DNI;
}
/**
 * @param dNI the dNI to set
 */
public void setDNI(String dNI) {
	DNI = dNI;
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
@Override
public String toString() {
	return  DNI + nombre + getDNI() + getNombre();
			
}

}
