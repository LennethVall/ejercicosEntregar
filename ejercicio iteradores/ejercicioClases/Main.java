package ejercicioClases;

public class Main {
    public static void main(String[] args) {
        Coche c1 = new Coche("1234ABC", "Rojo", "Ibiza", "Seat");
        Coche c2 = new Coche("5678XYZ", "Negro", "Golf", "Volkswagen");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println("Número de coches: " + Coche.getNumeroCoches());

        Empleado e1 = new Empleado("Ana", "García", "12345678A");
        Empleado e2 = new Empleado("Luis", "Martínez", "87654321B");

        System.out.println(e1);
        System.out.println(e2);
    }
}
