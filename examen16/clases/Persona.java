package clases;

import java.time.LocalDate;

public class Persona {
    private String nombre;
    private LocalDate fechaNacimiento;

    public Persona(String nombre, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    @Override
    public String toString() {
        return nombre + " (" + fechaNacimiento + ")";
    }
}
