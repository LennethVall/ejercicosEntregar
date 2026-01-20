package clases;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    protected String DNI;
    protected String nombre;

    public Persona(String DNI, String nombre) {
        this.DNI = DNI;
        this.nombre = nombre;
    }

    public abstract void visualizar();

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "DNI: " + DNI + ", Nombre: " + nombre;
    }
}
