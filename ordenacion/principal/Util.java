package principal;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {

	public static String introducirCadena(){
		 String cadena="";
		 InputStreamReader entrada =new InputStreamReader(System.in);
		 BufferedReader teclado= new BufferedReader(entrada);
		try {
			cadena=teclado.readLine();
		} catch (IOException e) {
			System.out.println("Error en la entrada de datos");
		}
		 return cadena;
	}

	public static char leerChar(){
		boolean error;
		String letra;
		
		do{
			error=false;
			letra=introducirCadena();
			if(letra.length()!=1){
				System.out.println("Error, introduce solo un carácter: ");
				error=true;
			}
			
		}while(error);
		return letra.toUpperCase().charAt(0); // Convertimos a mayúscula por conveniencia
	}

	public static int leerInt(){
		int num=0;
		boolean error;
		do{
			error=false;
			System.out.print(">");
			try{
				num=Integer.parseInt(introducirCadena());
			}
			catch(NumberFormatException e){
				System.out.println("Error al introducir el número. Introduce un número sin decimales.");
				error=true;
			}
		}while(error);
		return num;
	}
}
