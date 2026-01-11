package clases;

public abstract class Persona {
    protected String dni;
    protected String nombre;

    public Persona(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }

    // Método abstracto: cada subclase lo implementa
    public abstract void visualizar();
}
