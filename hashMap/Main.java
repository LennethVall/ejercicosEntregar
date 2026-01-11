package main;

import java.util.Scanner;

import clases.SistemaAccesos;

public class Main {
    public static void main(String[] args) {
        SistemaAccesos sa = new SistemaAccesos();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ DE CONTROL DE ACCESOS ===");
            System.out.println("1. Registrar/Actualizar tarjeta");
            System.out.println("2. Verificar acceso");
            System.out.println("3. Revocar tarjeta");
            System.out.println("4. Mostrar todas las tarjetas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Código RFID: ");
                    String codigo = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Departamento: ");
                    String depto = sc.nextLine();
                    System.out.print("Nivel de acceso (1-5): ");
                    int nivel = sc.nextInt();
                    sc.nextLine();
                    sa.registrarTarjeta(codigo, nombre, depto, nivel);
                    break;

                case 2:
                    System.out.print("Código RFID: ");
                    String codigoAcceso = sc.nextLine();
                    System.out.print("Nivel requerido de la zona (1-5): ");
                    int nivelZona = sc.nextInt();
                    sc.nextLine();
                    sa.verificarAcceso(codigoAcceso, nivelZona);
                    break;

                case 3:
                    System.out.print("Código RFID a revocar: ");
                    String codigoRevocar = sc.nextLine();
                    sa.revocarTarjeta(codigoRevocar);
                    break;

                case 4:
                    sa.mostrarTarjetas();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}