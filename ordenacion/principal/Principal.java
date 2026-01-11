
	package principal;

import clases.Alumno;
import clases.Ciclo;
import clases.Repetidor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


public class Principal {

    private static ArrayList<Alumno> alumnos = new ArrayList<>(); 

    // --- BLOQUE ESTATICO: Inicialización de 8 alumnos ---
    static {
        // Los datos cumplen con los requisitos de coincidencia
        alumnos.add(new Alumno("111A", "Juan", "Pérez", "García", LocalDate.of(2000, 5, 10), Ciclo.DAM, Repetidor.NO)); 
        alumnos.add(new Alumno("222B", "Juan", "Pérez", "López", LocalDate.of(1999, 11, 25), Ciclo.DAW, Repetidor.SI));  
        alumnos.add(new Alumno("333C", "Juan", "Martínez", "Soto", LocalDate.of(2001, 3, 15), Ciclo.DAM, Repetidor.NO)); 
        
        // Otros alumnos para completar la lista
        alumnos.add(new Alumno("444D", "María", "Gómez", "Ruiz", LocalDate.of(1998, 7, 20), Ciclo.DAW, Repetidor.SI));
        alumnos.add(new Alumno("555E", "Ana", "Díaz", "Vega", LocalDate.of(2002, 1, 5), Ciclo.DAM, Repetidor.NO));
        alumnos.add(new Alumno("666F", "Pedro", "García", "Ramos", LocalDate.of(2000, 9, 30), Ciclo.DAW, Repetidor.NO));
        alumnos.add(new Alumno("777G", "Laura", "Alonso", "Sanz", LocalDate.of(1997, 4, 12), Ciclo.DAM, Repetidor.SI));
        alumnos.add(new Alumno("888H", "Zoe", "Vidal", "Mora", LocalDate.of(2003, 10, 8), Ciclo.DAW, Repetidor.NO));
    }


    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = Util.leerInt();

            if (opcion == 1) {
                menuOrdenacion();
            } else if (opcion == 2) {
                System.out.println("¡Hasta luego!");
            } else {
                System.out.println("Opción no válida");
            }
        } while (opcion != 2); 
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL SIMPLIFICADO ---");
        System.out.println("1. Submenú de Ordenación");
        System.out.println("2. Salir");
        System.out.print("Opción: ");
    }
    
    // --- SUBMENÚ DE ORDENACIÓN ---

    public static void menuOrdenacion() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ DE ORDENACIÓN ---");
            System.out.println("1. Por Nombre de Pila (Alfabético)");
            System.out.println("2. Por Primer Apellido (Alfabético)");
            System.out.println("3. Por Repetidor/No Repetidor");
            System.out.println("4. Orden Natural (Nombre, Apellido1, Apellido2)");
            System.out.println("5. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = Util.leerInt();

            if (opcion == 1) {
                ordenarPorNombre();
            } else if (opcion == 2) {
                ordenarPorApellido1();
            } else if (opcion == 3) {
                ordenarPorRepetidor();
            } else if (opcion == 4) {
                ordenarPorOrdenNatural();
            } else if (opcion == 5) {
                System.out.println("Volviendo al menú principal...");
            } else {
                System.out.println("Opción no válida");
            }

        } while (opcion != 5);
    }
    
    
    // --- MÉTODOS DE ORDENACIÓN ---

    // 1. Ordenación por Nombre de Pila
    public static void ordenarPorNombre() {
        // Comparator: Ordena por nombre de pila (alfabético)
        Collections.sort(alumnos, (a1, a2) -> a1.getNombrePila().compareToIgnoreCase(a2.getNombrePila()));
        System.out.println("\n*** LISTA ORDENADA POR NOMBRE DE PILA (ALFABÉTICO) ***");
        listarAlumnos();
    }
    
    // 2. Ordenación por Primer Apellido
    public static void ordenarPorApellido1() {
        // Comparator: Ordena por primer apellido (alfabético)
        Collections.sort(alumnos, (a1, a2) -> a1.getApellido1().compareToIgnoreCase(a2.getApellido1()));
        System.out.println("\n*** LISTA ORDENADA POR PRIMER APELLIDO (ALFABÉTICO) ***");
        listarAlumnos();
    }
    
    // 3. Ordenación por Repetidor/No Repetidor
    public static void ordenarPorRepetidor() {
        // Comparator: Ordena por Enum Repetidor (NO primero, luego SI)
        Collections.sort(alumnos, (a1, a2) -> a1.getRepetidor().compareTo(a2.getRepetidor()));
        System.out.println("\n*** LISTA ORDENADA POR REPETIDOR (NO primero, luego SI) ***");
        listarAlumnos();
    }
    
    // 4. Ordenación por Orden Natural (Nombre, Apellido1, Apellido2)
    public static void ordenarPorOrdenNatural() {
        // Usa el compareTo() implementado en Alumno
        Collections.sort(alumnos);
        System.out.println("\n*** LISTA ORDENADA POR ORDEN NATURAL ***");
        listarAlumnos();
    }

    public static void listarAlumnos() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos.");
            return;
        }
        System.out.println("==========================================");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
        System.out.println("==========================================");
    }
}