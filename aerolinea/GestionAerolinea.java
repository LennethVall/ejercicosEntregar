package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

import clases.Auxiliar;
import clases.CargoAuxiliar;
import clases.OrigenInvalidoException;
import clases.Piloto;
import clases.Trabajador;
import clases.Vuelo;

public class GestionAerolinea {

    private static HashMap<String, Trabajador> trabajadores = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter fmtFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige opción: ");

            try {
                switch (opcion) {
                    case 1 -> altaTrabajador();
                    case 2 -> listadoTrabajadores();
                    case 3 -> anadirVuelosAPiloto();
                    case 4 -> anadirIdiomasAAuxiliar();
                    case 5 -> listadoVuelosPiloto();
                    case 6 -> vuelosEntreFechas();
                    case 7 -> pilotosPorDestino();
                    case 8 -> auxiliaresPorIdioma();
                    case 9 -> trabajadoresPorAntiguedad();
                    case 10 -> pilotosPorHorasVuelo();
                    case 11 -> estadisticaVuelosPiloto();
                    case 12 -> vuelosPorDestinoEnMesYOrigen();
                    case 13 -> pilotoMasAntiguoYAuxiliarMasJoven();
                    case 14 -> pilotosPorResidencia();
                    case 15 -> edadYAniosEmpresaTrabajador();
                    case 16 -> modificarCargoAuxiliar();
                    case 17 -> modificarVueloDePiloto();
                    case 18 -> auxiliaresPorCargo();
                    case 19 -> mediaEdadPilotosPorResidencia();
                    case 20 -> estadisticaEdadPilotos();
                    case 21 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 21);
    }

    private static void mostrarMenu() {
        System.out.println("""
                1. Alta trabajador
                2. Listado trabajadores
                3. Añadir vuelo/s a un piloto
                4. Añadir idioma/s a un auxiliar
                5. Listado vuelos de un piloto (DNI)
                6. Listado vuelos entre dos fechas
                7. Pilotos que han volado a un destino
                8. Auxiliares que manejan un idioma
                9. Trabajadores ordenados por antigüedad
                10. Pilotos ordenados por horas de vuelo
                11. Duración media y vuelo más largo/corto de un piloto
                12. Destinos y nº de vuelos en un mes y origen
                13. Piloto más antiguo y auxiliar más joven
                14. Pilotos con residencia concreta
                15. Edad y años en empresa de trabajador (por nombre y apellidos)
                16. Modificar cargo de un auxiliar
                17. Modificar un vuelo de un piloto
                18. Auxiliares con cargo concreto
                19. Media de edad de pilotos con residencia concreta
                20. Estadística de edad de los pilotos
                21. Salir
                """);
    }

    // ========= UTILIDADES =========

    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String linea = sc.nextLine();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

    private static LocalDate leerFecha(String msg) {
        while (true) {
            try {
                System.out.print(msg + " (dd/MM/yyyy): ");
                String linea = sc.nextLine();
                return LocalDate.parse(linea, fmtFecha);
            } catch (Exception e) {
                System.out.println("Fecha inválida.");
            }
        }
    }

    private static LocalDateTime leerFechaHora(String msg) {
        while (true) {
            try {
                System.out.print(msg + " (dd/MM/yyyy HH:mm): ");
                String linea = sc.nextLine();
                return LocalDateTime.parse(linea, fmtFechaHora);
            } catch (Exception e) {
                System.out.println("Fecha y hora inválidas.");
            }
        }
    }

    private static String leerLinea(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    // ========= OPCIÓN 1: ALTA TRABAJADOR =========

    private static void altaTrabajador() {
        String dni = leerLinea("DNI: ");
        if (trabajadores.containsKey(dni)) {
            System.out.println("Ya existe un trabajador con ese DNI.");
            return;
        }

        String nombre = leerLinea("Nombre: ");
        String apellidos = leerLinea("Apellidos: ");
        LocalDate fechaNac = leerFecha("Fecha de nacimiento");
        LocalDate fechaAlta = leerFecha("Fecha de alta");

        System.out.print("¿Es piloto (P) o auxiliar (A)? ");
        String tipo = sc.nextLine().trim().toUpperCase();

        switch (tipo) {
            case "P" -> altaPiloto(dni, nombre, apellidos, fechaNac, fechaAlta);
            case "A" -> altaAuxiliar(dni, nombre, apellidos, fechaNac, fechaAlta);
            default -> System.out.println("Tipo no válido.");
        }
    }

    private static void altaPiloto(String dni, String nombre, String apellidos,
                                   LocalDate fechaNac, LocalDate fechaAlta) {
        LocalDate fechaLicencia = leerFecha("Fecha de licencia");
        String residencia = leerLinea("Residencia oficial: ");

        Piloto p = new Piloto(dni, nombre, apellidos, fechaNac, fechaAlta, fechaLicencia, residencia);
        trabajadores.put(dni, p);
        System.out.println("Piloto añadido correctamente.");
    }

    private static void altaAuxiliar(String dni, String nombre, String apellidos,
                                     LocalDate fechaNac, LocalDate fechaAlta) {
        while (true) {
            try {
                String cargoStr = leerLinea("Cargo (AUXILIAR/JUNIOR/SENIOR): ").toUpperCase();
                CargoAuxiliar cargo = CargoAuxiliar.valueOf(cargoStr);
                Auxiliar a = new Auxiliar(dni, nombre, apellidos, fechaNac, fechaAlta, cargo);
                trabajadores.put(dni, a);
                System.out.println("Auxiliar añadido correctamente.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Cargo no válido. Intenta de nuevo.");
            }
        }
    }

    // ========= OPCIÓN 2: LISTADO TRABAJADORES =========

    private static void listadoTrabajadores() {
        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores.");
            return;
        }

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                System.out.println(piloto);
            } else if (t instanceof Auxiliar aux) {
                System.out.println(aux);
            } else {
                System.out.println("Trabajador: " + t);
            }
        }
    }

    // ========= OPCIÓN 3: AÑADIR VUELOS A PILOTO =========

    private static void anadirVuelosAPiloto() {
        String dni = leerLinea("DNI del piloto: ");
        Trabajador t = trabajadores.get(dni);
        if (!(t instanceof Piloto piloto)) {
            System.out.println("No existe un piloto con ese DNI.");
            return;
        }

        boolean seguir;
        do {
            try {
                String origen = leerLinea("Origen: ");
                String destino = leerLinea("Destino: ");
                LocalDateTime inicio = leerFechaHora("Fecha inicio");
                LocalDateTime fin = leerFechaHora("Fecha fin");
                String tipoAvion = leerLinea("Tipo de avión: ");

                Vuelo v = new Vuelo(origen, destino, inicio, fin, tipoAvion);
                piloto.addVuelo(v);
                System.out.println("Vuelo añadido: " + v);

            } catch (OrigenInvalidoException e) {
                System.out.println("Error en origen: " + e.getMessage());
            }

            String resp = leerLinea("¿Añadir otro vuelo? (S/N): ").toUpperCase();
            seguir = resp.equals("S");

        } while (seguir);
    }

    // ========= OPCIÓN 4: AÑADIR IDIOMAS A AUXILIAR =========

    private static void anadirIdiomasAAuxiliar() {
        String dni = leerLinea("DNI del auxiliar: ");
        Trabajador t = trabajadores.get(dni);
        if (!(t instanceof Auxiliar aux)) {
            System.out.println("No existe un auxiliar con ese DNI.");
            return;
        }

        boolean seguir;
        do {
            String idioma = leerLinea("Idioma: ");
            aux.addIdioma(idioma);
            String resp = leerLinea("¿Añadir otro idioma? (S/N): ").toUpperCase();
            seguir = resp.equals("S");
        } while (seguir);
    }

    // ========= OPCIÓN 5: LISTADO VUELOS DE UN PILOTO =========

    private static void listadoVuelosPiloto() {
        String dni = leerLinea("DNI del piloto: ");
        Trabajador t = trabajadores.get(dni);

        if (!(t instanceof Piloto piloto)) {
            System.out.println("No existe un piloto con ese DNI.");
            return;
        }

        List<Vuelo> vuelos = piloto.getVuelos();
        if (vuelos.isEmpty()) {
            System.out.println("Este piloto no tiene vuelos registrados.");
            return;
        }

        System.out.println("Vuelos del piloto " + piloto.getNombreCompleto() + ":");
        for (Vuelo v : vuelos) {
            System.out.println(v);
        }
    }

    // ========= OPCIÓN 6: VUELOS ENTRE DOS FECHAS =========

    private static void vuelosEntreFechas() {
        LocalDateTime inicio = leerFechaHora("Fecha inicio mínima");
        LocalDateTime fin = leerFechaHora("Fecha inicio máxima");

        boolean encontrado = false;

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                for (Vuelo v : piloto.getVuelos()) {
                    if (!v.getFechaInicio().isBefore(inicio) &&
                        !v.getFechaInicio().isAfter(fin)) {
                        System.out.println(v + " (Piloto: " + piloto.getNombreCompleto() + ")");
                        encontrado = true;
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("No hay vuelos en ese rango de fechas.");
        }
    }

    // ========= OPCIÓN 7: PILOTOS QUE HAN IDO A UN DESTINO =========

    private static void pilotosPorDestino() {
        String destino = leerLinea("Destino a buscar: ").toUpperCase();
        boolean encontrado = false;
        Set<String> mostrados = new HashSet<>();

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                for (Vuelo v : piloto.getVuelos()) {
                    if (v.getDestino().equalsIgnoreCase(destino)) {
                        if (!mostrados.contains(piloto.getDni())) {
                            System.out.println(piloto);
                            mostrados.add(piloto.getDni());
                        }
                        encontrado = true;
                        break;
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("Ningún piloto ha volado a ese destino.");
        }
    }

    // ========= OPCIÓN 8: AUXILIARES POR IDIOMA =========

    private static void auxiliaresPorIdioma() {
        String idioma = leerLinea("Idioma a buscar: ").toLowerCase();
        boolean encontrado = false;

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Auxiliar aux) {
                if (aux.getIdiomas().contains(idioma)) {
                    System.out.println(aux);
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Ningún auxiliar habla ese idioma.");
        }
    }

    // ========= OPCIÓN 9: TRABAJADORES POR ANTIGÜEDAD =========

    private static void trabajadoresPorAntiguedad() {
        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores.");
            return;
        }

        List<Trabajador> lista = new ArrayList<>(trabajadores.values());
        lista.sort(Comparator.comparing(Trabajador::getFechaAlta));

        System.out.println("Trabajadores ordenados por antigüedad:");
        for (Trabajador t : lista) {
            System.out.println(t.getNombreCompleto() + " - Alta: " + t.getFechaAlta());
        }
    }

    // ========= OPCIÓN 10: PILOTOS POR HORAS DE VUELO =========

    private static void pilotosPorHorasVuelo() {
        List<Piloto> pilotos = new ArrayList<>();
        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                pilotos.add(piloto);
            }
        }

        if (pilotos.isEmpty()) {
            System.out.println("No hay pilotos.");
            return;
        }

        pilotos.sort((p1, p2) -> Long.compare(p2.getHorasTotalesVuelo(), p1.getHorasTotalesVuelo()));

        System.out.println("Pilotos ordenados por horas de vuelo (descendente):");
        for (Piloto p : pilotos) {
            System.out.println(p);
        }
    }

    // ========= OPCIÓN 11: ESTADÍSTICA VUELOS DE UN PILOTO =========

    private static void estadisticaVuelosPiloto() {
        String dni = leerLinea("DNI del piloto: ");
        Trabajador t = trabajadores.get(dni);

        if (!(t instanceof Piloto piloto)) {
            System.out.println("No existe un piloto con ese DNI.");
            return;
        }

        List<Vuelo> vuelos = piloto.getVuelos();
        if (vuelos.isEmpty()) {
            System.out.println("Este piloto no tiene vuelos.");
            return;
        }

        long totalHoras = 0;
        Vuelo masLargo = null;
        Vuelo masCorto = null;
        long maxHoras = Long.MIN_VALUE;
        long minHoras = Long.MAX_VALUE;

        for (Vuelo v : vuelos) {
            long horas = java.time.Duration.between(v.getFechaInicio(), v.getFechaFin()).toHours();
            totalHoras += horas;

            if (horas > maxHoras) {
                maxHoras = horas;
                masLargo = v;
            }
            if (horas < minHoras) {
                minHoras = horas;
                masCorto = v;
            }
        }

        double media = (double) totalHoras / vuelos.size();
        System.out.println("Duración media de los vuelos: " + media + " horas.");
        System.out.println("Vuelo más largo (" + maxHoras + " h): " + masLargo);
        System.out.println("Vuelo más corto (" + minHoras + " h): " + masCorto);
    }

    // ========= OPCIÓN 12: AGRUPAR POR DESTINO EN MES Y ORIGEN =========

    private static void vuelosPorDestinoEnMesYOrigen() {
        String origen = leerLinea("Origen: ").toUpperCase();
        int mes = leerEntero("Mes (1-12): ");
        int anio = leerEntero("Año: ");

        Map<String, List<Vuelo>> mapa = new HashMap<>();

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                for (Vuelo v : piloto.getVuelos()) {
                    LocalDateTime f = v.getFechaInicio();
                    if (v.getOrigen().equalsIgnoreCase(origen) &&
                        f.getMonthValue() == mes &&
                        f.getYear() == anio) {

                        mapa.computeIfAbsent(v.getDestino(), k -> new ArrayList<>()).add(v);
                    }
                }
            }
        }

        if (mapa.isEmpty()) {
            System.out.println("No hay vuelos con ese origen y mes.");
            return;
        }

        for (String destino : mapa.keySet()) {
            List<Vuelo> lista = mapa.get(destino);
            System.out.println("Destino: " + destino + " - Nº vuelos: " + lista.size());
            for (Vuelo v : lista) {
                System.out.println("   Fecha inicio: " + v.getFechaInicio());
            }
        }
    }

    // ========= OPCIÓN 13: PILOTO MÁS ANTIGUO Y AUXILIAR MÁS JOVEN =========

    private static void pilotoMasAntiguoYAuxiliarMasJoven() {
        Piloto pilotoMasAntiguo = null;
        Auxiliar auxiliarMasJoven = null;

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                if (pilotoMasAntiguo == null ||
                    piloto.getFechaAlta().isBefore(pilotoMasAntiguo.getFechaAlta())) {
                    pilotoMasAntiguo = piloto;
                }
            } else if (t instanceof Auxiliar aux) {
                if (auxiliarMasJoven == null ||
                    aux.getFechaNacimiento().isAfter(auxiliarMasJoven.getFechaNacimiento())) {
                    auxiliarMasJoven = aux;
                }
            }
        }

        if (pilotoMasAntiguo != null) {
            int anios = Period.between(pilotoMasAntiguo.getFechaAlta(), LocalDate.now()).getYears();
            System.out.println("Piloto con más antigüedad: " +
                    pilotoMasAntiguo.getNombreCompleto() + " (" + anios + " años en la empresa)");
        } else {
            System.out.println("No hay pilotos.");
        }

        if (auxiliarMasJoven != null) {
            int edad = Period.between(auxiliarMasJoven.getFechaNacimiento(), LocalDate.now()).getYears();
            System.out.println("Auxiliar más joven: " +
                    auxiliarMasJoven.getNombreCompleto() + " (" + edad + " años)");
        } else {
            System.out.println("No hay auxiliares.");
        }
    }

    // ========= OPCIÓN 14: PILOTOS POR RESIDENCIA =========

    private static void pilotosPorResidencia() {
        String residencia = leerLinea("Residencia a buscar: ").toLowerCase();
        boolean encontrado = false;

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                if (piloto.getResidencia().toLowerCase().equals(residencia)) {
                    System.out.println(piloto);
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("No hay pilotos con esa residencia.");
        }
    }

    // ========= OPCIÓN 15: EDAD Y AÑOS EN EMPRESA POR NOMBRE Y APELLIDOS =========

    private static void edadYAniosEmpresaTrabajador() {
        String nombre = leerLinea("Nombre: ").trim().toLowerCase();
        String apellidos = leerLinea("Apellidos: ").trim().toLowerCase();
        boolean encontrado = false;

        for (Trabajador t : trabajadores.values()) {
            if (t.getNombre().trim().toLowerCase().equals(nombre) &&
                t.getApellidos().trim().toLowerCase().equals(apellidos)) {

                int edad = Period.between(t.getFechaNacimiento(), LocalDate.now()).getYears();
                int aniosEmpresa = Period.between(t.getFechaAlta(), LocalDate.now()).getYears();
                System.out.println(t.getNombreCompleto() +
                        " - Edad: " + edad +
                        " - Años en la empresa: " + aniosEmpresa);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se ha encontrado trabajador con ese nombre y apellidos.");
        }
    }

    // ========= OPCIÓN 16: MODIFICAR CARGO DE AUXILIAR =========

    private static void modificarCargoAuxiliar() {
        String dni = leerLinea("DNI del auxiliar: ");
        Trabajador t = trabajadores.get(dni);

        if (!(t instanceof Auxiliar aux)) {
            System.out.println("No existe un auxiliar con ese DNI.");
            return;
        }

        while (true) {
            try {
                String nuevoCargo = leerLinea("Nuevo cargo (AUXILIAR/JUNIOR/SENIOR): ").toUpperCase();
                CargoAuxiliar cargo = CargoAuxiliar.valueOf(nuevoCargo);
                aux.setCargo(cargo);
                System.out.println("Cargo modificado correctamente.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Cargo no válido.");
            }
        }
    }

    // ========= OPCIÓN 17: MODIFICAR UN VUELO DE UN PILOTO =========

    private static void modificarVueloDePiloto() {
        String dni = leerLinea("DNI del piloto: ");
        Trabajador t = trabajadores.get(dni);

        if (!(t instanceof Piloto piloto)) {
            System.out.println("No existe un piloto con ese DNI.");
            return;
        }

        List<Vuelo> vuelos = piloto.getVuelos();
        if (vuelos.isEmpty()) {
            System.out.println("Este piloto no tiene vuelos.");
            return;
        }

        System.out.println("Vuelos actuales:");
        for (Vuelo v : vuelos) {
            System.out.println(v.getIdVuelo() + " -> " + v);
        }

        String id = leerLinea("ID del vuelo a modificar: ");
        Vuelo vueloOriginal = null;
        for (Vuelo v : vuelos) {
            if (v.getIdVuelo().equals(id)) {
                vueloOriginal = v;
                break;
            }
        }

        if (vueloOriginal == null) {
            System.out.println("No se ha encontrado ese vuelo.");
            return;
        }

        try {
            String origen = leerLinea("Nuevo origen (actual: " + vueloOriginal.getOrigen() + "): ");
            String destino = leerLinea("Nuevo destino (actual: " + vueloOriginal.getDestino() + "): ");
            LocalDateTime inicio = leerFechaHora("Nueva fecha inicio");
            LocalDateTime fin = leerFechaHora("Nueva fecha fin");
            String tipoAvion = leerLinea("Nuevo tipo de avión (actual: " + vueloOriginal.getTipoAvion() + "): ");

            Vuelo nuevoVuelo = new Vuelo(origen, destino, inicio, fin, tipoAvion);
            int idx = vuelos.indexOf(vueloOriginal);
            vuelos.set(idx, nuevoVuelo);

            System.out.println("Vuelo modificado correctamente: " + nuevoVuelo);

        } catch (OrigenInvalidoException e) {
            System.out.println("Error al modificar vuelo: " + e.getMessage());
        }
    }

    // ========= OPCIÓN 18: AUXILIARES POR CARGO =========

    private static void auxiliaresPorCargo() {
        String cargoStr = leerLinea("Cargo a buscar (AUXILIAR/JUNIOR/SENIOR): ").toUpperCase();
        CargoAuxiliar cargo;

        try {
            cargo = CargoAuxiliar.valueOf(cargoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Cargo no válido.");
            return;
        }

        boolean encontrado = false;
        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Auxiliar aux && aux.getCargo() == cargo) {
                System.out.println(aux);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay auxiliares con ese cargo.");
        }
    }

    // ========= OPCIÓN 19: MEDIA DE EDAD DE PILOTOS POR RESIDENCIA =========

    private static void mediaEdadPilotosPorResidencia() {
        String residencia = leerLinea("Residencia: ").trim().toLowerCase();
        int sumaEdades = 0;
        int contador = 0;

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                if (piloto.getResidencia().trim().toLowerCase().equals(residencia)) {
                    int edad = Period.between(piloto.getFechaNacimiento(), LocalDate.now()).getYears();
                    sumaEdades += edad;
                    contador++;
                }
            }
        }

        if (contador == 0) {
            System.out.println("No hay pilotos con esa residencia.");
            return;
        }

        double media = (double) sumaEdades / contador;
        System.out.println("Media de edad de pilotos con residencia " + residencia + ": " + media);
    }

    // ========= OPCIÓN 20: ESTADÍSTICA DE EDAD DE PILOTOS =========

    private static void estadisticaEdadPilotos() {
        Map<Integer, Integer> mapa = new HashMap<>();

        for (Trabajador t : trabajadores.values()) {
            if (t instanceof Piloto piloto) {
                int edad = Period.between(piloto.getFechaNacimiento(), LocalDate.now()).getYears();
                mapa.put(edad, mapa.getOrDefault(edad, 0) + 1);
            }
        }

        if (mapa.isEmpty()) {
            System.out.println("No hay pilotos.");
            return;
        }

        List<Integer> edades = new ArrayList<>(mapa.keySet());
        Collections.sort(edades);

        System.out.println("Estadística de edades de pilotos (edad -> nº pilotos):");
        for (Integer edad : edades) {
            System.out.println(edad + " años -> " + mapa.get(edad) + " pilotos");
        }
    }
}
