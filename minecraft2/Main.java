package main;

import java.util.ArrayList;

import minecraft2clases.GameCharacter;
import minecraft2clases.Player;
import minecraft2clases.Role;

//Programa principal
public class Main {
 public static void main(String[] args) {

     // Ejemplo de substring
     String name = "Steve_the_Builder";
     String first = name.substring(0, 5);
     String first2 = name.substring(0, name.indexOf("_"));

     System.out.println("Substring 1: " + first);
     System.out.println("Substring 2: " + first2);

     // Ejemplo de try-catch
     try {
         int n = Integer.parseInt("abc");
     } catch (NumberFormatException e) {
         System.out.println("Error al convertir");
     }

     // Lista de personajes
     ArrayList<GameCharacter> lista = new ArrayList<>();
     lista.add(new Player("Steve", 20, Role.WARRIOR));
     lista.add(new GameCharacter("Zombie", 15));
     lista.add(new Player("Alex", 18, Role.ARCHER));

     // Recorrer la lista
     for (GameCharacter ch : lista) {
         if (ch instanceof Player) {
             Player p = (Player) ch;
             System.out.println(p.getName() + " - " + p.getRole());
         } else {
             System.out.println("Mob enemigo");
         }
     }

     // Ejemplo de captura múltiple
     try {
         String x = null;
         int y = Integer.parseInt(x);
     } catch (NumberFormatException | NullPointerException e) {
         System.out.println("Error en los datos");
     } finally {
         System.out.println("Esto se ejecuta siempre");
     }
 }
}
