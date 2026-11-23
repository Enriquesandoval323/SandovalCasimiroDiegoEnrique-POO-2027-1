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
 * Programa: Problema 2 - Retiros simultaneos
 * Descripción: Interfaz Runnable; dos solicitudes sobre una cuenta compartida.
 */

import java.util.Scanner;

/**
 * PRACTICA 12 - PARTE II - PROBLEMA 2
 * Simulacion de dos retiros simultaneos utilizando la interfaz Runnable.
 *
 * Los clientes comparten una cuenta; solo se autoriza un retiro
 * cuando el saldo alcanza para cubrir su monto.
 */
public class RetirosSimultaneos {

    /** Recurso compartido por las dos solicitudes de retiro. */
    static class CuentaBancaria {
        // Atributo que almacena el dinero disponible en la cuenta.
        private int saldo;

        // Constructor. Inicializa el saldo de la cuenta compartida.
        public CuentaBancaria(int saldoInicial) {
            this.saldo = saldoInicial;
        }

        /**
         * synchronized permite que solo un hilo compruebe y cambie el saldo
         * a la vez. Se usa el mismo objeto CuentaBancaria en ambos hilos.
         */
        // Autoriza o rechaza el retiro sin permitir cambios simultáneos al saldo.
        public synchronized void retirar(String cliente, int cantidad) {
            if (cantidad <= saldo) {
                saldo -= cantidad;
                System.out.println(cliente + ": retiro autorizado por $" + cantidad
                        + ". Saldo restante: $" + saldo);
            } else {
                System.out.println(cliente + ": retiro rechazado por $" + cantidad
                        + ". Saldo insuficiente ($" + saldo + ").");
            }
        }

        // La consulta se realiza sobre el mismo monitor que protege el retiro.
        public synchronized int consultarSaldo() {
            return saldo;
        }
    }

    /**
     * La clase representa una tarea, no un hilo por sí misma.
     * Por eso implementa Runnable y se ejecuta dentro de un Thread.
     */
    static class SolicitudRetiro implements Runnable {
        // Datos necesarios para ejecutar la solicitud de cada cliente.
        private final CuentaBancaria cuenta;
        private final String cliente;
        private final int cantidad;

        // Constructor de la solicitud que será ejecutada por un hilo.
        public SolicitudRetiro(CuentaBancaria cuenta, String cliente, int cantidad) {
            this.cuenta = cuenta;
            this.cliente = cliente;
            this.cantidad = cantidad;
        }

        // Se ejecuta al iniciar el Thread asociado a este Runnable.
        @Override
        public void run() {
            System.out.println(cliente + " solicita retirar $" + cantidad + ".");
            cuenta.retirar(cliente, cantidad);
        }
    }

    // Lee y valida las cantidades que introduce el usuario.
    private static int leerPositivo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(teclado.nextLine().trim());
                if (numero > 0 && numero <= 1_000_000) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // Se captura la entrada no numerica y el programa continua.
            }
            System.out.println("Ingresa un entero positivo de hasta 1000000.");
        }
    }

    // Método principal. Crea las solicitudes y muestra el saldo final.
    public static void main(String[] args) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("=== RETIROS SIMULTANEOS CON RUNNABLE ===");

        // Se capturan el saldo inicial y las cantidades de ambos retiros.
        int saldoInicial = leerPositivo(teclado, "Saldo inicial de la cuenta: $");
        int primerRetiro = leerPositivo(teclado, "Cantidad que retira Ana: $");
        int segundoRetiro = leerPositivo(teclado, "Cantidad que retira Luis: $");

        // Ambas solicitudes utilizan la misma cuenta bancaria.
        CuentaBancaria cuentaCompartida = new CuentaBancaria(saldoInicial);
        Thread hiloAna = new Thread(
                new SolicitudRetiro(cuentaCompartida, "Ana", primerRetiro), "Hilo-Ana");
        Thread hiloLuis = new Thread(
                new SolicitudRetiro(cuentaCompartida, "Luis", segundoRetiro), "Hilo-Luis");

        // Se inician los hilos para procesar las solicitudes.
        System.out.println("\nAmbas solicitudes se procesan...\n");
        hiloAna.start();
        hiloLuis.start();

        // Se espera a que terminen los dos hilos antes de consultar el saldo.
        hiloAna.join();
        hiloLuis.join();
        System.out.println("\nSaldo final de la cuenta: $" + cuentaCompartida.consultarSaldo());
        System.out.println("Operaciones finalizadas.");
    }
}
