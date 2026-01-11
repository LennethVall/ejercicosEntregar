package principal;

import java.time.LocalDate;
import java.util.*;

import clases.Cliente;
import clases.Entrenamiento;

public class Main {
    public static void main(String[] args) {
        // Crear cliente con 3 entrenamientos
        Cliente c1 = new Cliente("12345678A", "Ines", LocalDate.of(2025, 12, 2));
        c1.añadirEntrenamiento(LocalDate.of(2025, 12, 1), "Sentadillas", 20);
        c1.añadirEntrenamiento(LocalDate.of(2025, 12, 1), "Flexiones", 15);
        c1.añadirEntrenamiento(LocalDate.of(2025, 12, 1), "Burpees", 10);

        System.out.println("📌 Cliente y entrenamientos iniciales:");
        c1.visualizar();

        // Modificar fechas con Iterator (añadir 1 día)
        Map<String, Entrenamiento> entrenamientos = c1.getEntrenamientos();
        Iterator<Map.Entry<String, Entrenamiento>> it = entrenamientos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Entrenamiento> entry = it.next();
            Entrenamiento e = entry.getValue();
            e.setFecha(e.getFecha().plusDays(1));
        }

        System.out.println("\n✅ Cliente tras modificar fechas (+1 día):");
        c1.visualizar();
    }
}
