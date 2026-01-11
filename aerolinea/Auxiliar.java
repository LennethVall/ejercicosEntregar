package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Auxiliar extends Trabajador {

    private CargoAuxiliar cargo;
    private List<String> idiomas;

    public Auxiliar(String dni, String nombre, String apellidos,
                    LocalDate fechaNacimiento, LocalDate fechaAlta,
                    CargoAuxiliar cargo) {
        super(dni, nombre, apellidos, fechaNacimiento, fechaAlta);
        this.cargo = cargo;
        this.idiomas = new ArrayList<>();
    }

    public CargoAuxiliar getCargo() {
        return cargo;
    }

    public void setCargo(CargoAuxiliar cargo) {
        this.cargo = cargo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void addIdioma(String idioma) {
        if (!idiomas.contains(idioma.toLowerCase())) {
            idiomas.add(idioma.toLowerCase());
        }
    }

    @Override
    public String toString() {
        return "Auxiliar: " + dni + " - " + getNombreCompleto() +
               " - Cargo: " + cargo + " - Idiomas: " + idiomas;
    }
}
