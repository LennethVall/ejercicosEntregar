package clases;

import java.util.ArrayList;

public class ListaArticulo {

    private ArrayList<Articulo> lista;

    public ListaArticulo() {
        lista = new ArrayList<>();
    }

    public void fillData() {
        lista.add(new Refresco("R1", "Cola", "CocaCola", 1.5, 20, "Cola", false, true, 35));
        lista.add(new Refresco("R2", "Naranja", "Fanta", 1.4, 80, "Naranja", true, true, 18));
        lista.add(new Vino("V1", "Crianza", "Marqués", 12.5, 10, "Tinto", "Rioja", 2018, "Tempranillo"));
        lista.add(new Vino("V2", "Blanco", "Albariño", 9.0, 60, "Blanco", "Galicia", 2020, "Albariño"));
    }

    public ArrayList<Articulo> reponer() {
        ArrayList<Articulo> r = new ArrayList<>();
        for (Articulo a : lista) {
            if (a.getStock() < 50) r.add(a);
        }
        return r;
    }

    public ArrayList<Articulo> mascaro() {
        ArrayList<Articulo> r = new ArrayList<>();
        double max = 0;

        for (Articulo a : lista) {
            if (a.getPrecio() > max) max = a.getPrecio();
        }

        for (Articulo a : lista) {
            if (a.getPrecio() == max) r.add(a);
        }

        return r;
    }

    public double precio(String codigo) {
        for (Articulo a : lista) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) return a.getPrecio();
        }
        return -1;
    }

    public ArrayList<Articulo> equivalentes(String codigo) {
        double p = precio(codigo);
        ArrayList<Articulo> r = new ArrayList<>();

        for (Articulo a : lista) {
            if (a.getPrecio() == p && !a.getCodigo().equalsIgnoreCase(codigo)) {
                r.add(a);
            }
        }
        return r;
        
        }
    public ArrayList<Articulo> getLista() {
        return lista;
    }

    }

