package clases;

import java.time.LocalDate;

public class Articulo extends Publicacion {
    private String medio;

    public Articulo(LocalDate fecha, String titulo, String medio) {
        super(fecha, titulo);
        this.medio = medio;
    }

    public String getMedio() { return medio; }

    @Override
    public String toString() {
        return super.toString() + " | Medio: " + medio;
    }
}

