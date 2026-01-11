package herencia;

public class Estudiante extends Persona {
    private String curso;

    public Estudiante(String nombre, int edad, String curso) {
        super(nombre, edad); // llamada al constructor de Persona
        this.curso = curso;
    }

    @Override
    public String toString() {
        return super.toString() + ", Curso: " + curso;
    }
}


