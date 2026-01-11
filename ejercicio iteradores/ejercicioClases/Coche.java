package ejercicioClases;

public class Coche {
    private String matricula;
    private String color;
    private String modelo;
    private String marca;

    // atributo estático para contar coches
    private static int numeroCoches = 0;

    // Constructor
    public Coche(String matricula, String color, String modelo, String marca) {
        this.matricula = matricula;
        this.color = color;
        this.modelo = modelo;
        this.marca = marca;
        numeroCoches++; // cada vez que se crea un coche, aumenta el contador
    }

    // Getters
    public String getMatricula() { return matricula; }
    public String getColor() { return color; }
    public String getModelo() { return modelo; }
    public String getMarca() { return marca; }

    // Método estático para acceder al número de coches
    public static int getNumeroCoches() {
        return numeroCoches;
    }

    // toString
    @Override
    public String toString() {
        return "Matrícula: " + matricula + ", Color: " + color +
               ", Modelo: " + modelo + ", Marca: " + marca;
    }
}

