package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import clases.Socio;

import utilidades.Utilidades;
public class Main {

	private static ArrayList<Socio> socio = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {
		
		    int opcion;

		    do {
		        mostrarMenu();
		        opcion = Utilidades.leerInt("Elige opción: ");

		        switch (opcion) {
		            case 1 -> altaSocio();
		            case 2 -> listadoSocios();
		            case 3 -> buscarSocio();
		            case 4 -> menuEdades();
		            case 5 -> sociosAntiguos();
		            case 6 -> cumpleaniosMes();
		            case 7 -> buscarPorApellido();
		            case 8 -> bajaSocio();
		            case 9 -> System.out.println("Saliendo...");
		            default -> System.out.println("Opción no válida.");
		        }

		    } while (opcion != 9);
		}


	private static void mostrarMenu() {
	    System.out.println("""
	            ===== GESTIÓN DE SOCIOS =====
	            1. Alta de socio
	            2. Listado de socios
	            3. Buscar socio por DNI
	            4. Listado de socios por edades
	               a) Menores de una edad
	               b) Mayores de una edad
	               c) De una edad concreta
	               d) Listado con su edad y la edad en la que se dieron de alta
	            5. Socios con más de X años de antigüedad
	            6. Socios que cumplen años en un mes concreto
	            7. Socios cuyo apellido coincida o contenga un texto
	            8. Baja de socio
	            9. Salir
	            """);
	}

	private static void menuEdades() {
	    System.out.println("""
	            ---- Listado por edades ----
	            a) Menores de una edad
	            b) Mayores de una edad
	            c) De una edad concreta
	            d) Edad actual y edad al darse de alta
	            """);

	    char op = Utilidades.leerChar("Elige opción: ");

	    switch (Character.toLowerCase(op)) {
	        case 'a' -> menoresEdad();
	        case 'b' -> mayoresEdad();
	        case 'c' -> edadConcreta();
	        case 'd' -> edadActualYAlta();
	        default -> System.out.println("Opción no válida.");
	    }
	    }
	    private static void altaSocio() {
	    	File refFichero = new File("socio.dat");

			if (!refFichero.exists()) {

			    // Fichero NO existe → lo creamos con cabecera
			    try (ObjectOutputStream socioOStream =
			            new ObjectOutputStream(new FileOutputStream(refFichero))) {
			
	    	System.out.println ("Introduce nombre de socio nuevo. ");
	    	String nombre = Utilidades.introducirCadena("Nombre: ");
	    	System.out.println ("Introduce apellidos de socio nuevo. ");
	    	String apellidos = Utilidades.introducirCadena("Apellidos: ");
	    	System.out.println ("Introduce DNI de socio nuevo. ");
	    	String DNI = Utilidades.introducirCadena("DNI: ");
	    	System.out.println ("Introduce fecha de nacimiento de socio nuevo. ");
	    	LocalDate fechaNacimiento = Utilidades.pidoFechaDMA("fecha de nacimiento: ");
	    	System.out.println ("Introduce fecha de alta de socio nuevo. ");
	    	LocalDate fechaAlta = LocalDate.now();
	    	boolean ok;
	    	
	    	Socio nuevo = new Socio(nombre, apellidos, DNI, fechaNacimiento, fechaAlta);
	    	socio.add(nuevo);
	    	System.out.println("Socio dado de alta correctamente."); 
	    	System.out.println("Número de socio asignado: " + nuevo.getNumeroSocio());
			    }
			}
	    }
	    
	    public static void listadoSocios() {
	    	
	    	File refFichero=new File("socio.dat");
			
			// Lee el fichero
			if (!refFichero.exists())
			{
				System.out.println("Fichero no existente");
			}
			else
			{
				try (FileInputStream fileIStream = new FileInputStream(refFichero);
			             ObjectInputStream personaIStream = new ObjectInputStream(fileIStream)) 
				{
					while (true) {
						try {
							Socio aux = (Socio) personaIStream.readObject();
							System.out.println(aux);  
						} catch (EOFException eof) {
							break;
						}
					}

				} catch (Exception e) {
					System.out.println("Error de lectura: "+e.getMessage());
				}
			}
		}

	    
	    
	    public static void buscarSocio() {
	    }
	    public static void sociosAntiguos() {
	    }
	    public static void cumpleaniosMes() {
	    
	    }
	    public static void buscarPorApellido() {
	    	
	    }
	    public static void bajaSocio() {
	    	
	    }

}
