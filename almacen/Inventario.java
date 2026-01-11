package clases;

import java.util.HashMap;

public class Inventario {
    private HashMap<String, Producto> productos = new HashMap<>();

    // 1. Registrar producto (añadir o actualizar stock)
    public void registrarProducto(String codigo, String nombre, double precio, int cantidad) {
        if (productos.containsKey(codigo)) {
            productos.get(codigo).sumarStock(cantidad);
            System.out.println("Stock actualizado para " + codigo);
        } else {
            Producto p = new Producto(codigo, nombre, precio, cantidad);
            productos.put(codigo, p);
            System.out.println("Producto añadido: " + codigo);
        }
    }

    // 2. Consultar producto por código
    public void consultarProducto(String codigo) {
        Producto p = productos.get(codigo);
        if (p == null) {
            System.out.println("Producto no encontrado");
        } else {
            System.out.println(p);
        }
    }

    // 3. Eliminar producto
    public void eliminarProducto(String codigo) {
        productos.remove(codigo);
        System.out.println("Producto eliminado: " + codigo);
    }

    // 4. Mostrar inventario completo
    public void mostrarInventario() {
        for (Producto p : productos.values()) {
            System.out.println(p);
        }
    }
}