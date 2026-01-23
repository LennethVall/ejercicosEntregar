package clases;

public class Trabajador extends Agente {

    public enum Tipo {
        FIJO, TEMPORAL, PRACTICAS
    }

    private String puesto;
    private Tipo tipo;
    private boolean estado;

    public Trabajador(String codigo, String dni, String nombre, String email,
                      String puesto, Tipo tipo, boolean estado) {

        super(codigo, dni, nombre, email);
        this.puesto = puesto;
        this.tipo = tipo;
        this.estado = estado;
    }

	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return the tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Trabajador [puesto=" + puesto + ", tipo=" + tipo + ", estado=" + estado + ", getPuesto()=" + getPuesto()
				+ ", getTipo()=" + getTipo() + ", isEstado()=" + isEstado() + ", getCodigo()=" + getCodigo()
				+ ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getEmail()=" + getEmail()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
    
}
