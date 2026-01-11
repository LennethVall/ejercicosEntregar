package clases;

import java.time.LocalDate;

public abstract class Publicacion {
    private LocalDate fecha;
    private String titulo;

    public Publicacion(LocalDate fecha, String titulo) {
        this.fecha = fecha;
        this.titulo = titulo;
    }

    public LocalDate getFecha() { return fecha; }
    public String getTitulo() { return titulo; }

    @Override
    public String toString() {
        return fecha + " - " + titulo;
    }
}

