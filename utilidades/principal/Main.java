package principal;

import utilidades.Utilidades;  // Importamos la clase de utilidades

public class Main {
    public static void main(String[] args) {
        
        // 1️⃣ Leer un String con límite de caracteres
        String nombre = Utilidades.leerString(10, "Introduce tu nombre (máx 10 caracteres): ");
        System.out.println("Nombre válido: " + nombre);

        // 2️⃣ Leer un número entero sin rango
        int edad = Utilidades.leerInt("Introduce tu edad: ");
        System.out.println("Edad: " + edad);

        // 3️⃣ Leer un número entero con rango
        int puntos = Utilidades.leerInt(0, 100, "Introduce tus puntos (0-100): ");
        System.out.println("Puntos válidos: " + puntos);

        // 4️⃣ Leer un número decimal (double) sin rango
        double precio = Utilidades.leerDouble("Introduce el precio: ");
        System.out.println("Precio: " + precio);

        // 5️⃣ Leer un número decimal (double) con rango
        double nota = Utilidades.leerDouble(0, 10, "Introduce tu nota (0-10): ");
        System.out.println("Nota válida: " + nota);

        // 6️⃣ Leer un float
        float altura = Utilidades.leerFloat("Introduce tu altura en metros: ");
        System.out.println("Altura: " + altura);

        // 7️⃣ Leer un float con rango
        float peso = Utilidades.leerFloat(30, 200, "Introduce tu peso (30-200 kg): ");
        System.out.println("Peso válido: " + peso);

        // 8️⃣ Leer un booleano (acepta si/no, true/false, 1/0, etc.)
        boolean continuar = Utilidades.esBoolean("¿Quieres continuar? (si/no, true/false, 1/0): ");
        System.out.println("Continuar: " + continuar);

        // 9️⃣ Leer un carácter cualquiera
        char inicial = Utilidades.leerChar("Introduce la inicial de tu apellido: ");
        System.out.println("Inicial: " + inicial);

        // 🔟 Leer un carácter dentro de un conjunto permitido
        char opcion = Utilidades.leerCharArray(new char[]{'A','B','C'}, "Elige opción (A/B/C): ");
        System.out.println("Opción elegida: " + opcion);
    }
}
