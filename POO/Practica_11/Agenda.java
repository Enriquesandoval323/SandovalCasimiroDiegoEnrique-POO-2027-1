/*
El proposito de este programa es organizar las reservaciones y consultar la disponibilidad de un salon de fiestas.
El programa conserva las reservaciones en una lista, utiliza ArchivoReservaciones
para recuperar y guardar datos, y evita registrar horarios que se traslapen.

Integrantes del equipo:
Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio
Rivera Hernandez Luis Angel
Salvador Casimiro Diego
*/
// IOException permite informar problemas al leer o guardar el archivo.
import java.io.IOException;
// LocalDate y LocalDateTime representan fechas y horas de las reservaciones.
import java.time.LocalDate;
import java.time.LocalDateTime;
// TextStyle y Locale se usan para mostrar el nombre del dia en espanol de Mexico.
import java.time.format.TextStyle;
// ArrayList permite mantener en memoria una lista de reservaciones.
import java.util.ArrayList;
import java.util.Locale;

// La clase Agenda administra el conjunto de eventos y conecta el menu con el archivo.
public class Agenda{
    // Lista de objetos Reservacion que se mantienen cargados durante la ejecucion.
    private ArrayList<Reservacion> reservaciones;
    // Objeto encargado de las operaciones de lectura y escritura.
    private ArchivoReservaciones archivo;

    // Constructor que recibe el nombre del archivo donde se guardaran los eventos.
    public Agenda(String nombreArchivo) throws IOException{
        // Se prepara el acceso al archivo y una lista inicialmente vacia.
        archivo = new ArchivoReservaciones(nombreArchivo);
        reservaciones = new ArrayList<>();
        // Comprueba tambien los registros recuperados del archivo
        ArrayList<Reservacion> guardadas = archivo.cargarReservaciones();
        for (Reservacion reservacion : guardadas) {
            // Si alguna reservacion recuperada se cruza con otra, se detiene la carga
            // para no trabajar con datos inconsistentes.
            if (!consultarDisponibilidad(reservacion.getFechaInicio(), reservacion.getFechaFin())) {
                throw new IOException("El archivo contiene reservaciones que se cruzan.");
            }
            reservaciones.add(reservacion);
        }
    }

    // Comprueba que un intervalo tenga inicio anterior al fin y no se cruce
    // con ninguna reservacion que ya se encuentra en la lista.
    public boolean consultarDisponibilidad(LocalDateTime inicio, LocalDateTime fin) {
        if (!inicio.isBefore(fin)) {
            throw new IllegalArgumentException("El fin debe ser posterior al inicio.");
        }
        // Se revisa cada reservacion mediante su metodo hayConflicto().
        for (Reservacion reservacion : reservaciones) {
            if (reservacion.hayConflicto(inicio, fin)) {
                return false;
            }
        }
        // Si se termino la revision sin conflictos, el horario esta disponible.
        return true;
    }

    // Intenta agregar una nueva reservacion tanto al archivo como a la lista.
    public boolean agregarReservacion(Reservacion reservacion) throws IOException {
        // La primera condicion impide guardar un horario ocupado.
        if (!consultarDisponibilidad(reservacion.getFechaInicio(), reservacion.getFechaFin())) {
            return false;
        }
        // Se escribe primero en el archivo; solo si funciona se agrega en memoria.
        archivo.guardarReservacion(reservacion);
        reservaciones.add(reservacion);
        return true;
    }

    // Busca al cliente que ocupa una casilla de una hora
    // Esta busqueda devuelve el objeto que coincide con la hora consultada.
    public Reservacion buscarReservacion(LocalDateTime hora) {
        for (Reservacion reservacion : reservaciones) {
            if (reservacion.hayConflicto(hora, hora.plusHours(1))) {
                return reservacion;
            }
        }
        // El valor null indica que la casilla consultada no esta ocupada.
        return null;
    }

    // Imprime las 24 horas de una fecha y muestra si estan libres u ocupadas.
    public void mostrarAgenda(LocalDate fecha) {
        // Se obtiene el dia de la semana con su nombre en espanol.
        String dia = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL,
                Locale.forLanguageTag("es-MX"));
        System.out.println("\nAGENDA DEL SALON DE FIESTAS");
        System.out.println("Fecha: " + fecha);
        System.out.println("Dia de la semana: " + dia);
        System.out.println("Dia del anio: " + fecha.getDayOfYear());
        // Encabezado de las columnas que se mostraran en consola.
        System.out.println("HORA  | ESTADO     | CLIENTE");

        // Recorre las 24 casillas del dia, comenzando a las 00:00.
        for (int i = 0; i < 24; i++) {
            LocalDateTime hora = fecha.atTime(i, 0);
            // Se busca si cada hora corresponde a una reservacion existente.
            Reservacion reservacion = buscarReservacion(hora);
            if (reservacion == null) {
                System.out.println(hora.toLocalTime() + " | Disponible | -");
            } else {
                System.out.println(hora.toLocalTime() + " | Ocupado    | "
                        + reservacion.getNombreCliente());
            }
        }
    }

    // Muestra la lista de todas las reservaciones registradas.
    public void mostrarReservaciones() {
        // Si la lista esta vacia, se informa al usuario.
        if (reservaciones.isEmpty()) {
            System.out.println("No hay reservaciones registradas.");
        }
        // Cada objeto imprime sus datos mediante mostrarInformacion().
        for (Reservacion reservacion : reservaciones) {
            reservacion.mostrarInformacion();
            System.out.println("------------------------------");
        }
    }
}
