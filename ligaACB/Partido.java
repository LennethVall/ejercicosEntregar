package clases;

import java.time.LocalDate;

public class Partido {

    private LocalDate fecha;
    private int puntos;
    private int tirosLanzados;
    private int tirosAnotados;

    public Partido(LocalDate fecha, int puntos, int tirosLanzados, int tirosAnotados) {
        this.fecha = fecha;
        this.puntos = puntos;
        this.tirosLanzados = tirosLanzados;
        this.tirosAnotados = tirosAnotados;
    }

    public int getPuntos() { return puntos; }
    public int getTirosLanzados() { return tirosLanzados; }
    public int getTirosAnotados() { return tirosAnotados; }
}

