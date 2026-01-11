package minecraft2clases;

//Clase Player que hereda de Character e implementa Attackable
public class Player extends GameCharacter implements Attackable {

 private Role role;

 public Player(String nombre, int vida) {
     super(nombre, vida);
 }

 public Player(String nombre, int vida, Role role) {
     super(nombre, vida);
     this.role = role;
 }

 @Override
 public int attack() {
     return 10; // Daño fijo
 }

 public Role getRole() {
     return role;
 }

 // Método que puede lanzar una excepción
 public void heal(int amount) throws Exception {
     if (amount < 0) {
         throw new Exception("No puedes curar una cantidad negativa");
     }
     this.health += amount;
 }
}
