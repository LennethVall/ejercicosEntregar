package clases;

public class LineaFactura implements Facturacion {

    private String codigo;
    private int cantidad;
    private double totalLinea;

    public static final double IVA = 0.21;

    public LineaFactura(String codigo, int cantidad, double precioUnitario) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.totalLinea = calcularTotal(precioUnitario);
    }

    @Override
    public double calcularTotal(double precioUnitario) {
        return cantidad * precioUnitario * (1 + IVA);
    }

    public double getTotalLinea() { return totalLinea; }

    @Override
    public String toString() {
        return "Producto: " + codigo +
           " | Cantidad: " + cantidad +
           " | Total: " + totalLinea;
    }
}

