package clases;

import java.time.LocalDate;

public class Libro extends Publicacion {
    private String isbn;
    private boolean premiado;

    public Libro(LocalDate fecha, String titulo, String isbn, boolean premiado) {
        super(fecha, titulo);
        this.isbn = isbn;
        this.premiado = premiado;
    }

    public String getIsbn() { return isbn; }
    public boolean isPremiado() { return premiado; }

    @Override
    public String toString() {
        return super.toString() + " | ISBN: " + isbn + " | Premiado: " + premiado;
    }
}
