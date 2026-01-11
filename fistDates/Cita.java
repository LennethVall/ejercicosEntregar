package clases;

import java.time.LocalDate;

public class Cita {

    private LocalDate fecha;
    private String dniPareja;
    private Boolean exito; // null = sin evaluar

    public Cita(LocalDate fecha, String dniPareja) {
        this.fecha = fecha;
        this.dniPareja = dniPareja;
        this.exito = null;
    }

    public LocalDate getFecha() { return fecha; }
    public String getDniPareja() { return dniPareja; }
    public Boolean getExito() { return exito; }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    @Override
    public String toString() {
        String r = (exito == null) ? "SIN EVALUAR" : (exito ? "ÉXITO" : "FRACASO");
        return fecha + " | con: " + dniPareja + " | " + r;
    }
}
