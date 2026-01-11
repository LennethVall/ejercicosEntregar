package principal;

import alumnos.Alumno;
import alumnos.Ciclo;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Principal {

    private static ArrayList<Alumno> alumnos = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = Util.leerInt();

            if (opcion == 1) {
                matricularAlumno();
            } else if (opcion == 2) {
                listarAlumnos();
            } else if (opcion == 3) {
                infoAlumno();
            } else if (opcion == 4) {
                modificarAlumno();
            } else if (opcion == 5) {
                modificarRepetidorDAW();
            } else if (opcion == 6) {
                bajaAlumno();
            } else if (opcion == 7) {
                System.out.println("¡Hasta luego!");
            } else {
                System.out.println("Opción no válida");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
  
        System.out.println("1. Matricular alumno/a");
        System.out.println("2. Listado de alumnos");
        System.out.println("3. Información de un alumno por NIF");
        System.out.println("4. Modificar datos de un alumno");
        System.out.println("5. Modificar repetidor en DAW por edad");
        System.out.println("6. Dar de baja a un alumno");
        System.out.println("7. Salir");
        System.out.print("Opción: ");
    }

  
    // 1. MATRICULAR ALUMNO/A
    
    public static void matricularAlumno() {
        String otraVez;
        do {
            System.out.print("\nNIF: ");
            String nif = Util.introducirCadena().toUpperCase();

            boolean tieneDAM = false;
            boolean tieneDAW = false;

           
            for (Alumno a : alumnos) {
                if (a.getNif().equalsIgnoreCase(nif)) {
                    if (a.getCiclo() == Ciclo.DAM) {
                        tieneDAM = true;
                    } else if (a.getCiclo() == Ciclo.DAW) {
                        tieneDAW = true;
                    }
                }
            }

            
            if (tieneDAM && tieneDAW) {
                System.out.println("Alumno/a ya introducido");
            }
            
            else if (tieneDAM || tieneDAW) {
                Ciclo cicloMatricular;

                if (tieneDAM) {
                    cicloMatricular = Ciclo.DAW;
                    System.out.println("Ya está matriculado en DAM. Se matriculará en DAW.");
                } else {
                    cicloMatricular = Ciclo.DAM;
                    System.out.println("Ya está matriculado en DAW. Se matriculará en DAM.");
                }

                System.out.print("Nombre: ");
                String nombre = Util.introducirCadena();

                System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
                LocalDate fecha = LocalDate.parse(Util.introducirCadena());

                System.out.print("¿Repetidor? (s/n): ");
                char resp = Character.toUpperCase(Util.leerChar());
                boolean repetidor = false;
                if (resp == 'S') {
                    repetidor = true;
                }

                alumnos.add(new Alumno(nif, nombre, fecha, cicloMatricular, repetidor));
                System.out.println("Alumno/a matriculado correctamente en " + cicloMatricular);
            }
            
            else {
                System.out.print("Ciclo (DAM/DAW): ");
                String entrada = Util.introducirCadena().toUpperCase();
                Ciclo cicloMatricular;

                if (entrada.equals("DAM")) {
                    cicloMatricular = Ciclo.DAM;
                } else {
                    cicloMatricular = Ciclo.DAW;
                }

                System.out.print("Nombre: ");
                String nombre = Util.introducirCadena().trim();

                System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
                LocalDate fecha = LocalDate.parse(Util.introducirCadena());

                System.out.print("¿Repetidor? (s/n): ");
                char resp = Character.toUpperCase(Util.leerChar());
                boolean repetidor = false;
                if (resp == 'S') {
                    repetidor = true;
                }

                alumnos.add(new Alumno(nif, nombre, fecha, cicloMatricular, repetidor));
                System.out.println("Alumno/a matriculado correctamente en " + cicloMatricular);
            }

            System.out.print("\n¿Matricular otro alumno/a? (s/n): ");
            otraVez = Util.introducirCadena().toLowerCase();

        } while (otraVez.equals("s"));
    }

   
    // 2. LISTADO
    
    public static void listarAlumnos() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos introducidos");
            return;
        }
        System.out.println("\n=== LISTADO DE ALUMNOS ===");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }

   
    // 3. INFO POR NIF 
    
    public static void infoAlumno() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos introducidos");
            return;
        }
        System.out.print("Introduce NIF: ");
        String nif = Util.introducirCadena().toUpperCase();

        boolean encontrado = false;
        for (Alumno a : alumnos) {
            if (a.getNif().equalsIgnoreCase(nif)) {
                System.out.println(a);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Alumno/a no encontrado");
        }
    }

   
    // 4. MODIFICAR DATOS 
   
    private static void modificarAlumno() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos introducidos");
            return;
        }

        System.out.print("NIF del alumno a modificar: ");
        String nif = Util.introducirCadena().toUpperCase();

       
        ArrayList<Alumno> candidatos = new ArrayList<>();
        for (Alumno a : alumnos) {
            if (a.getNif().equalsIgnoreCase(nif)) {
                candidatos.add(a);
            }
        }

        if (candidatos.isEmpty()) {
            System.out.println("No existe ningún alumno con ese NIF");
            return;
        }

       
        Alumno elegido;
        if (candidatos.size() == 1) {
            elegido = candidatos.get(0);
        } else {
            System.out.println("Alumno matriculado en ambos ciclos. Elige cuál modificar:");
            for (int i = 0; i < candidatos.size(); i++) {
                System.out.println((i + 1) + ". " + candidatos.get(i));
            }
            System.out.print("Elige número (1 o 2): ");
            int opcion = Util.leerInt();
            if (opcion < 1 || opcion > candidatos.size()) {
                System.out.println("Número incorrecto");
                return;
            }
            elegido = candidatos.get(opcion - 1);
        }

       
        int opcion;
        do {
            System.out.println("\nDatos actuales:");
            System.out.println(elegido);
            System.out.println("\n¿Qué deseas modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Fecha de nacimiento");
            System.out.println("3. Repetidor");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elige opción (1-4): ");
            opcion = Util.leerInt();

            if (opcion == 1) {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = Util.introducirCadena();
                elegido.setNombre(nuevoNombre);
                System.out.println("Nombre modificado correctamente");
            }
            else if (opcion == 2) {
                System.out.print("Nueva fecha de nacimiento (AAAA-MM-DD): ");
                String fechaStr = Util.introducirCadena();
                LocalDate nuevaFecha = LocalDate.parse(fechaStr);
                elegido.setFechaNacimiento(nuevaFecha);
                System.out.println("Fecha modificada correctamente");
            }
            else if (opcion == 3) {
                System.out.print("¿Es repetidor? (s/n): ");
                char resp = Character.toUpperCase(Util.leerChar());
                if (resp == 'S') {
                    elegido.setRepetidor(true);
                } else if (resp == 'N') {
                    elegido.setRepetidor(false);
                } else {
                    System.out.println("Respuesta no válida. No se ha cambiado.");
                }
            }
            else if (opcion == 4) {
                System.out.println("Volviendo al menú principal...");
            }
            else {
                System.out.println("Opción no válida");
            }

           
            if (opcion != 4) {
                System.out.print("\n¿Deseas modificar algo más? (s/n): ");
                char seguir = Character.toUpperCase(Util.leerChar());
                if (seguir != 'S') {
                    opcion = 4;
                    System.out.println("Volviendo al menú principal...");
                }
            }

        } while (opcion != 4);

        System.out.println("Modificación finalizada.");
    }

    
    // 5. MODIFICAR REPETIDOR DAW POR EDAD
   
    public static void modificarRepetidorDAW() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos introducidos");
            return;
        }
        System.out.print("Edad: ");
        int edad = Util.leerInt();

        int contador = 0;
        for (Alumno a : alumnos) {
            int edadActual = Period.between(a.getFechaNacimiento(), LocalDate.now()).getYears();
            if (a.getCiclo() == Ciclo.DAW && edadActual == edad) {
                a.setRepetidor(true);
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No existen alumnos DAW con esa edad");
        } else {
            System.out.println("Se han marcado como repetidores " + contador + " alumno(s)");
        }
    }

    
    // 6. DAR DE BAJA
   
    public static void bajaAlumno() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos introducidos");
            return;
        }
        System.out.print("NIF a dar de baja: ");
        String nif = Util.introducirCadena().toUpperCase();

        ArrayList<Alumno> aBorrar = new ArrayList<>();
        for (Alumno a : alumnos) {
            if (a.getNif().equalsIgnoreCase(nif)) {
                aBorrar.add(a);
            }
        }

        if (aBorrar.isEmpty()) {
            System.out.println("Alumno/a no encontrado");
            return;
        }

        System.out.println("Se eliminarán los siguientes registros:");
        for (Alumno a : aBorrar) {
            System.out.println(a);
        }

        System.out.print("¿Confirmar baja? (s/n): ");
        char confirm = Character.toUpperCase(Util.leerChar());
        if (confirm == 'S') {
        	alumnos.removeAll(aBorrar);
        	
        
            System.out.println("Alumno/a dado de baja correctamente");
        } else {
            System.out.println("Operación cancelada");
        }
    }
}