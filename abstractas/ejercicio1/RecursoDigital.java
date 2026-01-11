package ejercicio1;

abstract class RecursoDigital {
    protected String titulo;
    protected String autor;
    protected String fechaPublicacion;

    public RecursoDigital(String titulo, String autor, String fechaPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public abstract void mostrarInfo();
}

interface Prestable {
    String calcularFechaDevolucion();
    boolean tieneMulta(int diasRetraso);
}

class Ebook extends RecursoDigital implements Prestable {
    public Ebook(String titulo, String autor, String fecha) {
        super(titulo, autor, fecha);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Ebook: " + titulo + " de " + autor);
    }

    @Override
    public String calcularFechaDevolucion() {
        return "15 días desde el préstamo";
    }

    @Override
    public boolean tieneMulta(int diasRetraso) {
        return diasRetraso > 0;
    }
}

class Audiolibro extends RecursoDigital implements Prestable {
    public Audiolibro(String titulo, String autor, String fecha) {
        super(titulo, autor, fecha);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Audiolibro: " + titulo + " de " + autor);
    }

    @Override
    public String calcularFechaDevolucion() {
        return "10 días desde el préstamo";
    }

    @Override
    public boolean tieneMulta(int diasRetraso) {
        return diasRetraso > 0;
    }
}

class VideoEducativo extends RecursoDigital {
    public VideoEducativo(String titulo, String autor, String fecha) {
        super(titulo, autor, fecha);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Video educativo: " + titulo + " de " + autor);
    }
}



