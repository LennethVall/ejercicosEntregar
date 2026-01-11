package principal;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Principal {
	public static void main(String[] args) throws Exception {
		
		int opc;
		do{
			System.out.println("1.-Altas\n2.-Listado\n3.-Consulta\n4.-Modificación\n5.-Borrado\n6.-Listado ordenado por apellido\n9.-SALIR");
					opc=Util.leerInt();
					switch(opc){
					case 1:
						/* Añade 3 productos al fichero productos.dat
						 * si no existe el fichero, lo crea
						 * el nombre del producto se lo pide al usuario, el stock es aleatorio
						 */
						altaVariosProductos();
						break;
					case 2:
						listado();
						break;
					case 3:
						/* Pide al usuario el nombre del producto,
						 * el programa busca el producto en el fichero 
						 * y le muestra por pantalla el stock 
						 * suponemos que solo hay un producto con ese nombre
						 */
						consulta();
						break;
					case 4:
						/* Pide al usuario el nombre del producto,
						 * el programa busca el producto en el fichero 
						 * y le muestra por pantalla el stock.
						 * luego le pide el nuevo stock y guarda el cambio,
						 * suponemos que solo hay un producto con ese nombre
						 * usar un fichero auxiliar
						 */
						modificacion();
						break;
					case 5:
						/* Pide al usuario el nombre del producto,
						 * el programa busca el producto en el fichero,
						 * suponemos que solo hay un producto con ese nombre,
						 * se borra
						 * usar un fichero auxiliar
						 */
						borrado();
						break;
					case 6:
						/*
						 * usar un fichero auxiliar para guardar el ArrayList ordenado
						 */
						ordenarPorNombre();
						break;
					}
		}while (opc!=9);
	}
	
		private static void altaUnProducto() {
		    System.out.println("Introduce el nombre del producto:");
		    String nombre = Util.introducirCadena();

		    System.out.println("Introduce el stock del producto:");
		    int stock = Util.leerInt();

		    Producto p = new Producto(nombre, stock);

		    File refFichero = new File("productos.dat");

		    try {
		        if (!refFichero.exists()) {
		            // Fichero NO existe → creamos uno nuevo con cabecera
		            try (ObjectOutputStream oos =
		                     new ObjectOutputStream(new FileOutputStream(refFichero))) {
		                oos.writeObject(p);
		            }
		        } else {
		            // Fichero SÍ existe → añadimos sin cabecera
		            try (SinCabeceraObjectOutputStream oos =
		                     new SinCabeceraObjectOutputStream(new FileOutputStream(refFichero, true))) {
		                oos.writeObject(p);
		            }
		        }

		        System.out.println("Producto añadido correctamente.");

		    } catch (IOException e) {
		        System.out.println("Error al dar de alta el producto: " + e.getMessage());
		    }
		}
		private static void altaVariosProductos() {
		    System.out.println("¿Cuántos productos quieres dar de alta?");
		    int cantidad = Util.leerInt();

		    File refFichero = new File("productos.dat");

		    for (int i = 0; i < cantidad; i++) {

		        System.out.println("\nIntroduce el nombre del producto " + (i + 1) + ":");
		        String nombre = Util.introducirCadena();

		        System.out.println("Introduce el stock del producto " + (i + 1) + ":");
		        int stock = Util.leerInt();

		        Producto p = new Producto(nombre, stock);

		        try {
		            if (!refFichero.exists() && i == 0) {
		                // Si el fichero NO existe y es el primer producto → cabecera
		                try (ObjectOutputStream oos =
		                         new ObjectOutputStream(new FileOutputStream(refFichero))) {
		                    oos.writeObject(p);
		                }
		            } else {
		                // Si el fichero existe → añadir sin cabecera
		                try (SinCabeceraObjectOutputStream oos =
		                         new SinCabeceraObjectOutputStream(new FileOutputStream(refFichero, true))) {
		                    oos.writeObject(p);
		                }
		            }

		            System.out.println("Producto " + (i + 1) + " añadido correctamente.");

		        } catch (IOException e) {
		            System.out.println("Error al dar de alta el producto: " + e.getMessage());
		        }
		    }
		}


	}
	private static void listado() {
		
		    File refFichero = new File("productos.dat");

		    if (!refFichero.exists()) {
		        System.out.println("No existe el fichero de productos.");
		        return;
		    }

		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(refFichero))) {

		        System.out.println("\n--- LISTADO DE PRODUCTOS ---\n");

		        while (true) {
		            try {
		                Producto p = (Producto) ois.readObject();
		                System.out.println("Nombre: " + p.getNombre() + " | Stock: " + p.getStock());
		            } catch (EOFException e) {
		                // Fin del fichero → salimos del bucle
		                break;
		            }
		        }

		        System.out.println("\n--- FIN DEL LISTADO ---\n");

		    } catch (FileNotFoundException e) {
		        System.out.println("No se encuentra el fichero.");
		    } catch (IOException e) {
		        System.out.println("Error de lectura: " + e.getMessage());
		    } catch (ClassNotFoundException e) {
		        System.out.println("Error: clase Producto no encontrada.");
		    }
		}

	}
	private static void modificacion()  {
		
		    File original = new File("productos.dat");

		    if (!original.exists()) {
		        System.out.println("No existe el fichero de productos.");
		        return;
		    }

		    System.out.println("Introduce el nombre del producto a modificar:");
		    String nombreBuscado = Util.introducirCadena();

		    ArrayList<Producto> lista = new ArrayList<>();
		    boolean encontrado = false;

		    // 1. Leer todos los productos al ArrayList
		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(original))) {

		        while (true) {
		            try {
		                Producto p = (Producto) ois.readObject();
		                lista.add(p);
		            } catch (EOFException e) {
		                break; // fin del fichero
		            }
		        }

		    } catch (IOException | ClassNotFoundException e) {
		        System.out.println("Error al leer el fichero: " + e.getMessage());
		        return;
		    }

		    // 2. Buscar y modificar
		    for (Producto p : lista) {
		        if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
		            encontrado = true;

		            System.out.println("Producto encontrado:");
		            System.out.println("Nombre: " + p.getNombre());
		            System.out.println("Stock actual: " + p.getStock());

		            System.out.println("Introduce el nuevo stock:");
		            int nuevoStock = Util.leerInt();

		            p.setStock(nuevoStock);
		            break;
		        }
		    }

		    if (!encontrado) {
		        System.out.println("No existe ningún producto con ese nombre.");
		        return;
		    }

		    // 3. Crear fichero auxiliar
		    File auxiliar = new File("auxiliar.dat");

		    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(auxiliar))) {
		        for (Producto p : lista) {
		            oos.writeObject(p);
		        }
		    } catch (IOException e) {
		        System.out.println("Error al escribir el fichero auxiliar: " + e.getMessage());
		        return;
		    }

		    // 4. Renombrar original como backup
		    File backup = new File("backup.dat");

		    // Si ya existe un backup viejo, lo borramos
		    if (backup.exists()) {
		        backup.delete();
		    }

		    if (!original.renameTo(backup)) {
		        System.out.println("No se pudo renombrar el fichero original a backup.");
		        return;
		    }

		    // 5. Renombrar auxiliar como original
		    if (!auxiliar.renameTo(original)) {
		        System.out.println("No se pudo renombrar el fichero auxiliar a productos.dat.");
		        return;
		    }

		    // 6. Borrar backup
		    if (!backup.delete()) {
		        System.out.println("Advertencia: no se pudo borrar el backup.");
		    }

		    System.out.println("Producto modificado correctamente.");
		}


	private static void consulta()  {
		
		    File refFichero = new File("productos.dat");

		    if (!refFichero.exists()) {
		        System.out.println("No existe el fichero de productos.");
		        return;
		    }

		    System.out.println("Introduce el nombre del producto a consultar:");
		    String nombreBuscado = Util.introducirCadena();

		    boolean encontrado = false;

		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(refFichero))) {

		        while (true) {
		            try {
		                Producto p = (Producto) ois.readObject();

		                if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
		                    System.out.println("\nProducto encontrado:");
		                    System.out.println("Nombre: " + p.getNombre());
		                    System.out.println("Stock: " + p.getStock());
		                    encontrado = true;
		                    break; // dejamos de leer
		                }

		            } catch (EOFException e) {
		                break; // fin del fichero
		            }
		        }

		        if (!encontrado) {
		            System.out.println("No existe ningún producto con ese nombre.");
		        }

		    } catch (IOException e) {
		        System.out.println("Error de lectura: " + e.getMessage());
		    } catch (ClassNotFoundException e) {
		        System.out.println("Error: clase Producto no encontrada.");
		    }
		}
	
	}
	
	private static void borrado()  {
		
		    File original = new File("productos.dat");

		    if (!original.exists()) {
		        System.out.println("No existe el fichero de productos.");
		        return;
		    }

		    System.out.println("Introduce el nombre del producto a borrar:");
		    String nombreBuscado = Util.introducirCadena();

		    ArrayList<Producto> lista = new ArrayList<>();
		    boolean encontrado = false;

		    // 1. Leer todos los productos al ArrayList
		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(original))) {

		        while (true) {
		            try {
		                Producto p = (Producto) ois.readObject();
		                lista.add(p);
		            } catch (EOFException e) {
		                break; // fin del fichero
		            }
		        }

		    } catch (IOException | ClassNotFoundException e) {
		        System.out.println("Error al leer el fichero: " + e.getMessage());
		        return;
		    }

		    // 2. Buscar y eliminar
		    for (int i = 0; i < lista.size(); i++) {
		        if (lista.get(i).getNombre().equalsIgnoreCase(nombreBuscado)) {
		            lista.remove(i);
		            encontrado = true;
		            break;
		        }
		    }

		    if (!encontrado) {
		        System.out.println("No existe ningún producto con ese nombre.");
		        return;
		    }

		    // 3. Crear fichero auxiliar
		    File auxiliar = new File("auxiliar.dat");

		    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(auxiliar))) {
		        for (Producto p : lista) {
		            oos.writeObject(p);
		        }
		    } catch (IOException e) {
		        System.out.println("Error al escribir el fichero auxiliar: " + e.getMessage());
		        return;
		    }

		    // 4. Renombrar original como backup
		    File backup = new File("backup.dat");

		    if (backup.exists()) {
		        backup.delete();
		    }

		    if (!original.renameTo(backup)) {
		        System.out.println("No se pudo renombrar el fichero original a backup.");
		        return;
		    }

		    // 5. Renombrar auxiliar como original
		    if (!auxiliar.renameTo(original)) {
		        System.out.println("No se pudo renombrar el fichero auxiliar a productos.dat.");
		        return;
		    }

		    // 6. Borrar backup
		    if (!backup.delete()) {
		        System.out.println("Advertencia: no se pudo borrar el backup.");
		    }

		    System.out.println("Producto borrado correctamente.");
		}

	}
	private static void ordenarPorNombre() {
	
		    File original = new File("productos.dat");

		    if (!original.exists()) {
		        System.out.println("No existe el fichero de productos.");
		        return;
		    }

		    ArrayList<Producto> lista = new ArrayList<>();

		    // 1. Leer todos los productos al ArrayList
		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(original))) {

		        while (true) {
		            try {
		                Producto p = (Producto) ois.readObject();
		                lista.add(p);
		            } catch (EOFException e) {
		                break; // fin del fichero
		            }
		        }

		    } catch (IOException | ClassNotFoundException e) {
		        System.out.println("Error al leer el fichero: " + e.getMessage());
		        return;
		    }

		    // 2. Ordenar por nombre
		    lista.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

		    // 3. Crear fichero auxiliar
		    File auxiliar = new File("auxiliar.dat");

		    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(auxiliar))) {
		        for (Producto p : lista) {
		            oos.writeObject(p);
		        }
		    } catch (IOException e) {
		        System.out.println("Error al escribir el fichero auxiliar: " + e.getMessage());
		        return;
		    }

		    // 4. Renombrar original como backup
		    File backup = new File("backup.dat");

		    if (backup.exists()) {
		        backup.delete();
		    }

		    if (!original.renameTo(backup)) {
		        System.out.println("No se pudo renombrar el fichero original a backup.");
		        return;
		    }

		    // 5. Renombrar auxiliar como original
		    if (!auxiliar.renameTo(original)) {
		        System.out.println("No se pudo renombrar el fichero auxiliar a productos.dat.");
		        return;
		    }

		    // 6. Borrar backup
		    if (!backup.delete()) {
		        System.out.println("Advertencia: no se pudo borrar el backup.");
		    }

		    System.out.println("Listado ordenado correctamente.");
		}

	}
}