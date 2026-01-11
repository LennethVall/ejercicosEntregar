package ejercicioIteradores;

import java.util.ArrayList;
import java.util.Iterator;

public class EjercicioIterator {
    public static void main(String[] args) {
        ArrayList<String> alumnos = new ArrayList<>();
        alumnos.add("Ana");
        alumnos.add("Luis");
        alumnos.add("Marta");
        alumnos.add("Juan");
        alumnos.add("Pedro");

        // Iterator para recorrer
        Iterator<String> it = alumnos.iterator();
        while (it.hasNext()) {
            String nombre = it.next();
            System.out.println(nombre);
            if (nombre.length() < 4) {
                it.remove(); // elimina nombres cortos
            }
        }

        System.out.println("Lista después de eliminar: " + alumnos);
    }
}

