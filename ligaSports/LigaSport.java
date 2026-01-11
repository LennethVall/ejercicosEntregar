package ligaSports;


	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Iterator;

	public class LigaSport {

	    private ArrayList<String> competidores;
	    private HashMap<String, Integer> partidasGanadas;

	    public LigaSport() {
	        competidores = new ArrayList<>();
	        partidasGanadas = new HashMap<>();
	    }

	    // -------------------------------
	    // CRUD COMPETIDORES (ArrayList)
	    // -------------------------------

	    public void añadirCompetidor(String nombre) {
	        if (!competidores.contains(nombre)) {
	            competidores.add(nombre);
	            System.out.println("Competidor añadido: " + nombre);
	        } else {
	            System.out.println("El competidor ya existe.");
	        }
	    }

	    public void listarCompetidores() {
	        System.out.println("Lista de competidores:");
	        for (String c : competidores) {
	            System.out.println("- " + c);
	        }
	    }

	    public void actualizarCompetidor(String antiguo, String nuevo) {
	        int index = competidores.indexOf(antiguo);
	        if (index != -1) {
	            competidores.set(index, nuevo);
	            System.out.println("Competidor actualizado: " + antiguo + " → " + nuevo);
	        } else {
	            System.out.println("El competidor no existe.");
	        }
	    }

	    public void eliminarCompetidor(String nombre) {
	        if (competidores.remove(nombre)) {
	            System.out.println("Competidor eliminado: " + nombre);
	        } else {
	            System.out.println("No se encontró el competidor.");
	        }
	    }

	    public void eliminarCompetidores(ArrayList<String> nombres) {
	        for (String n : nombres) {
	            competidores.remove(n); // sin streams, solo operaciones de ArrayList
	        }
	        System.out.println("Competidores eliminados: " + nombres);
	    }

	    // -------------------------------
	    // CRUD PARTIDAS GANADAS (HashMap)
	    // -------------------------------

	    public void registrarPartidas(String competidor, int cantidad) {
	        partidasGanadas.put(competidor, partidasGanadas.getOrDefault(competidor, 0) + cantidad);
	        System.out.println("Partidas registradas para " + competidor);
	    }

	    public void listarPartidas() {
	        System.out.println("Partidas ganadas:");
	        for (String c : partidasGanadas.keySet()) {
	            System.out.println(c + ": " + partidasGanadas.get(c));
	        }
	    }

	    public void actualizarPartidas(String competidor, int nuevasPartidas) {
	        if (partidasGanadas.containsKey(competidor)) {
	            partidasGanadas.put(competidor, nuevasPartidas);
	            System.out.println("Partidas actualizadas para " + competidor);
	        } else {
	            System.out.println("No existe registro para ese competidor.");
	        }
	    }

	    public void eliminarRegistro(String competidor) {
	        if (partidasGanadas.remove(competidor) != null) {
	            System.out.println("Registro eliminado: " + competidor);
	        } else {
	            System.out.println("No existe registro para ese competidor.");
	        }
	    }

	    // -------------------------------
	    // MÉTODO ESPECIAL CON ITERATORS
	    // -------------------------------

	    public boolean compararRegistros() {
	        Iterator<String> itCompetidores = competidores.iterator();
	        Iterator<String> itHashMap = partidasGanadas.keySet().iterator(); // requerido aunque no se use mucho

	        while (itCompetidores.hasNext()) {
	            String jugador = itCompetidores.next();
	            if (!partidasGanadas.containsKey(jugador)) {
	                return false;
	            }
	        }
	        return true;
	    }
	}

