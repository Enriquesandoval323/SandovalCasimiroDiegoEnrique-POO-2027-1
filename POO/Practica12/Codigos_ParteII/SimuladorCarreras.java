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
 * Programa: Problema 1 - Simulador de carreras
 * Descripción: Herencia de Thread; corredores que avanzan en hilos independientes.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * PRACTICA 12 - PARTE II - PROBLEMA 1
 * Simulador de carreras utilizando herencia de la clase Thread.
 *
 * Tres corredores avanzan de manera concurrente y se registra
 * el orden en el que llegan a una meta definida por el usuario.
 */
public class SimuladorCarreras {

    /**
     * Guarda los corredores que terminan la carrera.
     * Los tres hilos comparten una sola instancia de este registro.
     */
    static class RegistroLlegadas {
        // Lista compartida para guardar el orden de llegada.
        private final List<String> clasificacion = new ArrayList<>();

        // synchronized evita que dos hilos asignen el mismo lugar de llegada.
        // Agrega un corredor cuando termina y le asigna un lugar.
        public synchronized void registrar(String nombre) {
            clasificacion.add(nombre);
            System.out.println("META: " + nombre + " llego en el lugar "
                    + clasificacion.size() + ".");
        }

        // Se imprime la clasificacion cuando todos los corredores terminaron.
        // Muestra los lugares una vez que todos los hilos terminaron.
        public synchronized void mostrarClasificacion() {
            System.out.println("\n=== CLASIFICACION FINAL ===");
            for (int i = 0; i < clasificacion.size(); i++) {
                System.out.println((i + 1) + ". " + clasificacion.get(i));
            }
        }
    }

    /**
     * Cada objeto Corredor es un hilo, porque hereda de Thread.
     * Los valores de avance y pausa permiten que cada uno tenga
     * un ritmo diferente sin establecer de antemano al ganador.
     */
    static class Corredor extends Thread {
        // Atributos que describen el avance de cada participante.
        private final int avancePorTurno;
        private final int pausaMilisegundos;
        private final int meta;
        private final RegistroLlegadas registro;

        // Constructor. Recibe el nombre del hilo y las condiciones de la carrera.
        public Corredor(String nombre, int avancePorTurno, int pausaMilisegundos,
                        int meta, RegistroLlegadas registro) {
            super(nombre); // El nombre se asigna al hilo mediante Thread.
            this.avancePorTurno = avancePorTurno;
            this.pausaMilisegundos = pausaMilisegundos;
            this.meta = meta;
            this.registro = registro;
        }

        // Este método contiene las instrucciones de cada hilo corredor.
        @Override
        public void run() {
            int recorrido = 0;
            try {
                // Cada corredor actualiza su distancia de forma independiente.
                while (recorrido < meta) {
                    // sleep() simula el tiempo necesario para correr un tramo.
                    Thread.sleep(pausaMilisegundos);
                    recorrido = Math.min(meta, recorrido + avancePorTurno);
                    System.out.println(getName() + " avanzo hasta " + recorrido
                            + " de " + meta + " metros.");
                }
                // El registro compartido determina el lugar al llegar a la meta.
                registro.registrar(getName());
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrumpio su carrera.");
                Thread.currentThread().interrupt(); // Se conserva la interrupcion.
            }
        }
    }

    // Solicita un número entero y valida que esté dentro del intervalo.
    private static int leerEntero(Scanner teclado, String mensaje,
                                  int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(teclado.nextLine().trim());
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Si no es un entero, se solicita nuevamente sin cerrar el programa.
            }
            System.out.println("Ingresa un entero entre " + minimo + " y " + maximo + ".");
        }
    }

    // Pide un nombre válido para identificar al corredor.
    private static String leerNombre(Scanner teclado, int participante) {
        while (true) {
            System.out.print("Nombre del corredor " + participante + ": ");
            String nombre = teclado.nextLine().trim();
            if (!nombre.isEmpty()) {
                return nombre;
            }
            System.out.println("El nombre no puede quedar vacio.");
        }
    }

    // Método principal. Registra a los corredores e inicia la competencia.
    public static void main(String[] args) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("=== SIMULADOR DE CARRERAS CON THREAD ===");

        // Se capturan los datos de la carrera y de cada corredor.
        int meta = leerEntero(teclado, "Distancia de la carrera (20 a 100 m): ", 20, 100);
        RegistroLlegadas registro = new RegistroLlegadas();
        Corredor[] corredores = new Corredor[3];

        for (int i = 0; i < corredores.length; i++) {
            System.out.println("\nCorredor " + (i + 1));
            String nombre = leerNombre(teclado, i + 1);
            int avance = leerEntero(teclado, "Metros por turno (2 a 15): ", 2, 15);
            int pausa = leerEntero(teclado, "Pausa por turno (60 a 300 ms): ", 60, 300);
            corredores[i] = new Corredor(nombre, avance, pausa, meta, registro);
        }

        // start() pone en ejecución a los tres corredores de forma concurrente.
        System.out.println("\nInicia la carrera...\n");
        for (Corredor corredor : corredores) {
            corredor.start();
        }

        // join() permite esperar a que los tres hilos lleguen a la meta.
        for (Corredor corredor : corredores) {
            corredor.join();
        }

        // Finalmente se consulta el registro y se muestra la clasificación.
        registro.mostrarClasificacion();
        System.out.println("Los tres corredores han terminado.");
    }
}
