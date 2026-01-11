package clases;

public class Socio {
    private static final String NOMBRE_BIBLIOTECA = "Lectura Viva";
    private static final double CUOTA_BASE = 12.0;

    private String dni;
    private String nombreCompleto;
    private int mesAlta;
    private int añoAlta;
    private int limiteLibros;

    public Socio(String dni, String nombreCompleto, int mesAlta, int añoAlta, int limiteLibros) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.mesAlta = mesAlta;
        this.añoAlta = añoAlta;
        this.limiteLibros = limiteLibros;
    }

    public String getDni() { return dni; }
    public String getNombreCompleto() { return nombreCompleto; }
    public int getAñoAlta() { return añoAlta; }

    public double calcularCuotaFinal(int añoActual) {
        double cuota = CUOTA_BASE;
        if (limiteLibros > 3) {
            cuota += (limiteLibros - 3);
        }
        if ((añoActual - añoAlta) >= 8) {
            cuota -= 2;
        }
        return cuota;
    }

    @Override
    public String toString() {
        return "Biblioteca: " + NOMBRE_BIBLIOTECA +
               ", DNI: " + dni +
               ", Nombre: " + nombreCompleto +
               ", Alta: " + mesAlta + "/" + añoAlta +
               ", Límite libros: " + limiteLibros;
    }
}

