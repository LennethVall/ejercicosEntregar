package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Cargo;
import clases.Cliente;
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
                    verTrabajadores(refFichero);
                    break;

                case 2:
                    // Ver clientes ordenados por nombre
                    verClientesOrdenados(refFichero);
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
			String contraseña="";
			String usuario="";
			Cargo cargo=null;
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
						
						int opcionCargo;
						
						while (!cargoValido) {
							opcionCargo = Utilidades.leerInt(1, 2, "Introduce el cargo (1-Monitor / 2-Recepcionista):");

						    if (opcionCargo == 1) {
						        cargo = Cargo.monitor; // El valor del Enum
						        cargoValido = true;
						    } else if (opcionCargo == 2) {
						        cargo = Cargo.recepcionista; // El valor del Enum
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

	

		public static void verTrabajadores(File refFichero) {
		    if (!refFichero.exists()) {
		        System.out.println("Fichero no existente");
		        return;
		    }

		    try (ObjectInputStream personaIStream = new ObjectInputStream(new FileInputStream(refFichero))) {
		        System.out.println("--- TRABAJADORES REGISTRADOS ---");
		        
		        while (true) {
		           
		            Persona p = (Persona) personaIStream.readObject();
		            
		            
		            if (p instanceof Trabajador) {
		                p.visualizar(); 
		               
		            }
		        }
		    } catch (EOFException eof) {
		       
		    } catch (Exception e) {
		        System.out.println("Error de lectura: " + e.getMessage());
		    }
		}
	

		public static void verClientesOrdenados(File refFichero) {
		    
		    ArrayList<Cliente> listaClientes = new ArrayList<>();

		    if (!refFichero.exists()) {
		        System.out.println("No existe el fichero.");
		        return;
		    }

		  
		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(refFichero))) {
		        while (true) {
		            Object obj = ois.readObject();
		            if (obj instanceof Cliente) {
		                listaClientes.add((Cliente) obj);
		            }
		        }
		    } catch (EOFException e) {
		       
		    } catch (Exception e) {
		        System.out.println("Error al leer: " + e.getMessage());
		    }

		   
		    listaClientes.sort((c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));

		   
		    System.out.println("--- CLIENTES ORDENADOS POR NOMBRE ---");
		    if (listaClientes.isEmpty()) {
		        System.out.println("No hay clientes registrados.");
		    } else {
		        for (Cliente c : listaClientes) {
		            c.visualizar(); 
		        }
		    }
		}
	
	public static void aniadirEntrenamientoCliente() {
		
	}
	}
