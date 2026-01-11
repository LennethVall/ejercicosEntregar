package herencia;

abstract class VehiculoCompartido {
    private static int contador = 1;
    protected int idVehiculo;
    protected String marca;
    protected String modelo;
    protected double precioPorHora;

    public VehiculoCompartido(String marca, String modelo, double precioPorHora) {
        this.idVehiculo = contador++;
        this.marca = marca;
        this.modelo = modelo;
        this.precioPorHora = precioPorHora;
    }

    public double calcularPrecioAlquiler(int horas) {
        return precioPorHora * horas;
    }

    public abstract String descripcion();
}

class Bicicleta extends VehiculoCompartido {
    private String tipoFreno;

    public Bicicleta(String marca, String modelo, double precio, String tipoFreno) {
        super(marca, modelo, precio);
        this.tipoFreno = tipoFreno;
    }

    @Override
    public String descripcion() {
        return "Bicicleta " + marca + " " + modelo + " con freno: " + tipoFreno;
    }
}

class CocheCompartido extends VehiculoCompartido {
    private int numPuertas;
    private boolean esElectrico;

    public CocheCompartido(String marca, String modelo, double precio, int numPuertas, boolean esElectrico) {
        super(marca, modelo, precio);
        this.numPuertas = numPuertas;
        this.esElectrico = esElectrico;
    }

    @Override
    public double calcularPrecioAlquiler(int horas) {
        double precio = super.calcularPrecioAlquiler(horas);
        if (esElectrico) precio *= 0.9; // descuento 10%
        return precio;
    }

    @Override
    public String descripcion() {
        return "Coche " + marca + " " + modelo + " (" + numPuertas + " puertas, eléctrico: " + esElectrico + ")";
    }
}

class Patinete extends VehiculoCompartido {
    private int autonomiaBateria;

    public Patinete(String marca, String modelo, double precio, int autonomia) {
        super(marca, modelo, precio);
        this.autonomiaBateria = autonomia;
    }

    @Override
    public String descripcion() {
        return "Patinete " + marca + " " + modelo + " con autonomía: " + autonomiaBateria + " km";
    }
}

