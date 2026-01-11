package clases;

import java.util.HashMap;

public class SistemaAccesos {
    private HashMap<String, Tarjeta> tarjetas = new HashMap<>();

    // 🔐 Registrar o actualizar tarjeta
    public void registrarTarjeta(String codigo, String nombre, String depto, int nivel) {
        Tarjeta t = new Tarjeta(codigo, nombre, depto, nivel);
        tarjetas.put(codigo, t); // put actualiza si ya existe
        System.out.println("Tarjeta registrada/actualizada: " + codigo);
    }

    // 👁️‍🗨️ Verificar acceso
    public void verificarAcceso(String codigo, int nivelZona) {
        Tarjeta t = tarjetas.get(codigo);
        if (t == null) {
            System.out.println("Acceso denegado: tarjeta no registrada");
        } else if (t.getNivelAcceso() >= nivelZona) {
            System.out.println("Acceso permitido a " + codigo);
        } else {
            System.out.println("Acceso denegado: nivel insuficiente");
        }
    }

    // 🧹 Revocar tarjeta
    public void revocarTarjeta(String codigo) {
        tarjetas.remove(codigo);
        System.out.println("Tarjeta eliminada: " + codigo);
    }

    // 📋 Mostrar todas las tarjetas
    public void mostrarTarjetas() {
        for (Tarjeta t : tarjetas.values()) {
            System.out.println(t);
        }
    }
}