package vehiculos;

public class Bicicleta extends Vehiculo{
	private String tipoFreno;

    public Bicicleta(String marca, String modelo, double precioPorHora, String tipoFreno) {
        super(marca, modelo, precioPorHora);
        this.tipoFreno = tipoFreno;
    }

	public Bicicleta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bicicleta(String marca, String modelo, double precioPorHora) {
		super(marca, modelo, precioPorHora);
		// TODO Auto-generated constructor stub
	}

	public String getTipoFreno() {
		return tipoFreno;
	}

	public void setTipoFreno(String tipoFreno) {
		this.tipoFreno = tipoFreno;
	}

	@Override
	public String descripcion() {
	    return "Bicicleta [ID=" + getIdVehiculo() + 
	           ", Marca=" + getMarca() + 
	           ", Modelo=" + getModelo() + 
	           ", Precio/hora=" + getPrecioPorHora() + 
	           ", Tipo de freno=" + tipoFreno + "]";
	}
	public String toString() {
	    return "Bicicleta [ID=" + getIdVehiculo() + ", Marca=" + getMarca() +
	           ", Modelo=" + getModelo() + ", Precio/hora=" + getPrecioPorHora() +
	           ", Tipo de freno=" + tipoFreno + "]";
	}
	 
	
	}
    

