package ejercicioIteradores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class EjercicioIteradores {
	public static void main(String[] args) {
        
        ArrayList<String> alumnos = new ArrayList<>();
        alumnos.add("Ana");
        alumnos.add("Luis");
        alumnos.add("Marta");
        alumnos.add("Juan");
        alumnos.add("Pedro");

       
        //  Iterator
        
        
        Iterator<String> it = alumnos.iterator();

       
        System.out.println("Lista original:");
        while (it.hasNext()) {
            System.out.println(it.next());
        }

       
        it = alumnos.iterator(); 
        while (it.hasNext()) {
            String nombre = it.next();
            if (nombre.length() < 4) {
                it.remove();
            }
        }

       
        System.out.println("Lista después de eliminar (<4 letras):");
        System.out.println(alumnos);

       
        //ListIterator
       
       
       ListIterator<String> listIt = alumnos.listIterator();

        
        while (listIt.hasNext()) {
            String nombre = listIt.next();

            
            if (nombre.startsWith("P")) {
                listIt.set(nombre.toUpperCase());
            }

           
            if (nombre.startsWith("M")) {
                listIt.add("Marcos");
            }
        }

       
        System.out.println("Lista después de modificaciones:");
        System.out.println(alumnos);

      
        System.out.println("Recorrido hacia atrás:");
        while (listIt.hasPrevious()) {
            System.out.println(listIt.previous());
        }

       
        // Información adicional


       
        listIt = alumnos.listIterator();

     
        System.out.println("Recorrido hacia delante con índices:");
        while (listIt.hasNext()) {
            int index = listIt.nextIndex();
            String nombre = listIt.next();
            System.out.println("Índice " + index + ": " + nombre);
        }

       
        System.out.println("Recorrido hacia atrás con índices:");
        while (listIt.hasPrevious()) {
            int index = listIt.previousIndex();
            String nombre = listIt.previous();
            System.out.println("Índice " + index + ": " + nombre);
        }
    }

}
