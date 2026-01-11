package herencia;

public class Animal {
    private String nombre;
    private int edad;

    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String sonido() { return "Sonido genérico"; }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad;
    }
}




