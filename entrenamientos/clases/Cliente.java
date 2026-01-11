package clases;

import java.time.LocalDate;
import java.util.*;

public class Cliente extends Persona {
    private LocalDate fechaAlta;
    private Map<String, Entrenamiento> entrenamientos;
    private static int contadorWOD = 100;

    public Cliente(String dni, String nombre, LocalDate fechaAlta) {
        super(dni, nombre);
        this.fechaAlta = fechaAlta;
        this.entrenamientos = new LinkedHashMap<>();
    }

    public void añadirEntrenamiento(LocalDate fecha, String ejercicio, int repeticiones) {
        String codigo = "WOD-" + contadorWOD++;
        entrenamientos.put(codigo, new Entrenamiento(fecha, ejercicio, repeticiones));
    }

    @Override
    public void visualizar() {
        System.out.println("Cliente: " + nombre + ", DNI: " + dni + ", Alta: " + fechaAlta);
        for (Map.Entry<String, Entrenamiento> entry : entrenamientos.entrySet()) {
            System.out.println("Código: " + entry.getKey() + " → " + entry.getValue());
        }
    }

    public Map<String, Entrenamiento> getEntrenamientos() {
        return entrenamientos;
    }
}

