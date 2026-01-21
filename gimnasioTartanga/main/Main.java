package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Cargo;
import clases.DNIException;
import clases.Trabajador;
import clases.Utilidades;
import clases.SinCabeceraObjectOutputStream;
import clases.Persona;


public class Main {



	public static void main(String[] args) {
		
		
		
		File refFichero = new File("persona.obj");
		
//public void visualizar() {
		
		int opcion;

        do {
            mostrarMenu();
            opcion = Utilidades.leerInt("Elige una opción: ");

            switch (opcion) {

                case 1:
                    // Ver trabajadores (directamente desde fichero)
                    verTrabajadores();
                    break;

                case 2:
                    // Ver clientes ordenados por nombre
                    verClientesOrdenados();
                    break;

                case 3:
                    // Añadir entrenamiento a un cliente
                    aniadirEntrenamientoCliente();
                    break;

                case 4:
                    // Añadir trabajador (directamente al fichero)
                    aniadirTrabajador(refFichero);
                    break;

                case 5:
                    System.out.println("Saliendo de la aplicación...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);
    }
	
	 private static void mostrarMenu() {
	        System.out.println("\n--- MENÚ GIMNASIO TARTANGA ---");
	        System.out.println("1. Ver trabajadores");
	        System.out.println("2. Ver clientes ordenados por nombre");
	        System.out.println("3. Añadir entrenamiento a un cliente");
	        System.out.println("4. Añadir trabajador");
	        System.out.println("5. Salir");
	    }
	
		
		public static void aniadirTrabajador(File refFichero) {
			boolean cargoValido = false;
			boolean DNICorrecto = false;
			String DNI="";
			String nombre="";
			Stringh contraseña=""
			Strinh usuario="";
			Cargo cargo;
			while (!DNICorrecto) {
				try {

					DNI = Utilidades.introducirCadena("Introduce DNI de trabajador:");

					Pattern modelo = Pattern.compile("\\d{8}[A-HJ-NP-TV-Z]");
					Matcher matcher = modelo.matcher(DNI);

					if (!matcher.matches()) {

						throw new DNIException("El DNI no tiene el formato correcto.");
					} else {
						DNICorrecto=true;
						nombre = Utilidades.introducirCadena("inrtoduce el nombre: ");
						usuario = Utilidades.introducirCadena("introduce nombre de usuario: ");
						contraseña = Utilidades.introducirCadena("introduce la contraseña:");
						
						
						while (!cargoValido) {
							String cargoTexto = Utilidades.introducirCadena("Introduce el cargo (monitor / recepcionista): "); 
							if (cargoTexto.equalsIgnoreCase("monitor")) { 
								cargo = Cargo.monitor;
								cargoValido = true;
							} else if (cargoTexto.equalsIgnoreCase("recepcionista")) { 
								cargo = Cargo.recepcionista; 
								cargoValido = true;
							} else { 
								System.out.println("Cargo no válido. Debe ser 'monitor' o 'recepcionista'."); 
							}
						}
					}
				} catch (Exception e) {

					System.out.println("Error: DNI incorrecto: " + e.getMessage());

				}
			}

			
			try (SinCabeceraObjectOutputStream personaOStream = new SinCabeceraObjectOutputStream(
					new FileOutputStream(refFichero, true))) {

				personaOStream.writeObject(new Trabajador(DNI, nombre, usuario, contraseña, cargo));

			} catch (IOException e) {
				System.out.println("Error de escritura: " + e.getMessage());
			}

		}

	

	public static void verTrabajadores() {
	}
	

	public static void verClientesOrdenados() {
	}
	
	public static void aniadirEntrenamientoCliente() {
		
	}
	}