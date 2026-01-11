package clases;

import java.time.LocalDate;

public class Medicion {
    private LocalDate fecha;
    private double peso;

    public Medicion(LocalDate fecha, double peso) {
        this.fecha = fecha;
        this.peso = peso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return fecha + " " + peso + " Kg";
    }
}
