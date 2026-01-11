package herencia;

public class Vehiculo {
    private String marca;
    private int año;

    public Vehiculo(String marca, int año) {
        this.marca = marca;
        this.año = año;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Año: " + año;
    }
}

