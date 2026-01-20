package clases;



import java.time.LocalDate;

public class Entrenamiento {

    private String codigo;        // WOD-XXX
    private LocalDate fecha;      // Fecha del entrenamiento
    private String ejercicio;     // Nombre del ejercicio
    private int repeticiones;     // Número de repeticiones

    public Entrenamiento(String codigo, LocalDate fecha, String ejercicio, int repeticiones) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.ejercicio = ejercicio;
        this.repeticiones = repeticiones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @Override
    public String toString() {
        return codigo + " - " + fecha + " - " + ejercicio + " (" + repeticiones + " reps)";
    }
}
