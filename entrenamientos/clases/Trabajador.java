package clases;

public class Trabajador extends Persona {
    private String usuario;
    private String contraseña;
    private String cargo; // monitor o recepcionista

    public Trabajador(String dni, String nombre, String usuario, String contraseña, String cargo) {
        super(dni, nombre);
        if (!cargo.equalsIgnoreCase("monitor") && !cargo.equalsIgnoreCase("recepcionista")) {
            throw new IllegalArgumentException("Cargo inválido. Debe ser monitor o recepcionista.");
        }
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }

    @Override
    public void visualizar() {
        System.out.println("Trabajador: " + nombre + " (" + cargo + "), DNI: " + dni);
    }
}
