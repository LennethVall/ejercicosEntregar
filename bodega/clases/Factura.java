package clases;

import java.util.ArrayList;

public class Factura {

    private int numero;
    private String nombre;
    private String apellido;
    private ArrayList<LineaFactura> lineasFactura;
    private double total;

    public Factura(int numero, String nombre, String apellido) {
        this.numero = numero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lineasFactura = new ArrayList<>();
        this.total = 0;
    }

    public void añadirLinea(String codigo, int cantidad, double precioUnitario) {
        LineaFactura linea = new LineaFactura(codigo, cantidad, precioUnitario);
        lineasFactura.add(linea);
        total += linea.getTotalLinea();
    }

    public void print() {
        System.out.println("FACTURA Nº " + numero);
        System.out.println("Cliente: " + nombre + " " + apellido);
        System.out.println("----------------------------------");
        for (LineaFactura lf : lineasFactura) {
            lf.print();
        }
        System.out.println("----------------------------------");
        System.out.println("TOTAL FACTURA: " + total);
    }
}
