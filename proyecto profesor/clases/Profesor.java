package clases;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String email;
    private String nombre;
    private String departamento;
    private List<Publicacion> publicaciones;

    public Profesor(String email, String nombre, String departamento) {
        this.email = email;
        this.nombre = nombre;
        this.departamento = departamento;
        this.publicaciones = new ArrayList<>();
    }

    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public List<Publicacion> getPublicaciones() { return publicaciones; }

    public void addPublicacion(Publicacion p) {
        publicaciones.add(p);
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ") - " + departamento + 
               " | Publicaciones: " + publicaciones.size();
    }
}
