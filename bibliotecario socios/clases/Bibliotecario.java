package clases;

public class Bibliotecario extends Socio {
    private static final double PLUS_BIBLIOTECARIO = 30.0;
    private String seccion;

    public Bibliotecario(String dni, String nombreCompleto, int mesAlta, int añoAlta, int limiteLibros, String seccion) {
        super(dni, nombreCompleto, mesAlta, añoAlta, limiteLibros);
        this.seccion = seccion;
    }

    @Override
    public double calcularCuotaFinal(int añoActual) {
        return super.calcularCuotaFinal(añoActual) + PLUS_BIBLIOTECARIO;
    }

    public String getSeccion() { return seccion; }

    @Override
    public String toString() {
        return super.toString() + ", Sección: " + seccion + ", (Bibliotecario)";
    }
}

