package ligaSports;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        LigaSport liga = new LigaSport();

        // Añadir competidores
        liga.añadirCompetidor("Ana");
        liga.añadirCompetidor("Luis");
        liga.añadirCompetidor("Carlos");

        liga.listarCompetidores();

        // Registrar partidas
        liga.registrarPartidas("Ana", 5);
        liga.registrarPartidas("Luis", 3);

        liga.listarPartidas();

        // Comparar registros (debería dar FALSE porque Carlos no está en el HashMap)
        System.out.println("Comparación registros: " + liga.compararRegistros());

        // Añadimos a Carlos al HashMap
        liga.registrarPartidas("Carlos", 2);

        // Ahora debe dar TRUE
        System.out.println("Comparación registros: " + liga.compararRegistros());

        // Eliminar varios competidores
        ArrayList<String> listaEliminar = new ArrayList<>();
        listaEliminar.add("Ana");
        listaEliminar.add("Luis");

        liga.eliminarCompetidores(listaEliminar);
        liga.listarCompetidores();
    }
}
