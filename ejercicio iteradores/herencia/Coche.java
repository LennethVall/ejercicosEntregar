package herencia;

public class Coche extends Vehiculo {
    private int puertas;

    public Coche(String marca, int puertas, int año) {
        super(marca, año);
        this.puertas = puertas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Puertas: " + puertas;
    }
}


