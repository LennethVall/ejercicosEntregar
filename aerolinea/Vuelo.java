package clases;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vuelo {

    private String idVuelo;
    private String origen;
    private String destino;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String tipoAvion;

    public Vuelo(String origen, String destino,
                 LocalDateTime fechaInicio, LocalDateTime fechaFin,
                 String tipoAvion) throws OrigenInvalidoException {

        // Validar origen con Pattern
        Pattern modelo = Pattern.compile("[a-zA-Z]{5,10}");
        Matcher matcher = modelo.matcher(origen);

        if (!matcher.matches()) {
            throw new OrigenInvalidoException("Origen inválido: " + origen);
        }

        this.origen = origen.toUpperCase();
        this.destino = destino.toUpperCase();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoAvion = tipoAvion;
        this.idVuelo = generarIdVuelo();
    }

    private String generarIdVuelo() {
        String o = origen.substring(0, 3).toUpperCase();
        String d = destino.substring(0, 3).toUpperCase();
        return o + "/" + d;
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public String getTipoAvion() {
        return tipoAvion;
    }
    public String getDestino() {
        return destino;
    }

    public String getOrigen() {
        return origen;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id='" + idVuelo + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", inicio=" + fechaInicio +
                ", fin=" + fechaFin +
                ", tipoAvion='" + tipoAvion + '\'' +
                '}';
    }
}
