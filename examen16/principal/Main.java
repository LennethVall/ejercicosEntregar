package principal;

import clases.*;
import utilidades.Utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Persona> personas = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Alta de cliente/empleado");
            System.out.println("2. Listado de clientes");
            System.out.println("3. Añadir medición a un cliente y ver evolución");
            System.out.println("4. Estadística de clientes (gordis)");
            System.out.println("99. Salir");

            int opcion = Utilidades.leerInt(1, 99, "Elige opción: ");

            switch (opcion) {
                case 1 -> {
                    System.out.println("¿Qué quieres dar de alta?");
                    System.out.println("1. Cliente");
                    System.out.println("2. Empleado");
                    int tipo = Utilidades.leerInt(1, 2, "Elige tipo: ");

                    String nombre = Utilidades.leerString(30, "Introduce nombre: ");
                    String fechaNacStr = Utilidades.leerString(10, "Introduce fecha nacimiento (dd/MM/yyyy): ");
                    LocalDate fechaNac = LocalDate.parse(fechaNacStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    if (tipo == 1) {
                        double altura = Utilidades.leerDouble("Introduce altura en metros: ");
                        Cliente c = new Cliente(nombre, fechaNac, altura);

                        // Al menos una medición
                        boolean mas = true;
                        while (mas) {
                            String fechaMedStr = Utilidades.leerString(10, "Fecha medición (dd/MM/yyyy): ");
                            LocalDate fechaMed = LocalDate.parse(fechaMedStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            double peso = Utilidades.leerDouble("Peso en Kg: ");
                            c.addMedicion(new Medicion(fechaMed, peso));
                            mas = Utilidades.esBoolean("¿Quieres añadir otra medición? (si/no): ");
                        }
                        personas.add(c);
                        System.out.println("Cliente añadido con código: " + c.getCodigoCliente());
                    } else {
                        String fechaAltaStr = Utilidades.leerString(10, "Introduce fecha de alta (dd/MM/yyyy): ");
                        LocalDate fechaAlta = LocalDate.parse(fechaAltaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Empleado e = new Empleado(nombre, fechaNac, fechaAlta);
                        personas.add(e);
                        System.out.println("Empleado añadido correctamente.");
                    }
                }

                case 2 -> {
                    // Listado de clientes
                    boolean hayClientes = false;
                    for (Persona p : personas) {
                        if (p instanceof Cliente c) {
                            hayClientes = true;
                            System.out.println(c);
                        }
                    }
                    if (!hayClientes) {
                        System.out.println("No hay clientes registrados.");
                    }
                }

                case 3 -> {
                    // Añadir medición y ver evolución
                    String nombre = Utilidades.leerString(30, "Introduce nombre del cliente: ");
                    Cliente encontrado = null;
                    for (Persona p : personas) {
                        if (p instanceof Cliente c && c.getNombre().equalsIgnoreCase(nombre)) {
                            encontrado = c;
                            break;
                        }
                    }
                    if (encontrado == null) {
                        System.out.println("El cliente buscado no ha sido encontrado.");
                    } else {
                        System.out.println("Cliente encontrado: " + encontrado.getNombre());
                        for (Medicion m : encontrado.getMediciones()) {
                            System.out.println(m);
                        }
                        // Nueva medición
                        String fechaMedStr = Utilidades.leerString(10, "Fecha nueva medición (dd/MM/yyyy): ");
                        LocalDate fechaMed = LocalDate.parse(fechaMedStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        double peso = Utilidades.leerDouble("Nuevo peso: ");
                        encontrado.addMedicion(new Medicion(fechaMed, peso));

                        // Estadística
                        double imc = encontrado.calcularIMC();
                        System.out.println("IMC ACTUAL = " + String.format("%.2f", imc));

                        double ultimo = encontrado.getUltimoPeso();
                        double penultimo = encontrado.getMediciones().get(encontrado.getMediciones().size() - 2).getPeso();
                        double diferenciaUltima = ultimo - penultimo;
                        double diferenciaInicio = ultimo - encontrado.getMediciones().get(0).getPeso();

                        System.out.println("Diferencia peso última medición: " + String.format("%.3f", diferenciaUltima) + " Kg.");
                        System.out.println("Diferencia de peso desde el inicio: " + String.format("%.3f", diferenciaInicio) + " Kg.");

                        if (diferenciaUltima >= -0.200) {
                            System.out.println("REVISIÓN DE LA DIETA!!");
                        } else {
                            System.out.println("LA DIETA ASIGNADA ES CORRECTA!!");
                        }
                    }
                }

                case 4 -> {
                    // Estadística de clientes
                    Map<String, Integer> estadistica = new HashMap<>();
                    for (Persona p : personas) {
                        if (p instanceof Cliente c && !c.getMediciones().isEmpty()) {
                            double pesoInicial = c.getMediciones().get(0).getPeso();
                            double imc = pesoInicial / (c.getAltura() * c.getAltura());
                            String clasificacion = clasificarIMC(imc);
                            estadistica.put(clasificacion, estadistica.getOrDefault(clasificacion, 0) + 1);
                        }
                    }
                    if (estadistica.isEmpty()) {
                        System.out.println("No hay clientes para estadística.");
                    } else {
                        estadistica.entrySet().stream()
                                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
                    }
                }

                case 99 -> {
                    salir = true;
                    System.out.println("Programa finalizado.");
                }
            }
        }
    }

    // Método auxiliar para clasificar IMC
    public static String clasificarIMC(double imc) {
        if (imc < 16.00) return "Infrapeso: Delgadez Severa";
        else if (imc < 17.00) return "Infrapeso: Delgadez moderada";
        else if (imc < 18.50) return "Infrapeso: Delgadez aceptable";
        else if (imc < 25.00) return "Peso Normal";
        else if (imc < 30.00) return "Sobrepeso";
        else if (imc < 35.00) return "Obeso: Tipo I";
        else if (imc <= 40.00) return "Obeso: Tipo II";
        else return "Obeso: Tipo III";
    }
}
