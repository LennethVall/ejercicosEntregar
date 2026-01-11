package clases;

import java.time.LocalDate;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pretendiente extends Persona {

    private String generoBuscado;
    private ArrayList<Cita> citas;

    public Pretendiente(String dni, String nombre, LocalDate fechaNacimiento,
                        String genero, String localidad, String generoBuscado) {
        super(dni, nombre, fechaNacimiento, genero, localidad);
        this.generoBuscado = generoBuscado;
        this.citas = new ArrayList<>();
    }

    public String getGeneroBuscado() { return generoBuscado; }
    public ArrayList<Cita> getCitas() { return citas; }

    public void addCita(Cita c) {
        citas.add(c);
    }

    @Override
    public void visualizar() {
        System.out.println("PRETENDIENTE: " + nombre + " (" + dni + ")");
        System.out.println("  Edad: " + getEdad());
        System.out.println("  Localidad: " + localidad);
        System.out.println("  Género: " + genero);
        System.out.println("  Busca: " + generoBuscado);
        System.out.println("  Citas:");
        if (citas.isEmpty()) System.out.println("    Ninguna");
        else citas.forEach(c -> System.out.println("    " + c));
    }
}
