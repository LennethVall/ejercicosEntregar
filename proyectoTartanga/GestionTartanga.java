package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import clases.Alumno;
import clases.CargoProfesor;
import clases.DniInvalidoException;
import clases.Modulo;
import clases.Persona;
import clases.Profesor;
import clases.SalarioInvalidoException;

public class GestionTartanga {

    private static ArrayList<Persona> personas = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige opción: ");

            try {
                switch (opcion) {
                    case 1 -> matricularAlumnos();
                    case 2 -> listarAlumnos();
                    case 3 -> anadirProfesor();
                    case 4 -> listarProfesores();
                    case 5 -> infoAlumnoPorDni();
                    case 6 -> introducirNotasPorNombreApellido();
                    case 7 -> modificarNotaModulo();
                    case 8 -> bajaAlumnoPorDni();
                    case 9 -> bajaModuloDeAlumno();
                    case 10 -> mostrarPersonasOrdenadasPorApellido();
                    case 11 -> alumnosSuspendenModuloConcreto();
                    case 12 -> repetidoresDeCicloConcreto();
                    case 13 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 13);
    }

    private static void mostrarMenu() {
        System.out.println("""
                ==========================
                GESTIÓN TARTANGA - MENÚ
                ==========================
                1. Matricular alumno/a/s
                2. Listado de alumnos
                3. Añadir profesor/a
                4. Listado de profesores
                5. Info completa de un alumno (DNI)
                6. Introducir notas de alumno (por nombre y apellido)
                7. Modificar nota de un módulo
                8. Dar de baja alumno (por DNI)
                9. Dar de baja módulo de un alumno
                10. Mostrar todas las personas ordenadas por apellido
                11. Alumnos suspendidos en un módulo concreto
                12. Listado de repetidores de un ciclo concreto
                13. Salir
                """);
    }

    // ================= UTILIDADES =================

    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

    private static double leerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número decimal válido.");
            }
        }
    }

    private static LocalDate leerFecha(String msg) {
        while (true) {
            try {
                System.out.print(msg + " (dd/MM/yyyy): ");
                String linea = sc.nextLine();
                return LocalDate.parse(linea, fmtFecha);
            } catch (Exception e) {
                System.out.println("Fecha inválida.");
            }
        }
    }

    private static String leerLinea(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    private static boolean leerSiNo(String msg) {
        while (true) {
            String r = leerLinea(msg + " (S/N): ").trim().toUpperCase();
            if (r.equals("S")) return true;
            if (r.equals("N")) return false;
            System.out.println("Responde S o N.");
        }
    }

    // ================= BÚSQUEDAS AUXILIARES =================

    private static List<Alumno> buscarAlumnosPorDni(String dni) {
        List<Alumno> res = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Alumno a && a.getDni().equals(dni)) {
                res.add(a);
            }
        }
        return res;
    }

    private static List<Profesor> buscarProfesoresPorDni(String dni) {
        List<Profesor> res = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Profesor pr && pr.getDni().equals(dni)) {
                res.add(pr);
            }
        }
        return res;
    }

    private static List<Alumno> buscarAlumnosPorNombreApellido(String nombre, String apellido) {
        List<Alumno> res = new ArrayList<>();
        for (Persona p : personas) {
            if (p instanceof Alumno a) {
                if (a.getNombre().equalsIgnoreCase(nombre)
                        && a.getApellido().equalsIgnoreCase(apellido)) {
                    res.add(a);
                }
            }
        }
        return res;
    }

    // ================= OPCIÓN 1: MATRICULAR ALUMNO/S =================

    private static void matricularAlumnos() {
        boolean seguir;
        do {
            String dni = leerLinea("DNI del alumno/a: ");

            List<Alumno> alumnosConDni = buscarAlumnosPorDni(dni);
            if (!alumnosConDni.isEmpty()) {
                System.out.println("Alumno/a ya introducido con ese DNI.");
            } else {
                // puede existir como profesor, pero no pasa nada
                try {
                    String nombre = leerLinea("Nombre: ");
                    String apellido = leerLinea("Apellido: ");
                    String ciclo = leerLinea("Ciclo: ");
                    boolean repetidor = leerSiNo("¿Es repetidor/a?");

                    Alumno a = new Alumno(dni, nombre, apellido, ciclo, repetidor);

                    // Módulos
                    boolean seguirMod;
                    do {
                        String nombreModulo = leerLinea("Nombre del módulo a matricular: ");

                        if (a.tieneModuloConNombre(nombreModulo)) {
                            System.out.println("Ya está matriculado en un módulo con ese nombre.");
                        } else {
                            Modulo m = new Modulo(nombreModulo);
                            a.anadirModulo(m);
                            System.out.println("Módulo añadido: " + m.getCodigo() + " - " + m.getNombre());
                        }

                        seguirMod = leerSiNo("¿Añadir otro módulo a este alumno/a?");
                    } while (seguirMod);

                    personas.add(a);
                    System.out.println("Alumno/a matriculado correctamente.");

                } catch (DniInvalidoException e) {
                    System.out.println("Error en DNI: " + e.getMessage());
                }
            }

            seguir = leerSiNo("¿Matricular otro alumno/a?");

        } while (seguir);
    }

    // ================= OPCIÓN 2: LISTADO ALUMNOS =================

    private static void listarAlumnos() {
        boolean hay = false;
        for (Persona p : personas) {
            if (p instanceof Alumno) {
                p.visualizar();
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay alumnos matriculados.");
        }
    }

    // ================= OPCIÓN 3: AÑADIR PROFESOR =================

    private static void anadirProfesor() {
        String dni = leerLinea("DNI del profesor/a: ");

        List<Profesor> profesoresConDni = buscarProfesoresPorDni(dni);
        if (!profesoresConDni.isEmpty()) {
            System.out.println("Profesor/a ya introducido con ese DNI.");
            return;
        }

        try {
            String nombre = leerLinea("Nombre: ");
            String apellido = leerLinea("Apellido: ");
            double salario = leerDouble("Salario: ");
            LocalDate fechaNac = leerFecha("Fecha de nacimiento");

            CargoProfesor cargo;
            while (true) {
                String cargoStr = leerLinea("Cargo (PROFESOR/DIRECTOR/ADMINISTRATIVO): ").toUpperCase();
                try {
                    cargo = CargoProfesor.valueOf(cargoStr);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Cargo no válido, inténtalo de nuevo.");
                }
            }

            Profesor pr = new Profesor(dni, nombre, apellido, salario, fechaNac, cargo);
            personas.add(pr);
            System.out.println("Profesor/a añadido correctamente.");
        }
            catch (DniInvalidoException e) {
                System.out.println("Error en el DNI del profesor/a: " + e.getMessage());
            } catch (SalarioInvalidoException e) {
                System.out.println("Error en el salario del profesor/a: " + e.getMessage());
            }

    }

    // ================= OPCIÓN 4: LISTADO PROFESORES =================

    private static void listarProfesores() {
        boolean hay = false;
        for (Persona p : personas) {
            if (p instanceof Profesor) {
                p.visualizar();
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay profesores introducidos.");
        }
    }

    // ================= OPCIÓN 5: INFO COMPLETA ALUMNO POR DNI =================

    private static void infoAlumnoPorDni() {
        String dni = leerLinea("DNI del alumno/a: ");
        List<Alumno> alumnos = buscarAlumnosPorDni(dni);

        if (alumnos.isEmpty()) {
            System.out.println("No se ha encontrado alumno/a con ese DNI.");
            return;
        }

        for (Alumno a : alumnos) {
            System.out.println("ALUMNO: " + a.getDni() + " - " + a.getNombreCompleto());
            System.out.println("  Ciclo: " + a.getCiclo() + " | Repetidor: " + (a.isRepetidor() ? "Sí" : "No"));
            if (a.getModulos().isEmpty()) {
                System.out.println("  No tiene módulos matriculados.");
            } else {
                System.out.println("  Módulos:");
                for (Modulo m : a.getModulos().values()) {
                    if (m.getNota() == 0) {
                        System.out.println("    " + m.getCodigo() + " - " + m.getNombre() + " | SIN NOTA");
                    } else {
                        System.out.println("    " + m.getCodigo() + " - " + m.getNombre() + " | Nota: " + m.getNota());
                    }
                }
            }
        }
    }

    // ================= OPCIÓN 6: INTRODUCIR NOTAS POR NOMBRE Y APELLIDO =================

    private static void introducirNotasPorNombreApellido() {
        String nombre = leerLinea("Nombre del alumno/a: ");
        String apellido = leerLinea("Apellido del alumno/a: ");

        List<Alumno> alumnos = buscarAlumnosPorNombreApellido(nombre, apellido);

        if (alumnos.isEmpty()) {
            System.out.println("No se ha encontrado ningún alumno/a con ese nombre y apellido.");
            return;
        }

        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.getNombreCompleto() + " | Ciclo: " + a.getCiclo());
            List<Modulo> sinNota = new ArrayList<>();
            for (Modulo m : a.getModulos().values()) {
                if (m.getNota() == 0) {
                    sinNota.add(m);
                }
            }

            if (sinNota.isEmpty()) {
                System.out.println("  Todos los módulos tienen nota ya asignada.");
                continue;
            }

            for (Modulo m : sinNota) {
                System.out.println("  Módulo: " + m.getNombre() + " (" + m.getCodigo() + ") - SIN NOTA");
                boolean quiere = leerSiNo("¿Quieres introducir nota para este módulo?");
                if (quiere) {
                    int nota = leerNotaValida();
                    m.setNota(nota);
                    System.out.println("  Nota asignada: " + nota);
                }
            }
        }
    }

    private static int leerNotaValida() {
        while (true) {
            int nota = leerEntero("Introduce nota (1-10): ");
            if (nota >= 1 && nota <= 10) {
                return nota;
            }
            System.out.println("La nota debe estar entre 1 y 10.");
        }
    }

    // ================= OPCIÓN 7: MODIFICAR NOTA DE MÓDULO =================

    private static void modificarNotaModulo() {
        String dni = leerLinea("DNI del alumno/a: ");
        List<Alumno> alumnos = buscarAlumnosPorDni(dni);

        if (alumnos.isEmpty()) {
            System.out.println("No se ha encontrado alumno/a con ese DNI.");
            return;
        }

        // si hay varios alumnos con el mismo DNI, se aplicará a todos (caso raro)
        for (Alumno a : alumnos) {
            if (a.getModulos().isEmpty()) {
                System.out.println("El alumno/a " + a.getNombreCompleto() + " no tiene módulos.");
                continue;
            }

            System.out.println("Módulos de " + a.getNombreCompleto() + ":");
            for (Modulo m : a.getModulos().values()) {
                String notaStr = (m.getNota() == 0) ? "SIN NOTA" : String.valueOf(m.getNota());
                System.out.println("  " + m.getNombre() + " (" + m.getCodigo() + ") -> " + notaStr);
            }

            String nombreModulo = leerLinea("Nombre del módulo a modificar: ");
            Modulo mod = a.buscarModuloPorNombre(nombreModulo);

            if (mod == null) {
                System.out.println("No se ha encontrado ese módulo en este alumno.");
                continue;
            }

            if (mod.getNota() == 0) {
                System.out.println("La nota actual es 0 (SIN NOTA). No se puede modificar, pero sí introducir nota.");
                int nuevaNota = leerNotaValida();
                mod.setNota(nuevaNota);
                System.out.println("Nota establecida en " + nuevaNota);
            } else {
                System.out.println("Nota actual del módulo " + mod.getNombre() + ": " + mod.getNota());
                boolean cambiar = leerSiNo("¿Quieres cambiarla?");
                if (cambiar) {
                    int nuevaNota = leerNotaValida();
                    mod.setNota(nuevaNota);
                    System.out.println("Nota cambiada a " + nuevaNota);
                }
            }
        }
    }

    // ================= OPCIÓN 8: BAJA ALUMNO POR DNI =================

    private static void bajaAlumnoPorDni() {
        String dni = leerLinea("DNI del alumno/a a dar de baja: ");
        List<Alumno> alumnos = buscarAlumnosPorDni(dni);

        if (alumnos.isEmpty()) {
            System.out.println("No se ha encontrado alumno/a con ese DNI.");
            return;
        }

        for (Alumno a : alumnos) {
            System.out.println("Alumno a dar de baja:");
            a.visualizar();
            boolean confirmar = leerSiNo("¿Confirmar baja de este alumno/a?");
            if (confirmar) {
                personas.remove(a);
                System.out.println("Alumno/a dado de baja.");
            }
        }
    }

    // ================= OPCIÓN 9: BAJA MÓDULO DE ALUMNO =================

    private static void bajaModuloDeAlumno() {
        String dni = leerLinea("DNI del alumno/a: ");
        List<Alumno> alumnos = buscarAlumnosPorDni(dni);

        if (alumnos.isEmpty()) {
            System.out.println("No se ha encontrado alumno/a con ese DNI.");
            return;
        }

        for (Alumno a : alumnos) {
            if (a.getModulos().isEmpty()) {
                System.out.println("El alumno/a " + a.getNombreCompleto() + " no tiene módulos.");
                continue;
            }

            System.out.println("Módulos actuales de " + a.getNombreCompleto() + ":");
            for (Modulo m : a.getModulos().values()) {
                System.out.println("  " + m.getNombre() + " (" + m.getCodigo() + ")");
            }

            String nombreModulo = leerLinea("Nombre del módulo a dar de baja: ");
            Modulo mod = a.buscarModuloPorNombre(nombreModulo);

            if (mod == null) {
                System.out.println("No se ha encontrado ese módulo en este alumno.");
                continue;
            }

            boolean confirmar = leerSiNo("¿Confirmar baja del módulo " + mod.getNombre() + "?");
            if (confirmar) {
                a.eliminarModulo(mod);
                System.out.println("Módulo dado de baja.");

                if (a.getModulos().isEmpty()) {
                    System.out.println("El alumno/a ya no tiene módulos, se dará de baja automáticamente.");
                    personas.remove(a);
                }
            }
        }
    }

    // ================= OPCIÓN 10: PERSONAS ORDENADAS POR APELLIDO =================

    private static void mostrarPersonasOrdenadasPorApellido() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }

        List<Persona> copia = new ArrayList<>(personas);
        copia.sort(Comparator.comparing(Persona::getApellido, String.CASE_INSENSITIVE_ORDER)
                             .thenComparing(Persona::getNombre, String.CASE_INSENSITIVE_ORDER));

        for (Persona p : copia) {
            if (p instanceof Alumno) {
                System.out.print("[ALUMNO] ");
            } else if (p instanceof Profesor) {
                System.out.print("[PROFESOR] ");
            }
            System.out.println(p.getNombreCompleto() + " - DNI: " + p.getDni());
        }
    }

    // ================= OPCIÓN 11: ALUMNOS SUSPENDIDOS EN UN MÓDULO =================

    private static void alumnosSuspendenModuloConcreto() {
        String nombreModulo = leerLinea("Nombre del módulo: ");
        boolean encontrado = false;

        for (Persona p : personas) {
            if (p instanceof Alumno a) {
                Modulo m = a.buscarModuloPorNombre(nombreModulo);
                if (m != null && m.getNota() > 0 && m.getNota() < 5) {
                    System.out.println("Alumno: " + a.getNombreCompleto() +
                                       " | Módulo: " + m.getNombre() +
                                       " | Nota: " + m.getNota());
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("No hay alumnos suspendidos en ese módulo (o no evaluados / no matriculados).");
        }
    }

    // ================= OPCIÓN 12: REPETIDORES DE UN CICLO =================

    private static void repetidoresDeCicloConcreto() {
        String ciclo = leerLinea("Ciclo: ");
        boolean encontrado = false;

        for (Persona p : personas) {
            if (p instanceof Alumno a) {
                if (a.getCiclo().equalsIgnoreCase(ciclo) && a.isRepetidor()) {
                    System.out.println(a.getNombreCompleto() + " - DNI: " + a.getDni());
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("No hay repetidores en ese ciclo.");
        }
    }
}

