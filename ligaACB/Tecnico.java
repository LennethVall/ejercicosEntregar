package clases;

import java.time.LocalDate;

public class Tecnico extends Persona {

    private String equipo;
    private double salario;
    private String cargo;

    public Tecnico(String dni, String nombre, String apellido, LocalDate fechaNacimiento,
                   String equipo, double salario, String cargo) {
        super(dni, nombre, apellido, fechaNacimiento);
        this.equipo = equipo;
        this.salario = salario;
        this.cargo = cargo;
    }

    public String getEquipo() { return equipo; }
    public double getSalario() { return salario; }
    public String getCargo() { return cargo; }

    public void setEquipo(String equipo) { this.equipo = equipo; }
    public void setSalario(double salario) { this.salario = salario; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public void visualizar() {
        System.out.println("TÉCNICO: " + nombre + " " + apellido + " (" + dni + ")");
        System.out.println("  Equipo: " + equipo + " | Cargo: " + cargo + " | Salario: " + salario);
    }
}

