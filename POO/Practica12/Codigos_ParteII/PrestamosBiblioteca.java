/*
 * UNIVERSIDAD NACIONAL AUTÓNOMA DE MÉXICO
 * FACULTAD DE INGENIERÍA
 * Asignatura: Programación Orientada a Objetos
 * Práctica 12: Hilos - Parte II
 * Grupo: 09                 Semestre: 2027-1
 * Brigada: 2
 *
 * Integrantes:
 * Copado Hernández Luis Felipe
 * De La Cruz García Juan Antonio
 * Rivera Hernández Luis Ángel
 * Sandoval Casimiro Diego Enrique
 *
 * Programa: Problema 3 - Prestamos de biblioteca
 * Descripción: Interfaz Runnable; tres solicitudes para un unico ejemplar.
 */

import java.util.Scanner;

/**
 * PRACTICA 12 - PARTE II - PROBLEMA 3
 * Prestamo de un unico ejemplar solicitado por tres usuarios a la vez.
 * Las solicitudes se implementan mediante la interfaz Runnable.
 */
public class PrestamosBiblioteca {

    /** Libro compartido: existe un solo ejemplar por simulacion. */
    static class Libro {
        // Atributos que identifican el libro y registran su disponibilidad.
        private final String titulo;
        private boolean disponible = true;
        private String prestatario = "";

        // Constructor. Registra el título del ejemplar que se prestará.
        public Libro(String titulo) {
            this.titulo = titulo;
        }

        /**
         * El metodo synchronized protege la comprobacion de disponibilidad
         * y la asignacion del prestamo como una unica operacion.
         * Esto impide prestar el mismo ejemplar a dos personas.
         */
        // Comprueba y modifica la disponibilidad del libro en un solo paso.
        public synchronized void solicitarPrestamo(String nombre) {
            if (disponible) {
                disponible = false;
                prestatario = nombre;
                System.out.println(nombre + " obtuvo el prestamo de '" + titulo + "'.");
            } else {
                System.out.println(nombre + " no pudo obtener '" + titulo
                        + "': el ejemplar ya fue prestado.");
            }
        }

        // Se consulta una vez terminadas todas las solicitudes.
        public synchronized String resumen() {
            return "Ejemplar de '" + titulo + "' asignado a: " + prestatario;
        }
    }

    /** Cada tarea corresponde a una persona que solicita el libro. */
    static class SolicitudPrestamo implements Runnable {
        // Datos del libro compartido y del usuario que solicita el préstamo.
        private final Libro libro;
        private final String solicitante;

        // Constructor. Asocia al solicitante con el mismo ejemplar.
        public SolicitudPrestamo(Libro libro, String solicitante) {
            this.libro = libro;
            this.solicitante = solicitante;
        }

        // Ejecuta la solicitud cuando comienza el hilo correspondiente.
        @Override
        public void run() {
            System.out.println(solicitante + " solicita el libro.");
            libro.solicitarPrestamo(solicitante);
        }
    }

    // Verifica que los nombres y el título no queden vacíos.
    private static String leerTexto(Scanner teclado, String pregunta) {
        while (true) {
            System.out.print(pregunta);
            String texto = teclado.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo no puede quedar vacio.");
        }
    }

    // Consulta si se desea repetir la simulación con otro ejemplar.
    private static boolean deseaRepetir(Scanner teclado) {
        while (true) {
            String respuesta = leerTexto(teclado, "\nRealizar otra simulacion? (s/n): ");
            if (respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("si")) {
                return true;
            }
            if (respuesta.equalsIgnoreCase("n") || respuesta.equalsIgnoreCase("no")) {
                return false;
            }
            System.out.println("Responde s o n.");
        }
    }

    // Método principal. Captura los datos y atiende las tres solicitudes.
    public static void main(String[] args) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("=== PRESTAMOS DE BIBLIOTECA CON RUNNABLE ===");

        boolean repetir;
        do {
            // Se solicita el título y los nombres de los tres usuarios.
            String titulo = leerTexto(teclado, "Titulo del libro (un ejemplar): ");
            String[] nombres = new String[3];
            for (int i = 0; i < nombres.length; i++) {
                nombres[i] = leerTexto(teclado, "Nombre del solicitante " + (i + 1) + ": ");
            }

            // Las tres tareas utilizan un único objeto Libro compartido.
            Libro libroCompartido = new Libro(titulo);
            Thread[] solicitudes = new Thread[nombres.length];
            for (int i = 0; i < nombres.length; i++) {
                solicitudes[i] = new Thread(
                        new SolicitudPrestamo(libroCompartido, nombres[i]),
                        "Solicitud-" + (i + 1));
            }

            // Se inician los hilos sin establecer un orden de atención fijo.
            System.out.println("\nInician las tres solicitudes...\n");
            for (Thread solicitud : solicitudes) {
                solicitud.start();
            }

            // Se espera a que las tres solicitudes terminen antes del resumen.
            for (Thread solicitud : solicitudes) {
                solicitud.join();
            }
            System.out.println("\n" + libroCompartido.resumen());

            // Al repetir la simulación se crea un nuevo libro disponible.
            repetir = deseaRepetir(teclado);
            System.out.println();
        } while (repetir);

        System.out.println("Simulacion finalizada.");
    }
}
