package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import clases.Cita;
import clases.Empleado;
import clases.Persona;
import clases.Pretendiente;

public class Main {

    private static ArrayList<Persona> personas = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige opción: ");

            switch (opcion) {
                case 1 -> introducirPersona();
                case 2 -> gestionarCita();
                case 3 -> listadoCitasExitosas();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }

    private static void mostrarMenu() {
        System.out.println("""
                ===== FIRST DATES =====
                1. Introducir Emplead@ o Pretendiente
                2. Gestionar Cita a un Pretendiente
                3. Listado de citas exitosas
                4. Salir
                """);
    }

    // ---------------- UTILIDADES ----------------

    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Número inválido.");
            }
        }
    }

    private static LocalDate leerFecha(String msg) {
        while (true) {
            try {
                System.out.print(msg + " (dd/MM/yyyy): ");
                return LocalDate.parse(sc.nextLine(), fmt);
            } catch (Exception e) {
                System.out.println("Fecha inválida.");
            }
        }
    }

    private static String leerLinea(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    private static Persona buscarPorDni(String dni) {
        for (Persona p : personas) {
            if (p.getDni().equals(dni)) return p;
        }
        return null;
    }

    // ---------------- OPCIÓN 1 ----------------

    private static void introducirPersona() {
        System.out.println("1. Emplead@");
        System.out.println("2. Pretendiente");
        int tipo = leerEntero("Elige tipo: ");

        String dni = leerLinea("DNI: ");
        if (buscarPorDni(dni) != null) {
            System.out.println("Ese DNI ya existe.");
            return;
        }

        String nombre = leerLinea("Nombre: ");
        LocalDate fechaNac = leerFecha("Fecha nacimiento");
        String genero = leerLinea("Género: ");
        String localidad = leerLinea("Localidad: ");

        if (tipo == 1) {
            String cargo = leerLinea("Cargo: ");
            personas.add(new Empleado(dni, nombre, fechaNac, genero, localidad, cargo));
            System.out.println("Empleado añadido.");
        } else if (tipo == 2) {
            String generoBuscado = leerLinea("Género que busca: ");
            personas.add(new Pretendiente(dni, nombre, fechaNac, genero, localidad, generoBuscado));
            System.out.println("Pretendiente añadido.");
        } else {
            System.out.println("Tipo no válido.");
        }
    }

    // ---------------- OPCIÓN 2 ----------------

    private static void gestionarCita() {
        String dni = leerLinea("DNI del pretendiente: ");
        Persona p = buscarPorDni(dni);

        if (!(p instanceof Pretendiente pretendiente)) {
            System.out.println("No existe pretendiente con ese DNI.");
            return;
        }

        // Mostrar info
        pretendiente.visualizar();

        // Submenú
        System.out.println("""
                1. Establecer nueva cita
                2. Evaluar cita
                3. Volver
                """);

        int op = leerEntero("Elige opción: ");

        switch (op) {
            case 1 -> establecerNuevaCita(pretendiente);
            case 2 -> evaluarCita(pretendiente);
            case 3 -> {}
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void establecerNuevaCita(Pretendiente p) {

        // Buscar pretendientes compatibles
        ArrayList<Pretendiente> compatibles = new ArrayList<>();

        for (Persona per : personas) {
            if (per instanceof Pretendiente pr &&
                !pr.getDni().equals(p.getDni()) &&
                pr.getGenero().equalsIgnoreCase(p.getGeneroBuscado())) {
                compatibles.add(pr);
            }
        }

        if (compatibles.isEmpty()) {
            System.out.println("No hay pretendientes compatibles.");
            return;
        }

        System.out.println("Pretendientes compatibles:");
        for (Pretendiente pr : compatibles) {
            System.out.println("  " + pr.getDni() + " - " + pr.getNombre());
        }

        String dniElegido = leerLinea("DNI del pretendiente elegido: ");

        Pretendiente pareja = null;
        for (Pretendiente pr : compatibles) {
            if (pr.getDni().equals(dniElegido)) pareja = pr;
        }

        if (pareja == null) {
            System.out.println("DNI no válido.");
            return;
        }

        LocalDate fecha = leerFecha("Fecha de la cita");

        // Crear citas cruzadas
        p.addCita(new Cita(fecha, pareja.getDni()));
        pareja.addCita(new Cita(fecha, p.getDni()));

        System.out.println("Cita creada correctamente.");
    }

    private static void evaluarCita(Pretendiente p) {
        LocalDate fecha = leerFecha("Fecha de la cita a evaluar");

        Cita cita = null;
        for (Cita c : p.getCitas()) {
            if (c.getFecha().equals(fecha)) cita = c;
        }

        if (cita == null) {
            System.out.println("No existe cita en esa fecha.");
            return;
        }

        boolean exito = leerLinea("¿Éxito? (S/N): ").equalsIgnoreCase("S");
        cita.setExito(exito);

        // Actualizar en la pareja
        Persona pareja = buscarPorDni(cita.getDniPareja());
        if (pareja instanceof Pretendiente pr) {
            for (Cita c : pr.getCitas()) {
                if (c.getFecha().equals(fecha) &&
                    c.getDniPareja().equals(p.getDni())) {
                    c.setExito(exito);
                }
            }
        }

        System.out.println("Cita evaluada.");
    }

    // ---------------- OPCIÓN 3 ----------------

    private static void listadoCitasExitosas() {
        // localidad -> nº éxitos
        Map<String, Integer> mapa = new TreeMap<>();

        for (Persona p : personas) {
            if (p instanceof Pretendiente pr) {
                for (Cita c : pr.getCitas()) {
                    if (Boolean.TRUE.equals(c.getExito())) {
                        mapa.put(pr.getLocalidad(),
                                mapa.getOrDefault(pr.getLocalidad(), 0) + 1);
                    }
                }
            }
        }

        if (mapa.isEmpty()) {
            System.out.println("No hay citas exitosas.");
            return;
        }

        System.out.println("Citas exitosas por localidad:");
        mapa.forEach((loc, num) ->
                System.out.println(loc + ": " + num));
    }
}
