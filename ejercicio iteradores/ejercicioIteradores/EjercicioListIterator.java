package ejercicioIteradores;
import java.util.ArrayList;
import java.util.ListIterator;

public class EjercicioListIterator {
    public static void main(String[] args) {
        ArrayList<String> alumnos = new ArrayList<>();
        alumnos.add("Luis");
        alumnos.add("Marta");
        alumnos.add("Juan");
        alumnos.add("Pedro");

        // ListIterator hacia delante
        ListIterator<String> lit = alumnos.listIterator();
        while (lit.hasNext()) {
            String nombre = lit.next();
            if (nombre.startsWith("P")) {
                lit.set(nombre.toUpperCase()); // cambia a mayúsculas
            }
            if (nombre.startsWith("M")) {
                lit.add("Marcos"); // añade justo después
            }
        }

        System.out.println("Lista después de modificaciones: " + alumnos);

        // Recorrido hacia atrás
        while (lit.hasPrevious()) {
            System.out.println("Atrás: " + lit.previous());
        }
    }
}

