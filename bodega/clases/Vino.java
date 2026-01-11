package clases;

public class Vino extends Articulo {

    private String color;
    private String origen;
    private int año;
    private String uva;

    public Vino(String codigo, String nombre, String marca, double precio, int stock,
                String color, String origen, int año, String uva) {
        super(codigo, nombre, marca, precio, stock);
        this.color = color;
        this.origen = origen;
        this.año = año;
        this.uva = uva;
    }

    @Override
    public void printCaracteristicas() {
        System.out.println("VINO: " + nombre + " (" + marca + ")");
        System.out.println("Color: " + color + ", Origen: " + origen + ", Año: " + año);
        System.out.println("Uva: " + uva);
    }

    @Override
    public boolean sano() {
        return origen.equalsIgnoreCase("rioja");
    }
}
