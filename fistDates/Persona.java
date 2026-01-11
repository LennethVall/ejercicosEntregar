package clases;

import java.time.LocalDate;
import java.time.Period;

public abstract class Persona {

    protected String dni;
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String genero;
    protected String localidad;

    public Persona(String dni, String nombre, LocalDate fechaNacimiento,
                   String genero, String localidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.localidad = localidad;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getGenero() { return genero; }
    public String getLocalidad() { return localidad; }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public abstract void visualizar();
}
