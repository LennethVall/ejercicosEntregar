package fechas;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class EjercicioFechas {
    public static void main(String[] args) {

        // 1️⃣ LocalDate → solo fecha
        LocalDate fechaNacimiento = LocalDate.of(1989, Month.NOVEMBER, 11);
        System.out.println("Fecha nacimiento: " + fechaNacimiento);
        System.out.println("Año: " + fechaNacimiento.getYear());
        System.out.println("Mes: " + fechaNacimiento.getMonth());
        System.out.println("Día: " + fechaNacimiento.getDayOfMonth());

        // Ejemplo con TextStyle (mes en español)
        System.out.println("Mes en español (FULL): " +
        	    fechaNacimiento.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es-ES")));
        	System.out.println("Mes en español (SHORT): " +
        	    fechaNacimiento.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("es-ES")));
    

        // 2️⃣ LocalTime → solo hora
        LocalTime horaExacta = LocalTime.of(5, 30, 45, 35);
        System.out.println("Hora exacta: " + horaExacta);
        System.out.println("Hora: " + horaExacta.getHour());
        System.out.println("Minuto: " + horaExacta.getMinute());
        System.out.println("Segundo: " + horaExacta.getSecond());
        System.out.println("Nanosegundo: " + horaExacta.getNano());

        // 3️⃣ LocalDateTime → combinación fecha + hora
        LocalDateTime fechaHora = LocalDateTime.of(fechaNacimiento, horaExacta);
        System.out.println("Fecha y hora combinadas: " + fechaHora);

        // 4️⃣ Instant → tiempo en formato máquina
        Instant instante = Instant.ofEpochSecond(120);
        System.out.println("Instant desde epoch: " + instante);
        System.out.println("Instant actual: " + Instant.now());

        // 5️⃣ Duration → diferencia entre tiempos
        LocalTime inicio = LocalTime.of(12, 25);
        LocalTime fin = LocalTime.of(17, 35);
        Duration duracion = Duration.between(inicio, fin);
        System.out.println("Duración entre horas: " + duracion);

        // Ejemplo con ChronoUnit
        Duration unDia = Duration.of(1, ChronoUnit.DAYS);
        Duration dosHoras = Duration.of(2, ChronoUnit.HOURS);
        System.out.println("Duración de un día: " + unDia);
        System.out.println("Duración de dos horas: " + dosHoras);

        // 6️⃣ Period → diferencia entre fechas
        LocalDate inicioFecha = LocalDate.of(2016, Month.JULY, 18);
        LocalDate finFecha = LocalDate.of(2016, Month.JULY, 20);
        Period periodo = Period.between(inicioFecha, finFecha);
        System.out.println("Periodo entre fechas: " + periodo);

        // 7️⃣ DateTimeFormatter → parsear y formatear
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaParseada = LocalDate.parse("08/01/1990", formatoEntrada);
        System.out.println("Fecha parseada: " + fechaParseada);

        DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.forLanguageTag("es-ES"));
        System.out.println("Fecha formateada: " + fechaParseada.format(formatoSalida));

        // 8️⃣ Métodos plus/minus → sumar/restar tiempo
        LocalDateTime hoy = LocalDateTime.now();
        System.out.println("Hoy: " + hoy);
        LocalDateTime dentroUnAño = hoy.plusYears(1);
        System.out.println("Dentro de un año: " + dentroUnAño);

        // 9️⃣ Métodos de comparación
        System.out.println("¿Hoy es antes de dentro de un año? " + hoy.isBefore(dentroUnAño));
        System.out.println("¿Hoy es después de dentro de un año? " + hoy.isAfter(dentroUnAño));

        // 🔟 Ejemplo con ZonedDateTime (zonas horarias)
        ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
        System.out.println("Fecha y hora con zona: " + zoned);

        // 1️⃣1️⃣ Ejemplo de convención de métodos
        LocalDate fechaConvencion = LocalDate.of(2025, 12, 8);
        System.out.println("Fecha original: " + fechaConvencion);
        System.out.println("Fecha modificada con withDayOfMonth: " + fechaConvencion.withDayOfMonth(15));
        System.out.println("Fecha más 10 días: " + fechaConvencion.plusDays(10));
        System.out.println("Fecha menos 2 meses: " + fechaConvencion.minusMonths(2));
    }
}
