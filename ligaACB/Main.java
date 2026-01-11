package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import clases.Jugador;
import clases.Partido;
import clases.Persona;
import clases.Tecnico;

public class Main {

    private static ArrayList<Persona> personal = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige opción: ");

            switch (opcion) {
                case 1 -> altaPersonal();
                case 2 -> anadirPartidoJugador();
                case 3 -> modificarTecnico();
                case 4 -> listadoJugadoresEquipo();
                case 5 -> estadisticaCargosPorEquipo();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("""
                ===== LIGA ACB =====
                1. Alta personal (Jugador/Técnico)
                2. Añadir partido a jugador
                3. Modificar técnico
                4. Listado jugadores por equipo
                5. Estadística cargos por equipo
                6. Salir
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
        for (Persona p : personal) {
            if (p.getDni().equals(dni)) return p;
        }
        return null;
    }

    // ---------------- OPCIÓN 1: ALTA PERSONAL ----------------

    private static void altaPersonal() {
        System.out.println("1. Jugador");
        System.out.println("2. Técnico");
        int tipo = leerEntero("Elige tipo: ");

        String dni = leerLinea("DNI: ");
        if (buscarPorDni(dni) != null) {
            System.out.println("Ese DNI ya existe.");
            return;
        }

        String nombre = leerLinea("Nombre: ");
        String apellido = leerLinea("Apellido: ");
        LocalDate fechaNac = leerFecha("Fecha nacimiento");

        if (tipo == 1) {
            int dorsal = leerEntero("Dorsal: ");
            String equipo = leerLinea("Equipo: ");
            String posicion = leerLinea("Posición: ");

            personal.add(new Jugador(dni, nombre, apellido, fechaNac, dorsal, equipo, posicion));
            System.out.println("Jugador añadido.");

        } else if (tipo == 2) {
            String equipo = leerLinea("Equipo: ");
            double salario = Double.parseDouble(leerLinea("Salario: "));
            String cargo = leerLinea("Cargo: ");

            personal.add(new Tecnico(dni, nombre, apellido, fechaNac, equipo, salario, cargo));
            System.out.println("Técnico añadido.");
        }
    }

    // ---------------- OPCIÓN 2: AÑADIR PARTIDO ----------------

    private static void anadirPartidoJugador() {
        String dni = leerLinea("DNI del jugador: ");
        Persona p = buscarPorDni(dni);

        if (!(p instanceof Jugador jugador)) {
            System.out.println("No existe jugador con ese DNI.");
            return;
        }

        boolean seguir;
        do {
            LocalDate fecha = leerFecha("Fecha del partido");
            int puntos = leerEntero("Puntos anotados: ");
            int lanzados = leerEntero("Tiros lanzados: ");
            int anotados = leerEntero("Tiros anotados: ");

            jugador.addPartido(new Partido(fecha, puntos, lanzados, anotados));
            System.out.println("Partido añadido.");

            seguir = leerLinea("¿Añadir otro partido? (S/N): ").equalsIgnoreCase("S");

        } while (seguir);
    }

    // ---------------- OPCIÓN 3: MODIFICAR TÉCNICO ----------------

    private static void modificarTecnico() {
        String dni = leerLinea("DNI del técnico: ");
        Persona p = buscarPorDni(dni);

        if (!(p instanceof Tecnico tecnico)) {
            System.out.println("No existe técnico con ese DNI.");
            return;
        }

        String nuevoEquipo = leerLinea("Nuevo equipo (ENTER para no cambiar): ");
        if (!nuevoEquipo.isBlank()) tecnico.setEquipo(nuevoEquipo);

        String nuevoCargo = leerLinea("Nuevo cargo (ENTER para no cambiar): ");
        if (!nuevoCargo.isBlank()) tecnico.setCargo(nuevoCargo);

        String salarioStr = leerLinea("Nuevo salario (ENTER para no cambiar): ");
        if (!salarioStr.isBlank()) tecnico.setSalario(Double.parseDouble(salarioStr));

        System.out.println("Datos modificados.");
    }

    // ---------------- OPCIÓN 4: LISTADO JUGADORES POR EQUIPO ----------------

    private static void listadoJugadoresEquipo() {
        String equipo = leerLinea("Equipo: ");

        ArrayList<Jugador> lista = new ArrayList<>();
        for (Persona p : personal) {
            if (p instanceof Jugador j && j.getEquipo().equalsIgnoreCase(equipo)) {
                lista.add(j);
            }
        }

        if (lista.isEmpty()) {
            System.out.println("No hay jugadores en ese equipo.");
            return;
        }

        int totalPuntos = 0;
        int totalEdad = 0;

        System.out.println("Jugadores del equipo " + equipo + ":");

        for (Jugador j : lista) {
            int partidos = j.getPartidos().size();
            int puntos = j.getPartidos().stream().mapToInt(Partido::getPuntos).sum();
            int lanzados = j.getPartidos().stream().mapToInt(Partido::getTirosLanzados).sum();
            int anotados = j.getPartidos().stream().mapToInt(Partido::getTirosAnotados).sum();

            double acierto = lanzados == 0 ? 0 : (anotados * 100.0 / lanzados);

            totalPuntos += puntos;
            totalEdad += j.getEdad();

            System.out.printf("%s. %s  Edad:%d  Partidos:%d  Puntos:%d  Acierto:%.2f%%\n",
                    j.getNombre().charAt(0),
                    j.getApellido(),
                    j.getEdad(),
                    partidos,
                    puntos,
                    acierto);
        }

        System.out.println("Media edad: " + (totalEdad / lista.size()));
        System.out.println("Total puntos acumulados: " + totalPuntos);
    }

    // ---------------- OPCIÓN 5: ESTADÍSTICA CARGOS POR EQUIPO ----------------

    private static void estadisticaCargosPorEquipo() {
        Map<String, Map<String, Integer>> mapa = new HashMap<>();

        for (Persona p : personal) {
            if (p instanceof Tecnico t) {
                mapa.putIfAbsent(t.getEquipo(), new HashMap<>());
                Map<String, Integer> cargos = mapa.get(t.getEquipo());
                cargos.put(t.getCargo(), cargos.getOrDefault(t.getCargo(), 0) + 1);
            }
        }

        if (mapa.isEmpty()) {
            System.out.println("No hay técnicos registrados.");
            return;
        }

        for (String equipo : mapa.keySet()) {
            System.out.println("Equipo " + equipo);
            for (String cargo : mapa.get(equipo).keySet()) {
                System.out.println("  " + cargo + ": " + mapa.get(equipo).get(cargo));
            }
        }
    }
}
