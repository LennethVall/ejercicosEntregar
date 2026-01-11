package main;

import java.util.ArrayList;
import java.util.Scanner;

import clases.Articulo;
import clases.Factura;
import clases.ListaArticulo;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ListaArticulo lista = new ListaArticulo();
        lista.fillData();

        Factura factura = null;

        int opcion;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Listar todos los artículos");
            System.out.println("2. Artículos a reponer (stock < 50)");
            System.out.println("3. Artículos más caros");
            System.out.println("4. Buscar equivalentes por precio");
            System.out.println("5. Crear factura");
            System.out.println("6. Añadir línea a factura");
            System.out.println("7. Mostrar factura");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1:
                    System.out.println("\n===== LISTA DE ARTÍCULOS =====");
                    for (Articulo a : lista.getLista()) {
                        a.printCaracteristicas();
                        System.out.println("Código: " + a.getCodigo());
                        System.out.println("Precio: " + a.getPrecio() + " €");
                        System.out.println("Stock: " + a.getStock());
                        System.out.println("Sano: " + a.sano());
                        System.out.println("-----------------------------");
                    }
                    break;

                case 2:
                    System.out.println("\n===== ARTÍCULOS A REPONER =====");
                    for (Articulo a : lista.reponer()) {
                        System.out.println(a.getCodigo() + " - " + a.getNombre() + " (Stock: " + a.getStock() + ")");
                    }
                    break;

                case 3:
                    System.out.println("\n===== ARTÍCULOS MÁS CAROS =====");
                    for (Articulo a : lista.mascaro()) {
                        System.out.println(a.getCodigo() + " - " + a.getNombre() + " - " + a.getPrecio() + " €");
                    }
                    break;

                case 4:
                    System.out.print("Introduce el código del artículo: ");
                    String cod = sc.nextLine();
                    System.out.println("\n===== EQUIVALENTES =====");
                    ArrayList<Articulo> eq = lista.equivalentes(cod);
                    if (eq.isEmpty()) {
                        System.out.println("No hay equivalentes.");
                    } else {
                        for (Articulo a : eq) {
                            System.out.println(a.getCodigo() + " - " + a.getNombre());
                        }
                    }
                    break;

                case 5:
                    System.out.print("Número de factura: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre cliente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido cliente: ");
                    String apellido = sc.nextLine();

                    factura = new Factura(num, nombre, apellido);
                    System.out.println("Factura creada correctamente.");
                    break;

                case 6:
                    if (factura == null) {
                        System.out.println("Primero debes crear una factura (opción 5).");
                        break;
                    }

                    System.out.print("Código del artículo: ");
                    String c = sc.nextLine();
                    double precio = lista.precio(c);

                    if (precio == -1) {
                        System.out.println("Código no encontrado.");
                        break;
                    }

                    System.out.print("Cantidad: ");
                    int cant = sc.nextInt();
                    sc.nextLine();

                    factura.añadirLinea(c, cant, precio);
                    System.out.println("Línea añadida correctamente.");
                    break;

                case 7:
                    if (factura == null) {
                        System.out.println("No hay factura creada.");
                    } else {
                        factura.print();
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
