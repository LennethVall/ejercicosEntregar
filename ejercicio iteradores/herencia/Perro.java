package herencia;

public class Perro extends Animal {
    private String raza;

    public Perro(String nombre, int edad, String raza) {
        super(nombre, edad);
        this.raza = raza;
    }

    @Override
    public String sonido() { return "Guau"; }

    @Override
    public String toString() {
        return super.toString() + ", Raza: " + raza;
    }

}
