package ejercicioClases;

public class Empleado {
    private String nombre;
    private String apellido;
    private String dni;
    private int idEmpleado;

    // atributo estático para generar IDs
    private static int contador = 100;

    // Constructor
    public Empleado(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.idEmpleado = contador++;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public int getIdEmpleado() { return idEmpleado; }

    // toString
    @Override
    public String toString() {
        return "Empleado número: " + idEmpleado + ", " + nombre + " " + apellido;
    }
}

