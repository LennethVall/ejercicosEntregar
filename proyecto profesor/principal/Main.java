package principal;

import utilidades.Utilidades;
import clases.*;   // Profesor, Publicacion, Libro, Articulo

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Profesor> profesores = new HashMap<>();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Introducir nuevo profesor");
            System.out.println("2. Añadir publicación");
            System.out.println("3. Mostrar libros premiados de un año");
            System.out.println("4. Listado por departamento");
            System.out.println("5. Listar profesores");
            System.out.println("6. Salir");

            int opcion = Utilidades.leerInt(1, 6, "Elige opción (1-6): ");

            switch (opcion) {
                case 1 -> {
                    // Alta de profesor
                    String email = Utilidades.leerString(50, "Introduce email del profesor: ");
                    if (profesores.containsKey(email)) {
                        System.out.println("Error: ya existe un profesor con ese email.");
                    } else {
                        String nombre = Utilidades.leerString(30, "Introduce nombre: ");
                        String departamento = Utilidades.leerString(30, "Introduce departamento: ");
                        Profesor p = new Profesor(email, nombre, departamento);
                        profesores.put(email, p);
                        System.out.println("Profesor añadido correctamente.");
                    }
                }
                case 2 -> {
                    // Añadir publicación
                    String email = Utilidades.leerString(50, "Introduce email del profesor: ");
                    Profesor p = profesores.get(email);
                    if (p == null) {
                        System.out.println("Error: no existe ese profesor.");
                    } else {
                        System.out.println("Profesor encontrado: " + p.getNombre());
                        boolean mas = true;
                        while (mas) {
                            System.out.println("¿Qué tipo de publicación quieres añadir?");
                            System.out.println("1. Libro");
                            System.out.println("2. Artículo");
                            int tipo = Utilidades.leerInt(1, 2, "Elige tipo: ");
                            
                            // Fecha y título comunes
                            LocalDate fecha = leerFecha("Introduce fecha (dd/MM/yyyy o dd-MM-yyyy): ");
                            String titulo = Utilidades.leerString(50, "Introduce título: ");

                            if (tipo == 1) {
                                String isbn = Utilidades.leerString(20, "Introduce ISBN: ");
                                boolean premiado = Utilidades.esBoolean("¿Premiado? (si/no): ");
                                p.addPublicacion(new Libro(fecha, titulo, isbn, premiado));
                            } else {
                                String medio = Utilidades.leerString(30, "Introduce medio: ");
                                p.addPublicacion(new Articulo(fecha, titulo, medio));
                            }
                            mas = Utilidades.esBoolean("¿Quieres añadir otra publicación? (si/no): ");
                        }
                    }
                }
                case 3 -> {
                    // Mostrar libros premiados de un año
                    int year = Utilidades.leerInt(1900, 2100, "Introduce año: ");
                    boolean encontrado = false;
                    for (Profesor p : profesores.values()) {
                        for (Publicacion pub : p.getPublicaciones()) {
                            if (pub instanceof Libro libro) {
                                if (libro.isPremiado() && libro.getFecha().getYear() == year) {
                                    encontrado = true;
                                    System.out.println(libro.getFecha() + " | " + libro.getTitulo() +
                                        " | ISBN: " + libro.getIsbn() +
                                        " | Profesor: " + p.getNombre() +
                                        " | Departamento: " + p.getDepartamento());
                                }
                            }
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No se han encontrado libros premiados en " + year);
                    }
                }

                case 4 -> {
                    // Listado por departamento
                    if (profesores.isEmpty()) {
                        System.out.println("No hay profesores registrados.");
                    } else {
                        Map<String, List<Profesor>> porDepto = new HashMap<>();
                        for (Profesor p : profesores.values()) {
                            porDepto.computeIfAbsent(p.getDepartamento(), k -> new ArrayList<>()).add(p);
                        }
                        for (String depto : porDepto.keySet()) {
                            List<Profesor> lista = porDepto.get(depto);
                            lista.sort(Comparator
                                .comparingInt((Profesor p2) -> p2.getPublicaciones().size()).reversed()
                                .thenComparing(Profesor::getNombre));
                            System.out.println("\nDepartamento: " + depto);
                            for (Profesor p2 : lista) {
                                System.out.println(p2.getNombre() + " | Publicaciones: " + p2.getPublicaciones().size());
                            }
                        }
                    }
                }

                case 5 -> {
                    // Listar profesores
                    if (profesores.isEmpty()) {
                        System.out.println("No hay profesores registrados.");
                    } else {
                        for (Profesor p : profesores.values()) {
                            System.out.println(p);
                        }
                    }
                }

                case 6 -> {
                    salir = true;
                    System.out.println("Programa finalizado.");
}
                }
            }
        }
       
    public static boolean validarEmail(String email) {
        if (email == null) return false;
        String regex = "^[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-zA-Z]+$";
        return email.matches(regex);
    }
    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean valido = false;

        // Dos formatos aceptados
        DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (!valido) {
            String fechaStr = Utilidades.leerString(10, mensaje);
            try {
                if (fechaStr.contains("/")) {
                    fecha = LocalDate.parse(fechaStr, formato1);
                } else if (fechaStr.contains("-")) {
                    fecha = LocalDate.parse(fechaStr, formato2);
                } else {
                    throw new DateTimeParseException("Formato inválido", fechaStr, 0);
                }
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: formato de fecha incorrecto. Usa dd/MM/yyyy o dd-MM-yyyy.");
            }
        }
        return fecha;
    }
}


