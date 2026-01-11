package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Piloto extends Trabajador {

    private LocalDate fechaLicencia;
    private String residencia;
    private List<Vuelo> vuelos;

    public Piloto(String dni, String nombre, String apellidos,
                  LocalDate fechaNacimiento, LocalDate fechaAlta,
                  LocalDate fechaLicencia, String residencia) {
        super(dni, nombre, apellidos, fechaNacimiento, fechaAlta);
        this.fechaLicencia = fechaLicencia;
        this.residencia = residencia;
        this.vuelos = new ArrayList<>();
    }

    public String getResidencia() {
        return residencia;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void addVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public long getHorasTotalesVuelo() {
        long totalHoras = 0;
        for (Vuelo v : vuelos) {
            Duration d = Duration.between(v.getFechaInicio(), v.getFechaFin());
            totalHoras += d.toHours();
        }
        return totalHoras;
    }

    @Override
    public String toString() {
        return "Piloto: " + dni + " - " + getNombreCompleto() +
               " - Residencia: " + residencia +
               " - Nº vuelos: " + vuelos.size();
    }
}

