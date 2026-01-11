package clases;

import java.util.*;

public class Universidad {

    private HashMap<String, Profesor> profesores;

    public Universidad() {
        profesores = new HashMap<>();
    }

    public static boolean validarEmail(String email) {
        if (email == null) return false;
        String regex = "^[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-zA-Z]+$";
        return email.matches(regex);
    }

    // 1. Añadir profesor
    public boolean añadirProfesor(String email, String nombre, String departamento) {
        if (!validarEmail(email)) return false;
        if (profesores.containsKey(email)) return false;

        profesores.put(email, new Profesor(email, nombre, departamento));
        return true;
    }

    public Profesor buscarProfesor(String email) {
        return profesores.get(email);
    }

    // 3. Libros premiados de un año
    public void mostrarLibrosPremiados(int año) {
        boolean encontrado = false;

        for (Profesor p : profesores.values()) {
            for (Publicacion pub : p.getPublicaciones()) {
                if (pub instanceof Libro libro) {
                    if (libro.isPremiado() && pub.getFecha().contains(String.valueOf(año))) {
                        encontrado = true;
                        System.out.println(
                            "Fecha: " + pub.getFecha() +
                            " | Título: " + pub.getTitulo() +
                            " | ISBN: " + libro.getIsbn() +
                            " | Profesor: " + p.getNombre() +
                            " | Departamento: " + p.getDepartamento()
                        );
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("No hay libros premiados en ese año.");
        }
    }

    // 4. Listado por departamento
    public void listadoPorDepartamento() {
        HashMap<String, ArrayList<Profesor>> mapa = new HashMap<>();

        for (Profesor p : profesores.values()) {
            if (p.getPublicaciones().isEmpty()) continue;

            mapa.putIfAbsent(p.getDepartamento(), new ArrayList<>());
            mapa.get(p.getDepartamento()).add(p);
        }

        if (mapa.isEmpty()) {
            System.out.println("No existen publicaciones registradas.");
            return;
        }

        for (String dep : mapa.keySet()) {
            System.out.println("\nDepartamento: " + dep);

            ArrayList<Profesor> lista = mapa.get(dep);

            lista.sort((a, b) -> {
                int cmp = Integer.compare(b.getPublicaciones().size(), a.getPublicaciones().size());
                if (cmp == 0) return a.getNombre().compareToIgnoreCase(b.getNombre());
                return cmp;
            });

            for (Profesor p : lista) {
                System.out.println(" - " + p.getNombre() + " (" + p.getPublicaciones().size() + ")");
            }
        }
    }

    // 5. Listado de profesores
    public void listarProfesores() {
        for (Profesor p : profesores.values()) {
            System.out.println(p);
        }
    }
}
