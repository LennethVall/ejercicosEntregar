package clases;

import java.time.LocalDate;

public class Empleado extends Persona {

    private String cargo;
    private LocalDate fechaAlta;

    public Empleado(String dni, String nombre, LocalDate fechaNacimiento,
                    String genero, String localidad, String cargo) {
        super(dni, nombre, fechaNacimiento, genero, localidad);
        this.cargo = cargo;
        this.fechaAlta = LocalDate.now();
    }

    @Override
    public void visualizar() {
        System.out.println("EMPLEADO: " + nombre + " (" + dni + ")");
        System.out.println("  Cargo: " + cargo);
        System.out.println("  Localidad: " + localidad);
        System.out.println("  Género: " + genero);
        System.out.println("  Fecha alta: " + fechaAlta);
    }
}

