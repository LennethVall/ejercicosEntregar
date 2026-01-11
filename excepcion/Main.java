package main;

import clases.Alumno;

public class Main {
    public static void main(String[] args) {

        // Alumno correcto
        try {
            Alumno a1 = new Alumno("Ana", "12345678A", 8.5);
            System.out.println("Alumno creado correctamente: " + a1);
        } catch (Exception e) {
            System.out.println("Error creando alumno correcto: " + e.getMessage());
        }

        // Alumno con DNI incorrecto
        try {
            Alumno a2 = new Alumno("Luis", "12A", 7.0);
            System.out.println(a2);
        } catch (Exception e) {
            System.out.println("Error creando alumno con DNI incorrecto: " + e.getMessage());
        }

        // Alumno con nota fuera de rango
        try {
            Alumno a3 = new Alumno("Marta", "87654321B", 15);
            System.out.println(a3);
        } catch (Exception e) {
            System.out.println("Error creando alumno con nota incorrecta: " + e.getMessage());
        }
    }
}
