package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Persona {
    private String codigoCliente;
    private double altura;
    private ArrayList<Medicion> mediciones;

    public Cliente(String nombre, LocalDate fechaNacimiento, double altura) {
        super(nombre, fechaNacimiento);
        this.altura = altura;
        this.mediciones = new ArrayList<>();
        this.codigoCliente = generarCodigo(nombre, fechaNacimiento);
    }

    private String generarCodigo(String nombre, LocalDate fechaNacimiento) {
        String letras = nombre.substring(0, 2).toUpperCase();
        String year = String.valueOf(fechaNacimiento.getYear());
        return letras + "-" + year.substring(year.length() - 2);
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public double getAltura() {
        return altura;
    }

    public ArrayList<Medicion> getMediciones() {
        return mediciones;
    }

    public void addMedicion(Medicion m) {
        mediciones.add(m);
    }

    public double getUltimoPeso() {
        if (mediciones.isEmpty()) return 0;
        return mediciones.get(mediciones.size() - 1).getPeso();
    }

    public double calcularIMC() {
        try {
            return getUltimoPeso() / (altura * altura);
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre() + " | Edad: " + getEdad() +
               " | Código: " + codigoCliente +
               " | IMC: " + String.format("%.2f", calcularIMC());
    }
}
