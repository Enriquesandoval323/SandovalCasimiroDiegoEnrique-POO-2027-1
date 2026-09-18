import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class CalculadorAntiguedad {

    // Formateador estático para validar y parsear cadenas al formato estándar de fecha dd/MM/yyyy
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        // Inicialización de recursos de entrada y de la clase de servicio
        Scanner scanner = new Scanner(System.in);
        CalculadorAntiguedad servicio = new CalculadorAntiguedad();

        // Solicita al usuario una fecha pasada válida evitando ingresos futuros o mal formateados
        LocalDate fechaIngreso = servicio.solicitarFechaPast(scanner, "fecha de ingreso (DD/MM/AAAA)");

        // Calcula la diferencia relativa de tiempo respecto a la fecha actual
        Period antiguedad = servicio.calcularTiempoTranscurrido(fechaIngreso);

        // Muestra en consola el resultado desglosado en años, meses y días
        System.out.printf("Antigüedad acumulada: %d años, %d meses y %d días.%n",
                antiguedad.getYears(), antiguedad.getMonths(), antiguedad.getDays());
    }

    /**
     * Solicita una fecha por consola y la valida de forma iterativa.
     * Evita que el usuario rompa el flujo mediante un bloque try-catch para cadenas inválidas.
     */
    public LocalDate solicitarFechaPast(Scanner scanner, String campo) {

        while (true) {
            System.out.print("Ingrese " + campo + ": ");
            String linea = scanner.nextLine().trim();

            try {
                // Intenta convertir la cadena ingresada al objeto LocalDate usando el patrón definido
                LocalDate fecha = LocalDate.parse(linea, FORMATO);

                // Obtiene la fecha actual ajustada a la zona horaria del sistema
                LocalDate hoy = LocalDate.now(ZoneId.systemDefault());

                // Regla de negocio: La fecha ingresada no puede ser del futuro
                if (fecha.isAfter(hoy)) {
                    System.out.println("-> Error: La fecha no puede ser posterior al día de hoy.");
                    continue; // Pide el dato nuevamente
                }

                return fecha; // Retorna la fecha válida

            } catch (DateTimeParseException e) {
                // Captura el error de parseo si el texto no coincide con el formato dd/MM/yyyy
                System.out.println("-> Error: Formato no válido. Use el formato DD/MM/AAAA.");
            }
        }
    }

    /**
     * Calcula la diferencia de tiempo entre la fecha recibida y la fecha actual del sistema.
     */
    public Period calcularTiempoTranscurrido(LocalDate fechaInicio) {

        // Programación defensiva: asegura que el parámetro no sea nulo antes de operar
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no debe ser nula.");

        // Retorna el periodo transcurrido entre la fecha dada y hoy
        return Period.between(fechaInicio, LocalDate.now(ZoneId.systemDefault()));
    }
}
