package ejercicioIteradores;

import java.util.ArrayList;
import java.util.ListIterator;

public class EjercicioIndices {
    public static void main(String[] args) {
        ArrayList<String> alumnos = new ArrayList<>();
        alumnos.add("Luis");
        alumnos.add("Marta");
        alumnos.add("Juan");
        alumnos.add("PEDRO"); // ya modificado en parte 2

        ListIterator<String> lit = alumnos.listIterator();

        // Recorrido hacia delante con índices
        while (lit.hasNext()) {
            System.out.println("Índice: " + lit.nextIndex() + " -> " + lit.next());
        }

        // Recorrido hacia atrás con índices
        while (lit.hasPrevious()) {
            System.out.println("Índice: " + lit.previousIndex() + " -> " + lit.previous());
        }
    }
}

