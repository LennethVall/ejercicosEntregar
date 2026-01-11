package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Jugador extends Persona {

    private int dorsal;
    private String equipo;
    private String posicion;
    private ArrayList<Partido> partidos;

    public Jugador(String dni, String nombre, String apellido, LocalDate fechaNacimiento,
                   int dorsal, String equipo, String posicion) {
        super(dni, nombre, apellido, fechaNacimiento);
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.posicion = posicion;
        this.partidos = new ArrayList<>();
    }

    public String getEquipo() { return equipo; }
    public ArrayList<Partido> getPartidos() { return partidos; }

    public void addPartido(Partido p) {
        partidos.add(p);
    }

    @Override
    public void visualizar() {
        System.out.println("JUGADOR: " + nombre + " " + apellido + " (" + dni + ")");
        System.out.println("  Equipo: " + equipo + " | Dorsal: " + dorsal + " | Posición: " + posicion);
        System.out.println("  Partidos jugados: " + partidos.size());
    }
}
