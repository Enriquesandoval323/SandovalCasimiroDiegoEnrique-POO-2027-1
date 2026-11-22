/*
El proposito de este programa es permitir al usuario utilizar una agenda de reservaciones para un salon de fiestas.
Mediante un menu interactivo se pueden consultar horarios, registrar eventos,
mostrar la agenda diaria y recuperar los datos guardados en un archivo de texto.

Integrantes del equipo:
Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio
Rivera Hernandez Luis Angel
Salvador Casimiro Diego
*/
// IOException permite manejar problemas del archivo sin continuar con datos incompletos.
import java.io.IOException;
// LocalDate y LocalDateTime se usan para ingresar fechas y horarios.
import java.time.LocalDate;
import java.time.LocalDateTime;
// DateTimeException se utiliza en el manejo de fechas incorrectas.
import java.time.DateTimeException;
// Scanner recibe los datos que el usuario escribe en la consola.
import java.util.Scanner;
// NoSuchElementException permite terminar si se agota la entrada de la consola.
import java.util.NoSuchElementException;

// La clase Main contiene el punto de entrada y el menu principal.
public class Main {
    // El metodo main inicia el programa y controla el ciclo de interaccion.
    public static void main(String[] args) {
        // try-with-resources cierra el Scanner al terminar la ejecucion.
        try (Scanner scanner = new Scanner(System.in)) {
            // Se crea la agenda y se recuperan los eventos de reservaciones.txt.
            Agenda agenda = new Agenda("reservaciones.txt");
            // La variable opcion registra la seleccion actual del usuario.
            int opcion = 0;
            // El menu se repite hasta que el usuario seleccione la opcion 5.
            do {
                System.out.println("\nSISTEMA DE RESERVACIONES");
                System.out.println("1. Consultar disponibilidad");
                System.out.println("2. Registrar reservacion");
                System.out.println("3. Mostrar agenda de un dia");
                System.out.println("4. Mostrar todas las reservaciones");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opcion: ");

                // Se intenta leer la opcion como un numero entero.
                try {
                    opcion = Integer.parseInt(scanner.nextLine().trim());
                    // El switch ejecuta una de las funciones disponibles.
                    switch (opcion) {
                        // Opcion 1: consulta si las horas solicitadas estan libres.
                        case 1:
                            LocalDateTime inicio = leerInicio(scanner);
                            int duracion = leerDuracion(scanner);
                            // La duracion se suma al inicio para calcular el fin de la consulta.
                            if (agenda.consultarDisponibilidad(inicio, inicio.plusHours(duracion))) {
                                System.out.println("Horario disponible.");
                            } else {
                                System.out.println("Error: el horario no esta disponible.");
                            }
                            break;
                        // Opcion 2: registra los datos del cliente y el intervalo solicitado.
                        case 2:
                            System.out.print("Nombre de la persona o empresa: ");
                            String nombre = scanner.nextLine();
                            LocalDateTime fechaInicio = leerInicio(scanner);
                            int horas = leerDuracion(scanner);
                            // Se construye un objeto Reservacion con los datos ingresados.
                            Reservacion reservacion = new Reservacion(nombre, fechaInicio, horas);
                            // Si no hay traslape, Agenda lo guarda; si lo hay, informa el error.
                            if (agenda.agregarReservacion(reservacion)) {
                                System.out.println("Reservacion registrada correctamente.");
                                reservacion.mostrarInformacion();
                            } else {
                                System.out.println("Error: el horario no esta disponible.");
                            }
                            break;
                        // Opcion 3: imprime la disponibilidad de las 24 horas de una fecha.
                        case 3:
                            LocalDate fecha = leerFecha(scanner);
                            agenda.mostrarAgenda(fecha);
                            break;
                        // Opcion 4: enumera los eventos guardados.
                        case 4:
                            agenda.mostrarReservaciones();
                            break;
                        // Opcion 5: sale del ciclo y termina el programa.
                        case 5:
                            System.out.println("Saliendo del programa.");
                            break;
                        // Las opciones diferentes de 1 a 5 se consideran invalidas.
                        default:
                            System.out.println("Error: opcion no valida.");
                    }
                // La captura informa errores de fecha, numero o validacion del intervalo.
                } catch (DateTimeException | IllegalArgumentException e) {
                    System.out.println("Error: datos invalidos. " + e.getMessage());
                }
            // El ciclo termina cuando el usuario eligio salir.
            } while (opcion != 5);
        // Un error de lectura o escritura detiene el programa para evitar inconsistencias.
        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
            System.out.println("El programa se cerrara para evitar trabajar con datos incompletos.");
        // La ausencia de una entrada disponible permite cerrar de forma controlada.
        } catch (NoSuchElementException e) {
            System.out.println("Fin de la entrada. Saliendo del programa.");
        }
    }

    // Pide una fecha en formato AAAA-MM-DD y la convierte a LocalDate.
    public static LocalDate leerFecha(Scanner scanner) {
        System.out.print("Fecha (AAAA-MM-DD): ");
        return LocalDate.parse(scanner.nextLine().trim());
    }

    // Solicita una fecha y una hora entera para obtener un LocalDateTime.
    public static LocalDateTime leerInicio(Scanner scanner) {
        LocalDate fecha = leerFecha(scanner);
        System.out.print("Hora de inicio (0 a 23): ");
        // Si la hora no esta entre 0 y 23, LocalDate genera una excepcion.
        int hora = Integer.parseInt(scanner.nextLine().trim());
        return fecha.atTime(hora, 0);
    }

    // Solicita la duracion del evento. Enter asigna 6 horas como valor inicial.
    public static int leerDuracion(Scanner scanner) {
        System.out.print("Duracion en horas (1 a 24, Enter = 6): ");
        String entrada = scanner.nextLine().trim();
        int duracion = 6;
        // Si hay texto, se reemplaza el valor predeterminado con el numero introducido.
        if (!entrada.isEmpty()) {
            duracion = Integer.parseInt(entrada);
        }
        // La duracion se valida antes de devolverla a main.
        if (duracion < 1 || duracion > 24) {
            throw new IllegalArgumentException("La duracion debe ser de 1 a 24 horas.");
        }
        return duracion;
    }
}
