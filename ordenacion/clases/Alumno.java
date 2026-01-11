package clases;


	import java.time.LocalDate;

	// Implementación de Comparable adaptada para usar los campos separados
	public class Alumno implements Comparable<Alumno> { 
		
		    private String nif;
		    // Campos de nombre separados
		    private String nombrePila;
		    private String apellido1;
		    private String apellido2;
		    
		    private LocalDate fechaNacimiento;
		    private Ciclo ciclo;
		    // Nuevo Enum
		    private Repetidor repetidor;
		    
		    
			public Alumno() {
				super();
			}

			// Constructor actualizado para los nuevos 7 parámetros de datos
			public Alumno(String nif, String nombrePila, String apellido1, String apellido2, LocalDate fechaNacimiento, Ciclo ciclo, Repetidor repetidor) {
				super();
				this.nif = nif;
				this.nombrePila = nombrePila;
				this.apellido1 = apellido1;
				this.apellido2 = apellido2;
				this.fechaNacimiento = fechaNacimiento;
				this.ciclo = ciclo;
				this.repetidor = repetidor;
			}

			// --- Getters y Setters actualizados/nuevos ---

			public String getNif() {
				return nif;
			}

			public void setNif(String nif) {
				this.nif = nif;
			}

			public String getNombrePila() {
				return nombrePila;
			}

			public void setNombrePila(String nombrePila) {
				this.nombrePila = nombrePila;
			}

			public String getApellido1() {
				return apellido1;
			}

			public void setApellido1(String apellido1) {
				this.apellido1 = apellido1;
			}

			public String getApellido2() {
				return apellido2;
			}

			public void setApellido2(String apellido2) {
				this.apellido2 = apellido2;
			}
	        
	        // Getter para nombre completo (conveniente para el toString)
	        public String getNombreCompleto() {
	            return nombrePila + " " + apellido1 + " " + apellido2;
	        }

			public LocalDate getFechaNacimiento() {
				return fechaNacimiento;
			}

			public void setFechaNacimiento(LocalDate fechaNacimiento) {
				this.fechaNacimiento = fechaNacimiento;
			}

			public Ciclo getCiclo() {
				return ciclo;
			}

			public void setCiclo(Ciclo ciclo) {
				this.ciclo = ciclo;
			}

			public Repetidor getRepetidor() {
				return repetidor;
			}

			public void setRepetidor(Repetidor repetidor) {
				this.repetidor = repetidor;
			}
	        
	        // --- compareTo() actualizado ---
		    /**
		     * Ordena Alumnos por Nombre de pila, luego Apellido1, luego Apellido2 (alfabéticamente).
		     */
		    @Override
		    public int compareTo(Alumno otroAlumno) {
		        
		        // 1. Comparar por Nombre de pila
		        int result = this.nombrePila.compareToIgnoreCase(otroAlumno.nombrePila);
		        if (result != 0) {
		            return result;
		        }
		        
		        // 2. Si el nombre coincide, comparar por Primer Apellido
		        result = this.apellido1.compareToIgnoreCase(otroAlumno.apellido1);
		        if (result != 0) {
		            return result;
		        }
		        
		        // 3. Si el Primer Apellido coincide, comparar por Segundo Apellido
		        return this.apellido2.compareToIgnoreCase(otroAlumno.apellido2);
		    }


			@Override
			public String toString() {
				return  "Alumno{" +
		                "nif='" + nif + '\'' +
		                ", nombre='" + getNombreCompleto() + '\'' +
		                ", fechaNacimiento=" + fechaNacimiento +
		                ", ciclo=" + ciclo +
		                ", repetidor=" + repetidor +
		                '}';
			}
		
}
