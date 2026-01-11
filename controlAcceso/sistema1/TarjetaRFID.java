package sistema1;

public class TarjetaRFID {
    private String codigo;
    private String nombreEmpleado;
    private String departamento;
    private int nivelAcceso; // 1 a 5

    public TarjetaRFID(String codigo, String nombreEmpleado, String departamento, int nivelAcceso) {
        this.codigo = codigo;
        this.nombreEmpleado = nombreEmpleado;
        this.departamento = departamento;
        this.nivelAcceso = nivelAcceso;
    }

    public String getCodigo() { return codigo; }
    public String getNombreEmpleado() { return nombreEmpleado; }
    public String getDepartamento() { return departamento; }
    public int getNivelAcceso() { return nivelAcceso; }

    public void actualizar(String nombre, String dep, int nivel) {
        this.nombreEmpleado = nombre;
        this.departamento = dep;
        this.nivelAcceso = nivel;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
               " | Empleado: " + nombreEmpleado +
               " | Departamento: " + departamento +
               " | Nivel acceso: " + nivelAcceso;
    }
}
