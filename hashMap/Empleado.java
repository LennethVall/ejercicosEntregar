package clases;

public class Empleado {
    private String nombre;
    private String departamento;
    private int nivelAcceso;

    public Empleado(String nombre, String departamento, int nivelAcceso) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() { return nivelAcceso; }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Departamento: " + departamento +
               ", Nivel: " + nivelAcceso;
    }
}