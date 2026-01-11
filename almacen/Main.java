package main;

import java.util.Scanner;

import clases.Inventario;

public class Main {
    public static void main(String[] args) {
        Inventario inv = new Inventario();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ INVENTARIO ===");
            System.out.println("1. Registrar/Actualizar producto");
            System.out.println("2. Consultar producto por código");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Mostrar inventario completo");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Código: ");
                    String codigo = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
                    inv.registrarProducto(codigo, nombre, precio, cantidad);
                    break;

                case 2:
                    System.out.print("Código: ");
                    String codigoConsulta = sc.nextLine();
                    inv.consultarProducto(codigoConsulta);
                    break;

                case 3:
                    System.out.print("Código: ");
                    String codigoEliminar = sc.nextLine();
                    inv.eliminarProducto(codigoEliminar);
                    break;

                case 4:
                    inv.mostrarInventario();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}