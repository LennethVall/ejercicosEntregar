package clases;

public class Alumno {

    private String nombre;
    private String dni;
    private double notaMedia;

    public Alumno(String nombre, String dni, double notaMedia)
            throws DniInvalidoException, NotaFueraDeRangoException {

        // Validar DNI
        if (!dni.matches("^[0-9]{8}[A-Za-z]$")) {
            throw new DniInvalidoException("DNI inválido: " + dni);
        }

        // Validar nota
        if (notaMedia < 0 || notaMedia > 10) {
            throw new NotaFueraDeRangoException("La nota debe estar entre 0 y 10");
        }

        this.nombre = nombre;
        this.dni = dni;
        this.notaMedia = notaMedia;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", notaMedia=" + notaMedia +
                '}';
    }
}
