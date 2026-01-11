package sistema1;

import java.util.HashMap;

public class ControlAccesos {

    private HashMap<String, TarjetaRFID> tarjetas;

    public ControlAccesos() {
        tarjetas = new HashMap<>();
    }

    // 1. Registrar o actualizar tarjeta
    public void registrarTarjeta(String codigo, String nombre, String dep, int nivel) {
        if (tarjetas.containsKey(codigo)) {
            tarjetas.get(codigo).actualizar(nombre, dep, nivel);
            System.out.println("Tarjeta actualizada.");
        } else {
            tarjetas.put(codigo, new TarjetaRFID(codigo, nombre, dep, nivel));
            System.out.println("Tarjeta registrada.");
        }
    }

    // 2. Verificar acceso
    public void verificarAcceso(String codigo, int nivelRequerido) {
        TarjetaRFID t = tarjetas.get(codigo);

        if (t == null) {
            System.out.println("Acceso denegado: tarjeta no registrada.");
            return;
        }

        if (t.getNivelAcceso() >= nivelRequerido) {
            System.out.println("Acceso permitido a " + t.getNombreEmpleado());
        } else {
            System.out.println("Acceso denegado: nivel insuficiente.");
        }
    }

    // 3. Revocar tarjeta
    public void revocarTarjeta(String codigo) {
        if (tarjetas.remove(codigo) != null) {
            System.out.println("Tarjeta eliminada.");
        } else {
            System.out.println("No existe esa tarjeta.");
        }
    }

    // 4. Mostrar todas las tarjetas
    public void mostrarTarjetas() {
        if (tarjetas.isEmpty()) {
            System.out.println("No hay tarjetas registradas.");
            return;
        }

        tarjetas.values().forEach(System.out::println);
    }
}
