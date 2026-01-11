package clases;

public class Tarjeta {
    private String codigoRFID;
    private Empleado empleado;

    public Tarjeta(String codigoRFID, Empleado empleado) {
        this.codigoRFID = codigoRFID;
        this.empleado = empleado;
    }

    public Tarjeta(String codigo, String nombre, String depto, int nivel) {
		// TODO Auto-generated constructor stub
	}

	public String getCodigoRFID() { return codigoRFID; }
    public Empleado getEmpleado() { return empleado; }

    @Override
    public String toString() {
        return "Código: " + codigoRFID + " → " + empleado.toString();
    }

	public int getNivelAcceso() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}