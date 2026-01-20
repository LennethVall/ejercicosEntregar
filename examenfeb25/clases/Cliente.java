package clases;

import java.time.LocalDate;
import java.util.HashMap;

public class Cliente extends Persona {

    private LocalDate fechaAlta;
    private HashMap<String, Entrenamiento> entrenamientos;
    private int contadorWOD = 100;   // Cada cliente empieza en 100

    public Cliente(String DNI, String nombre, LocalDate fechaAlta,
                   HashMap<String, Entrenamiento> entrenamientos) {

        super(DNI, nombre);
        this.fechaAlta = fechaAlta;
        this.entrenamientos = entrenamientos;

        // Ajustar contador si ya hay entrenamientos cargados del fichero
        if (!entrenamientos.isEmpty()) {
            this.contadorWOD = calcularSiguienteCodigo();
        }
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public HashMap<String, Entrenamiento> getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(HashMap<String, Entrenamiento> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    /**
     * Añade un nuevo entrenamiento generando automáticamente el código WOD-XXX
     */
    public void aniadirEntrenamiento(String ejercicio, int repeticiones) {

        String codigo = "WOD-" + contadorWOD;
        contadorWOD++;

        Entrenamiento ent = new Entrenamiento(
                codigo,
                LocalDate.now(),
                ejercicio,
                repeticiones
        );

        entrenamientos.put(codigo, ent);
    }

    /**
     * Si el cliente ya tiene entrenamientos cargados desde el fichero,
     * calculamos el siguiente número WOD disponible.
     */
    private int calcularSiguienteCodigo() {
        int max = 100;

        for (String cod : entrenamientos.keySet()) {
            try {
                int num = Integer.parseInt(cod.substring(4)); // "WOD-101" → 101
                if (num >= max) {
                    max = num + 1;
                }
            } catch (Exception e) {
                // Ignorar códigos mal formados (no debería ocurrir)
            }
        }
        return max;
    }

    @Override
    public void visualizar() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Fecha alta: " + fechaAlta +
               ", Entrenamientos: " + entrenamientos.values();
    }
}

