package clases;

public class Trabajador extends Persona {

    private String usuario;
    private String contrasena;
    private Cargo cargo;   // MONITOR o RECEPCIONISTA

    public Trabajador(String DNI, String nombre, String usuario, String contrasena, Cargo cargo) {
        super(DNI, nombre);
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public void visualizar() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Usuario: " + usuario +
               ", Contraseña: " + contrasena +
               ", Cargo: " + cargo;
    }
}
