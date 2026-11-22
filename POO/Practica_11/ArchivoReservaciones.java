/*
El proposito de este programa es guardar y recuperar las reservaciones en un archivo de texto.
Se usa el archivo como almacenamiento persistente para conservar los eventos
aunque el programa se cierre. Cada registro almacena inicio, duracion y cliente.

Integrantes del equipo:
Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio
Rivera Hernandez Luis Angel
Salvador Casimiro Diego
*/
// File representa la ruta del archivo. FileReader y FileWriter permiten leer y escribir.
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
// BufferedReader y BufferedWriter permiten trabajar con lineas completas de texto.
import java.io.BufferedReader;
import java.io.BufferedWriter;
// IOException identifica errores de entrada y salida; UTF-8 define la codificacion.
import java.io.IOException;
import java.nio.charset.StandardCharsets;
// LocalDateTime se utiliza para reconstruir la fecha y hora de cada reservacion.
import java.time.LocalDateTime;
// DateTimeException se usa para detectar fechas u horas incorrectas al recuperar datos.
import java.time.DateTimeException;
// ArrayList permite devolver la lista de reservaciones leidas.
import java.util.ArrayList;

// Esta clase concentra las operaciones de almacenamiento y recuperacion.
public class ArchivoReservaciones {

    // Atributo privado que representa el archivo usado por el sistema.
    private File archivo;

    // Constructor que recibe el nombre del archivo donde se guardaran los datos.
    public ArchivoReservaciones(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
    }

    // Recupera las reservaciones guardadas al iniciar el programa
    public ArrayList<Reservacion> cargarReservaciones() throws IOException {
        // Se crea una lista que almacenara los objetos reconstruidos.
        ArrayList<Reservacion> reservaciones = new ArrayList<>();

        // Si el archivo aun no existe, se intenta crear para iniciar una agenda vacia.
        if (!archivo.exists()) {
            archivo.createNewFile();
        }

        // try-with-resources cierra automaticamente el lector al salir del bloque.
        try (BufferedReader entrada = new BufferedReader(
                new FileReader(archivo, StandardCharsets.UTF_8))) {
            // Se lee la primera linea y se prepara un contador para ubicar errores.
            String linea = entrada.readLine();
            int numeroLinea = 1;

            // El ciclo procesa una linea a la vez hasta llegar al final del archivo.
            while (linea != null) {
                // Cada registro usa el separador | y debe tener exactamente tres campos:
                // fecha-hora de inicio, duracion en horas y nombre del cliente.
                String[] datos = linea.split("\\|", -1);
                if (datos.length != 3) {
                    throw new IOException("Registro invalido en la linea " + numeroLinea);
                }
                // La conversion puede fallar si los datos guardados son invalidos.
                try {
                    // Se convierten los textos a fecha y hora, numero y objeto Reservacion.
                    LocalDateTime inicio = LocalDateTime.parse(datos[0]);
                    int duracion = Integer.parseInt(datos[1]);
                    Reservacion reservacion = new Reservacion(datos[2], inicio, duracion);
                    reservaciones.add(reservacion);
                // Si falla la fecha, el numero o la validacion del objeto,
                // se informa la linea que contiene el problema mediante IOException.
                } catch (DateTimeException | IllegalArgumentException e) {
                    throw new IOException("Datos invalidos en la linea " + numeroLinea, e);
                }
                // Se avanza al siguiente registro y aumenta el contador de linea.
                linea = entrada.readLine();
                numeroLinea++;
            }
        }
        // Se entrega a Agenda la lista completa de datos recuperados.
        return reservaciones;
    }

    // Agrega una reservacion sin sobrescribir las anteriores
    public void guardarReservacion(Reservacion reservacion) throws IOException {
        // El modo append (true) agrega el nuevo registro al final del archivo.
        try (BufferedWriter salida = new BufferedWriter(
                new FileWriter(archivo, StandardCharsets.UTF_8, true))) {
            // Se guarda una reservacion por linea con sus tres datos separados por |.
            salida.write(reservacion.getFechaInicio() + "|"
                    + reservacion.getDuracionHoras() + "|"
                    + reservacion.getNombreCliente());
            // Se inserta un salto de linea para separar el siguiente registro.
            salida.newLine();
        }
    }
}
