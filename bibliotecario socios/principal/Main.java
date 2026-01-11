package principal;

import java.util.*;

import clases.Bibliotecario;
import clases.Socio;

public class Main {
    private static ArrayList<Socio> socios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            switch(opcion) {
                case 1: introducirSocio(sc); break;
                case 2: mostrarTodos(); break;
                case 3: mostrarBibliotecarios(); break;
                case 4: mostrarPorSeccion(sc); break;
                case 5: buscarPorNombre(sc); break;
                case 6: filtrarPorCuota(sc); break;
                case 7: bibliotecariosPorAntiguedad(sc); break;
                case 8: darDeBaja(sc); break;
                case 9: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida");
            }
        } while(opcion != 9);
    }

    private static void mostrarMenu() {
        System.out.println("1. Introducir socio/bibliotecario");
        System.out.println("2. Visualizar todos");
        System.out.println("3. Visualizar bibliotecarios");
        System.out.println("4. Visualizar bibliotecarios por sección");
        System.out.println("5. Buscar por nombre");
        System.out.println("6. Filtrar por cuota");
        System.out.println("7. Bibliotecarios por antigüedad");
        System.out.println("8. Dar de baja por DNI");
        System.out.println("9. Salir");
    }

 // 1. Introducir socio o bibliotecario
    private static void introducirSocio(Scanner sc) {
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        // comprobar si ya existe
        for (Socio s : socios) {
            if (s.getDni().equals(dni)) {
                System.out.println("Error: ya existe un socio con ese DNI.");
                return;
            }
        }

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Mes de alta: ");
        int mes = sc.nextInt(); sc.nextLine();
        System.out.print("Año de alta: ");
        int año = sc.nextInt(); sc.nextLine();
        System.out.print("Límite de libros: ");
        int limite = sc.nextInt(); sc.nextLine();

        System.out.print("¿Es bibliotecario? (s/n): ");
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Sección: ");
            String seccion = sc.nextLine();
            socios.add(new Bibliotecario(dni, nombre, mes, año, limite, seccion));
        } else {
            socios.add(new Socio(dni, nombre, mes, año, limite));
        }
        System.out.println("Registro añadido correctamente.");
    }
 // 2. Mostrar todos los socios (incluye bibliotecarios)
    private static void mostrarTodos() {
        int añoActual = Calendar.getInstance().get(Calendar.YEAR);
        for (Socio s : socios) {
            System.out.println(s + " | Cuota final: " + s.calcularCuotaFinal(añoActual) + "€");
        }
        }
     // 3. Mostrar solo bibliotecarios
        private static void mostrarBibliotecarios() {
            int añoActual = Calendar.getInstance().get(Calendar.YEAR);
            for (Socio s : socios) {
                if (s instanceof Bibliotecario) {
                    System.out.println(s + " | Cuota final: " + s.calcularCuotaFinal(añoActual) + "€");
                }
            }
            }
        
         // 4. Mostrar bibliotecarios de una sección concreta
            private static void mostrarPorSeccion(Scanner sc) {
                System.out.print("Introduce sección: ");
                String seccion = sc.nextLine();
                boolean encontrado = false;
                System.out.println("Bibliotecario/s de la sección " + seccion + ":");
                for (Socio s : socios) {
                    if (s instanceof Bibliotecario) {
                        Bibliotecario b = (Bibliotecario) s;
                        if (b.getSeccion().equalsIgnoreCase(seccion)) {
                            System.out.println(b.getNombreCompleto());
                            encontrado = true;
                        }
                    }
                }
                if (!encontrado) {
                    System.out.println("No hay bibliotecarios en esa sección.");
                }
            }
            
             // 5. Buscar por nombre o parte del nombre
                private static void buscarPorNombre(Scanner sc) {
                    System.out.print("Introduce nombre o parte: ");
                    String parte = sc.nextLine();
                    boolean encontrado = false;
                    int añoActual = Calendar.getInstance().get(Calendar.YEAR);

                    for (Socio s : socios) {
                        if (s.getNombreCompleto().toLowerCase().contains(parte.toLowerCase())) {
                            int años = añoActual - s.getAñoAlta();
                            System.out.println("DNI: " + s.getDni() + ", Años: " + años +
                                               ", Bibliotecario: " + (s instanceof Bibliotecario));
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No se encontraron coincidencias.");
                    }
                

    }
    
             // 6. Filtrar por cuota mensual
                private static void filtrarPorCuota(Scanner sc) {
                    System.out.print("Introduce cuota mínima: ");
                    double cuota = sc.nextDouble(); sc.nextLine();
                    int añoActual = Calendar.getInstance().get(Calendar.YEAR);
                    boolean encontrado = false;

                    for (Socio s : socios) {
                        double cuotaFinal = s.calcularCuotaFinal(añoActual);
                        if (cuotaFinal >= cuota) {
                            System.out.println(s + " | Cuota: " + cuotaFinal + "€ | Bibliotecario: " + (s instanceof Bibliotecario));
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No se encontraron socios con esa cuota.");
                    }
                }
             // 7. Bibliotecarios por antigüedad
                private static void bibliotecariosPorAntiguedad(Scanner sc) {
                    System.out.print("Introduce nº de años: ");
                    int años = sc.nextInt(); sc.nextLine();
                    int añoActual = Calendar.getInstance().get(Calendar.YEAR);
                    boolean encontrado = false;

                    for (Socio s : socios) {
                        if (s instanceof Bibliotecario) {
                            int antiguedad = añoActual - s.getAñoAlta();
                            if (antiguedad >= años) {
                                System.out.println(s);
                                encontrado = true;
                            }
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No hay bibliotecarios con esa antigüedad.");
                    }
                }
             // 8. Dar de baja por DNI
                private static void darDeBaja(Scanner sc) {
                    System.out.print("Introduce DNI: ");
                    String dni = sc.nextLine();
                    Iterator<Socio> it = socios.iterator();
                    boolean eliminado = false;

                    while (it.hasNext()) {
                        Socio s = it.next();
                        if (s.getDni().equals(dni)) {
                            it.remove();
                            eliminado = true;
                            System.out.println("Socio eliminado.");
                        }
                    }
                    if (!eliminado) {
                        System.out.println("No existe socio con ese DNI.");
                    }
                }

}
