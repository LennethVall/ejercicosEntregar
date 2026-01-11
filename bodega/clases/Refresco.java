package clases;

public class Refresco extends Articulo {

    private String sabor;
    private boolean zumo;
    private boolean gas;
    private int azucar;

    public Refresco(String codigo, String nombre, String marca, double precio, int stock,
                    String sabor, boolean zumo, boolean gas, int azucar) {
        super(codigo, nombre, marca, precio, stock);
        this.sabor = sabor;
        this.zumo = zumo;
        this.gas = gas;
        this.azucar = azucar;
    }

    @Override
    public void printCaracteristicas() {
        System.out.println("REFRESCO: " + nombre + " (" + marca + ")");
        System.out.println("Sabor: " + sabor + ", Zumo: " + zumo + ", Gas: " + gas);
        System.out.println("Azúcar: " + azucar + "g");
    }

    @Override
    public boolean sano() {
        return azucar < 25;
    }
}
