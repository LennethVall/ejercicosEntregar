package clases;

public class Trabajador extends Persona {
	
	
	private String usuario;
	private String contrasena;
	
	public Trabajador(String DNI, String nombre, String usuariio, String contrasena) {
	
		super(DNI, nombre);
		this.usuario=usuario;
		this.contrasena=contrasena;
		
		
	
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
public void visualizar() {
	return super.toString() + usuario + contrasena + getUsuario() + getContrasena();
			
}
}
