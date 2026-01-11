package clases;

import java.time.LocalDate;

public abstract class Trabajador {
    protected String dni;
    protected String nombre;
    protected String apellidos;
    protected LocalDate fechaNacimiento;
    protected LocalDate fechaAlta;

    public Trabajador(String dni, String nombre, String apellidos,
                      LocalDate fechaNacimiento, LocalDate fechaAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    @Override
    public String toString() {
        return dni + " - " + getNombreCompleto();
    }
}

