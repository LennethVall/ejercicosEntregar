package principal;

import vehiculos.Bicicleta;
import vehiculos.Coche;
import vehiculos.Patinete;

public class Principal {
	public static void main(String[] args) {
		
		
		Bicicleta bici = new Bicicleta("BH", "Atom", 2.8, "V-Brake");
		Coche coche = new Coche("SEAT", "León", 12.0, 5, false);
		Patinete patinete = new Patinete("Segway", "Ninebot", 7.0, 35);
	        
	        System.out.println(bici.descripcion());
	        System.out.println(coche.descripcion());
	        System.out.println(patinete.descripcion());
	        
	        int horas = 5;
	        System.out.println("\nPrecio alquiler bici (" + horas + "h): " + bici.calcularPrecioAlquiler(horas));
	        System.out.println("Precio alquiler coche (" + horas + "h): " + coche.calcularPrecioAlquiler(horas));
	        System.out.println("Precio alquiler patinete (" + horas + "h): " + patinete.calcularPrecioAlquiler(horas));
	    }
	}