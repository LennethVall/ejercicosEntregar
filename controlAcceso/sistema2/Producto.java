package sistema2;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public void añadirStock(int cantidad) {
        this.stock += cantidad;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
               " | Nombre: " + nombre +
               " | Precio: " + precio +
               " | Stock: " + stock;
    }
}
