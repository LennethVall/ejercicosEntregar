package sistema2;

import java.util.HashMap;

public class Inventario {

    private HashMap<String, Producto> productos;

    public Inventario() {
        productos = new HashMap<>();
    }

    // 1. Registrar o actualizar stock
    public void registrarProducto(String codigo, String nombre, double precio, int cantidad) {
        if (productos.containsKey(codigo)) {
            productos.get(codigo).añadirStock(cantidad);
            System.out.println("Stock actualizado.");
        } else {
            productos.put(codigo, new Producto(codigo, nombre, precio, cantidad));
            System.out.println("Producto añadido.");
        }
    }

    // 2. Consultar producto
    public void consultarProducto(String codigo) {
        Producto p = productos.get(codigo);
        if (p == null) {
            System.out.println("No existe ese producto.");
        } else {
            System.out.println(p);
        }
    }

    // 3. Eliminar producto
    public void eliminarProducto(String codigo) {
        if (productos.remove(codigo) != null) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No existe ese producto.");
        }
    }

    // 4. Mostrar inventario
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("Inventario vacío.");
            return;
        }
        productos.values().forEach(System.out::println);
    }
}
