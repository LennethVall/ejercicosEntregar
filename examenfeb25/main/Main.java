package main;



import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Utilidades;
import clases.*;

public class Main {

    // Estructuras en memoria
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();

    public static void main(String[] args) {

        File fichP = new File("personas.obj");

        // 1. Cargar datos del fichero: trabajadores y clientes
        cargarDatosIniciales(fichP);

        // 2. Login obligatorio
        Trabajador trabajadorLogueado = login(fichP);

        if (trabajadorLogueado == null) {
            System.out.println("Credenciales incorrectas. Saliendo...");
            return;
        }

        // 3. Menú repetitivo
        int opcion;

        do {
            mostrarMenu();
            opcion = Utilidades.leerInt("Elige una opción: ");

            switch (opcion) {

                case 1:
                    // Ver trabajadores (directamente desde fichero)
                    verTrabajadores(fichP);
                    break;

                case 2:
                    // Ver clientes ordenados por nombre
                    verClientesOrdenados();
                    break;

                case 3:
                    // Añadir entrenamiento a un cliente
                    aniadirEntrenamientoCliente();
                    break;

                case 4:
                    // Añadir trabajador (directamente al fichero)
                    aniadirTrabajador(fichP);
                    break;

                case 5:
                    System.out.println("Saliendo de la aplicación...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);
    }

    // ---------------- MÉTODOS DEL MENÚ ----------------

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ GIMNASIO TARTANGA ---");
        System.out.println("1. Ver trabajadores");
        System.out.println("2. Ver clientes ordenados por nombre");
        System.out.println("3. Añadir entrenamiento a un cliente");
        System.out.println("4. Añadir trabajador");
        System.out.println("5. Salir");
    }

    // Estos métodos los implementaremos uno a uno
    private static void cargarDatosIniciales(File fichP) {

        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(fichP));

            while (true) {
                Object obj = ois.readObject();

                if (obj instanceof Cliente) {
                    Cliente c = (Cliente) obj;
                    listaClientes.add(c);
                }
                // Si es Trabajador → no lo guardamos en memoria
            }

        } catch (EOFException e) {
            // Fin del fichero → comportamiento normal
        } catch (IOException e) {
            System.out.println("Error leyendo el fichero.");
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero.");
            }
        }
    }

    private static Trabajador login(File fichP) {

        String usuario = Utilidades.introducirCadena("Usuario: ");
        String contrasena = Utilidades.introducirCadena("Contraseña: ");

        if (!fichP.exists()) {
            System.out.println("No existe el fichero de personas.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichP))) {

            while (true) {
                Object obj = ois.readObject();

                if (obj instanceof Trabajador) {
                    Trabajador t = (Trabajador) obj;

                    if (t.getUsuario().equals(usuario) &&
                        t.getContrasena().equals(contrasena)) {

                        return t;
                    }
                }
            }

        } catch (EOFException e) {
            // Fin del fichero
        } catch (Exception e) {
            System.out.println("Error en el login.");
        }

        return null;
    }

    private static void verTrabajadores(File fichP) {

        if (!fichP.exists()) {
            System.out.println("No existe el fichero de personas.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichP))) {

            System.out.println("\n--- LISTA DE TRABAJADORES ---");

            while (true) {
                Object obj = ois.readObject();

                if (obj instanceof Trabajador) {
                    Trabajador t = (Trabajador) obj;
                    t.visualizar();
                }
            }

        } catch (EOFException e) {
            // Fin del fichero → comportamiento normal
        } catch (Exception e) {
            System.out.println("Error leyendo trabajadores.");
        }
    }

    private static void verClientesOrdenados() {

        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        // Ordenar por nombre
        listaClientes.sort((c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));

        // Mostrar
        System.out.println("\n--- CLIENTES ORDENADOS POR NOMBRE ---");
        for (Cliente c : listaClientes) {
            c.visualizar();
        }
    }

    private static void aniadirEntrenamientoCliente() {

        String dni = Utilidades.introducirCadena("Introduce el DNI del cliente: ");

        // Buscar cliente
        Cliente clienteEncontrado = null;

        for (Cliente c : listaClientes) {
            if (c.getDNI().equalsIgnoreCase(dni)) {
                clienteEncontrado = c;
                break;
            }
        }

        if (clienteEncontrado == null) {
            System.out.println("No existe ningún cliente con ese DNI.");
            return;
        }

        // Pedir datos del entrenamiento
        String ejercicio = Utilidades.introducirCadena("Nombre del ejercicio: ");
        int repeticiones = Utilidades.leerInt("Número de repeticiones: ");

        // Añadir entrenamiento
        clienteEncontrado.aniadirEntrenamiento(ejercicio, repeticiones);

        System.out.println("Entrenamiento añadido correctamente.");
    }


    private static void aniadirTrabajador(File fichP) {

        String dni = Utilidades.introducirCadena("Introduce el DNI del nuevo trabajador: ");

        try {
            validarDNI(dni);
        } catch (DNIException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (dniTrabajadorExiste(fichP, dni)) {
            System.out.println("Ya existe un trabajador con ese DNI.");
            return;
        }

        String nombre = Utilidades.introducirCadena("Nombre: ");
        String usuario = Utilidades.introducirCadena("Usuario: ");
        String contrasena = Utilidades.introducirCadena("Contraseña: ");

        String cargoStr = Utilidades.introducirCadena("Cargo (monitor/recepcionista): ").toUpperCase();

        Cargo cargo;
        if (cargoStr.equals("MONITOR")) {
            cargo = Cargo.MONITOR;
        } else if (cargoStr.equals("RECEPCIONISTA")) {
            cargo = Cargo.RECEPCIONISTA;
        } else {
            System.out.println("Cargo no válido.");
            return;
        }

        Trabajador nuevo = new Trabajador(dni, nombre, usuario, contrasena, cargo);

        escribirTrabajadorEnFichero(fichP, nuevo);

        System.out.println("Trabajador añadido correctamente.");
    }

    private static boolean dniTrabajadorExiste(File fichP, String dni) {

        if (!fichP.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichP))) {

            while (true) {
                Object obj = ois.readObject();

                if (obj instanceof Trabajador) {
                    Trabajador t = (Trabajador) obj;
                    if (t.getDNI().equalsIgnoreCase(dni)) {
                        return true;
                    }
                }
            }

        } catch (EOFException e) {
            // Fin del fichero
        } catch (Exception e) {
            System.out.println("Error leyendo el fichero.");
        }

        return false;
    }
    private static void validarDNI(String dni) throws DNIException {

        Pattern modelo = Pattern.compile("\\d{8}[A-HJ-NP-TV-Z]");
        Matcher m = modelo.matcher(dni);

        if (!m.matches()) {
            throw new DNIException("El DNI no tiene el formato correcto.");
        }
    }

    private static void escribirTrabajadorEnFichero(File fichP, Trabajador t) {

        try {
            // Si el fichero está vacío → escribir cabecera normal
            if (fichP.length() == 0) {

                try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream(fichP))) {

                    oos.writeObject(t);
                }

            } else {
                // Si ya tiene contenido → append sin cabecera
                try (SinCabeceraObjectOutputStream oos =
                         new SinCabeceraObjectOutputStream(new FileOutputStream(fichP, true))) {

                    oos.writeObject(t);
                }
            }

        } catch (IOException e) {
            System.out.println("Error escribiendo en el fichero.");
        }
    }
}
