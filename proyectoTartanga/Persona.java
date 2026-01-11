package clases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Persona {

    protected String dni;
    protected String nombre;
    protected String apellido;

    public Persona(String dni, String nombre, String apellido) throws DniInvalidoException {
        validarDni(dni);
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    private void validarDni(String dni) throws DniInvalidoException {
        String regex = "^[0-9]{8}[A-HJ-NP-TV-Z]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dni);
        if (!matcher.matches()) {
            throw new DniInvalidoException("DNI inválido: " + dni);
        }
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public abstract void visualizar();
}
