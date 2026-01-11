package ejercicio1;


	abstract class Transporte {
	    protected String id;
	    protected int capacidadMaxima;
	    protected double velocidadMedia;

	    public Transporte(String id, int capacidadMaxima, double velocidadMedia) {
	        this.id = id;
	        this.capacidadMaxima = capacidadMaxima;
	        this.velocidadMedia = velocidadMedia;
	    }

	    public abstract double calcularCosteKm();
	}

	class AutobusElectrico extends Transporte {
	    public AutobusElectrico(String id, int capacidad, double velocidad) {
	        super(id, capacidad, velocidad);
	    }

	    @Override
	    public double calcularCosteKm() {
	        return 0.15; // coste fijo por km
	    }
	}

	class Tranvia extends Transporte {
	    public Tranvia(String id, int capacidad, double velocidad) {
	        super(id, capacidad, velocidad);
	    }

	    @Override
	    public double calcularCosteKm() {
	        return 0.10; // coste más bajo por km
	    }
	}

	class BarcoTuristico extends Transporte {
	    public BarcoTuristico(String id, int capacidad, double velocidad) {
	        super(id, capacidad, velocidad);
	    }

	    @Override
	    public double calcularCosteKm() {
	        return 0.25; // coste más alto por km
	    }
	}


