package main;

import java.util.Scanner;

import clases.Articulo;
import clases.Libro;
import clases.Profesor;
import clases.Universidad;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Universidad uni = new Universidad();

        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Introducir profesor");
            System.out.println("2. Añadir publicación");
            System.out.println("3. Mostrar libros premiados por año");
            System.out.println("4. Listado por departamento");
            System.out.println("5. Listado de profesores");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    if (!Universidad.validarEmail(email)) {
                        System.out.println("Email no válido.");
                        break;
                    }

                    if (uni.buscarProfesor(email) != null) {
                        System.out.println("Ese profesor ya existe.");
                        break;
                    }

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Departamento: ");
                    String dep = sc.nextLine();

                    uni.añadirProfesor(email, nombre, dep);
                    System.out.println("Profesor añadido.");
                }

                case 2 -> {
                    System.out.print("Email del profesor: ");
                    String email = sc.nextLine();

                    Profesor p = uni.buscarProfesor(email);
                    if (p == null) {
                        System.out.println("No existe ese profesor.");
                        break;
                    }

                    System.out.println("Profesor encontrado:");
                    System.out.println(p);

                    String continuar;
                    do {
                        System.out.println("¿Libro (L) o Artículo (A)? ");
                        String tipo = sc.nextLine().toUpperCase();

                        System.out.print("Fecha: ");
                        String fecha = sc.nextLine();

                        System.out.print("Título: ");
                        String titulo = sc.nextLine();

                        if (tipo.equals("L")) {
                            System.out.print("ISBN: ");
                            String isbn = sc.nextLine();

                            System.out.print("Premiado (S/N): ");
                            boolean premiado = sc.nextLine().equalsIgnoreCase("S");

                            p.añadirPublicacion(new Libro(fecha, titulo, isbn, premiado));

                        } else if (tipo.equals("A")) {
                            System.out.print("Medio: ");
                            String medio = sc.nextLine();

                            p.añadirPublicacion(new Articulo(fecha, titulo, medio));
                        }

                        System.out.print("¿Añadir otra publicación? (S/N): ");
                        continuar = sc.nextLine();

                    } while (continuar.equalsIgnoreCase("S"));
                }

                case 3 -> {
                    System.out.print("Año: ");
                    int año = sc.nextInt();
                    sc.nextLine();
                    uni.mostrarLibrosPremiados(año);
                }

                case 4 -> uni.listadoPorDepartamento();

                case 5 -> uni.listarProfesores();

                case 6 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 6);

        sc.close();
    }
}
