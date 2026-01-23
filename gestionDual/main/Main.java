package main;

import java.io.File;


import utilidades.SuperUtil;

public class Main {

public static void main(String[] args) {
		
		
		
		File refFichero = new File("Agente.obj");
		
//public void visualizar() {
		
		int opcion;

        do {
            mostrarMenu();
            opcion = SuperUtil.leerInt("Elige una opción: ");

            switch (opcion) {

                case 1:
                    
                    introNuevaEmpresa(refFichero);
                    break;

                case 2:
                    
                    bajaTrabajador(refFichero);
                    break;

                case 3:
                    
                    mostrarValoracion(refFichero);
                    break;

               
                case 4:
                    System.out.println("Saliendo de la aplicación...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 4);
    }
	
	 private static void mostrarMenu() {
	        System.out.println("\n--- MENÚ GESTIÓN DE DUAL ---");
	        System.out.println("1. INTRODUCIR NUEVA EMPRESA");
	        System.out.println("2. DAR DE BAJA TRABAJADORES");
	        System.out.println("3. MOSTRAR VALORACION");
	        System.out.println("4. SALIR");
	      
	    }
	public void introNuevaEmpresa (File refFichero) {
		
	}
	public void bajaTrabajador (File refFichero) {
		
	}
	public void mostrarValoracion (File refFichero) {
		
	}
}
