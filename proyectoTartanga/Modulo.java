package clases;

public class Modulo {

    private static int contador = 1;

    private String codigo;
    private String nombre;
    private int nota; // 0 si no tiene nota

    public Modulo(String nombre) {
        this.nombre = nombre;
        this.codigo = generarCodigo(nombre);
        this.nota = 0;
    }

    private String generarCodigo(String nombreModulo) {
        String limpio = nombreModulo.trim().toUpperCase();
        String nom = limpio.replaceAll("\\s+", "");
        if (nom.length() >= 3) {
            nom = nom.substring(0, 3);
        }
        return nom + "-" + (contador++);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nota=" + nota +
                '}';
    }
}
