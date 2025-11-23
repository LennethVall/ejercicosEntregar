package vehiculos;

public class Coche extends Vehiculo {
	

	private int numPuertas;
    private boolean esElectrico;
    

public Coche(String marca, String modelo, double precioPorHora, int numPuertas, boolean esElectrico) {
    super(marca, modelo, precioPorHora);
    this.numPuertas = numPuertas;
    this.esElectrico = esElectrico;
}

@Override
public double calcularPrecioAlquiler(int horas) {
    double precio = super.calcularPrecioAlquiler(horas);
    if (esElectrico) {
        precio *= 0.9; 
    }
    return precio;
}

public int getNumPuertas() {
	return numPuertas;
}


public void setNumPuertas(int numPuertas) {
	this.numPuertas = numPuertas;
}


public boolean isEsElectrico() {
	return esElectrico;
}


public void setEsElectrico(boolean esElectrico) {
	this.esElectrico = esElectrico;
}


@Override
public String toString() {
	return  "Coche [ID=" + getIdVehiculo() + ", Marca=" + getMarca() +
            ", Modelo=" + getModelo() + ", Precio/hora=" + getPrecioPorHora() +
            ", Puertas=" + numPuertas + ", Eléctrico=" + esElectrico + "]";
 }
}
