package sistema1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ControlAccesos sistema = new ControlAccesos();

        int opcion;

        do {
            System.out.println("\n--- CONTROL DE ACCESOS RFID ---");
            System.out.println("1. Registrar/Actualizar tarjeta");
            System.out.println("2. Verificar acceso");
            System.out.println("3. Revocar tarjeta");
            System.out.println("4. Mostrar tarjetas");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Código RFID: ");
                    String codigo = sc.nextLine();

                    System.out.print("Nombre empleado: ");
                    String nombre = sc.nextLine();

                    System.out.print("Departamento: ");
                    String dep = sc.nextLine();

                    System.out.print("Nivel acceso (1-5): ");
                    int nivel = sc.nextInt();
                    sc.nextLine();

                    sistema.registrarTarjeta(codigo, nombre, dep, nivel);
                }

                case 2 -> {
                    System.out.print("Código RFID: ");
                    String codigo = sc.nextLine();

                    System.out.print("Nivel requerido: ");
                    int nivel = sc.nextInt();
                    sc.nextLine();

                    sistema.verificarAcceso(codigo, nivel);
                }

                case 3 -> {
                    System.out.print("Código RFID: ");
                    String codigo = sc.nextLine();
                    sistema.revocarTarjeta(codigo);
                }

                case 4 -> sistema.mostrarTarjetas();

                case 5 -> System.out.println("Saliendo...");
            }

        } while (opcion != 5);

        sc.close();
    }
}
