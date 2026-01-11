package clases;

import java.time.LocalDate;
import java.time.Period;

public abstract class Persona {

    protected String dni;
    protected String nombre;
    protected String apellido;
    protected LocalDate fechaNacimiento;

    public Persona(String dni, String nombre, String apellido, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public abstract void visualizar();
}
