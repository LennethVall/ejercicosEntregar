package clases;

public abstract class Publicacion {
    protected String fecha;   // dd/mm/aaaa o dd-mm-aaaa
    protected String titulo;

    public Publicacion(String fecha, String titulo) {
        this.fecha = fecha;
        this.titulo = titulo;
    }

    public String getFecha() { return fecha; }
    public String getTitulo() { return titulo; }

    @Override
    public String toString() {
        return "Fecha: " + fecha + ", Título: " + titulo;
    }
}
