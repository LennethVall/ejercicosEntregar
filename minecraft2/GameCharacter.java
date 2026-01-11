package minecraft2clases;

//Clase base para todos los personajes
public class GameCharacter {
 protected String name;
 protected int health;

 public GameCharacter(String name, int health) {
     if (health < 0) {
         throw new IllegalArgumentException("La vida no puede ser negativa");
     }
     this.name = name;
     this.health = health;
 }

 public String getName() {
     return name;
 }
}
