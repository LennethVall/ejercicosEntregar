package clases;

import java.util.HashMap;
import java.util.Map;

public class Alumno extends Persona {

    private String ciclo;
    private boolean repetidor;
    // clave: código del módulo
    private Map<String, Modulo> modulos;

    public Alumno(String dni, String nombre, String apellido,
                  String ciclo, boolean repetidor) throws DniInvalidoException {
        super(dni, nombre, apellido);
        this.ciclo = ciclo;
        this.repetidor = repetidor;
        this.modulos = new HashMap<>();
    }

    public String getCiclo() {
        return ciclo;
    }

    public boolean isRepetidor() {
        return repetidor;
    }

    public Map<String, Modulo> getModulos() {
        return modulos;
    }

    public boolean tieneModuloConNombre(String nombreModulo) {
        for (Modulo m : modulos.values()) {
            if (m.getNombre().equalsIgnoreCase(nombreModulo)) {
                return true;
            }
        }
        return false;
    }

    public Modulo buscarModuloPorNombre(String nombreModulo) {
        for (Modulo m : modulos.values()) {
            if (m.getNombre().equalsIgnoreCase(nombreModulo)) {
                return m;
            }
        }
        return null;
    }

    public void anadirModulo(Modulo modulo) {
        modulos.put(modulo.getCodigo(), modulo);
    }

    public void eliminarModulo(Modulo modulo) {
        modulos.remove(modulo.getCodigo());
    }

    @Override
    public void visualizar() {
        System.out.println("ALUMNO: " + dni + " - " + getNombreCompleto());
        System.out.println("  Ciclo: " + ciclo + " | Repetidor: " + (repetidor ? "Sí" : "No"));
        if (modulos.isEmpty()) {
            System.out.println("  No tiene módulos matriculados.");
        } else {
            System.out.println("  Módulos:");
            for (Modulo m : modulos.values()) {
                System.out.println("    " + m.getCodigo() + " - " + m.getNombre() +
                                   " | Nota: " + m.getNota());
            }
        }
    }
}

