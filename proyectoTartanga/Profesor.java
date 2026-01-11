package clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Profesor extends Persona {

    private double salario;
    private LocalDate fechaNacimiento;
    private CargoProfesor cargo;

    public Profesor(String dni, String nombre, String apellido,
                    double salario, LocalDate fechaNacimiento,
                    CargoProfesor cargo) throws DniInvalidoException, SalarioInvalidoException {
        super(dni, nombre, apellido);
        if (salario <= 0) {
            throw new SalarioInvalidoException("El salario debe ser mayor que 0");
        }
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public CargoProfesor getCargo() {
        return cargo;
    }

    @Override
    public void visualizar() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("PROFESOR: " + dni + " - " + getNombreCompleto());
        System.out.println("  Cargo: " + cargo +
                           " | Salario: " + salario +
                           " | Fecha nacimiento: " + fechaNacimiento.format(fmt));
    }
}
