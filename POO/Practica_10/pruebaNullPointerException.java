/*
El proposito de este programa es el de mostrar como es que funciona de manera practica la excepcion NullPointerException.
El programa se basa, en que se le va a pedir a el usuario que ingrese un texto, para posteriormente calcular su longitud y mostrarlo en mayusculas.
Si el usuario no escribe nada y solo presiona Enter, el programa guarda el texto como null (referencia vacia), simulando una variable que nunca fue inicializada.
Se usa NullPointerException para poder evitar que el programa se detenga cuando se intenta usar un metodo (length, toUpperCase) sobre un objeto que vale null.

Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio 
Rivera Hernandez Luis Angel 
Salvador Casimiro Diego

*/

import java.util.Scanner;

public class pruebaNullPointerException {
    // Declaracion de los atributos, se ponen en privado para que el usuario no pueda acceder a ellos directamente
    // Un String es un objeto, por lo tanto puede valer null a diferencia de los tipos primitivos como int o float
    private String texto;

    // Declaracion de los dos constructores, tanto el caso base, asi como el constructor lleno
    public pruebaNullPointerException() {

    }

    public pruebaNullPointerException(String texto) {
        this.texto = texto;
    }

    // Metodos setters
    public void settexto(String texto) {
        this.texto = texto;
    }

    // Metodos getters
    public String gettexto() {
        return texto;
    }

    // Creacion del metodo analizarTexto, aqui es donde vamos a hacer uso de try-catch, el cual nos va a permitir atrapar la excepcion NullPointerException
    public void analizarTexto() {
        try {
            // Si texto vale null, al llamar texto.length() Java lanza NullPointerException porque no hay ningun objeto sobre el cual ejecutar el metodo
            int longitud = texto.length();
            String mayusculas = texto.toUpperCase();
            System.out.println("El texto tiene " + longitud + " caracteres.");
            System.out.println("El texto en mayusculas es: " + mayusculas);
        } catch (NullPointerException e) {
            System.out.println("Error: No se puede analizar el texto porque no existe (valor null).");
        }
    }

    // Creacion de la clase main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcion;

        System.out.println("Programa para probar las excepciones");

        // Ciclo con ayuda de un while, que permite al usuario probar varias veces el programa hasta que decida salir
        do {
            System.out.println("Ingresa un texto (si solo presionas Enter, el texto se guardara como null): ");
            String entrada = scanner.nextLine();

            // Si el usuario no escribio nada, se asigna null a proposito para provocar la excepcion
            if (entrada.isEmpty()) {
                entrada = null;
            }

            // Creamos el objeto y operamos (aqui actua la NullPointerException en el metodo)
            pruebaNullPointerException operacion = new pruebaNullPointerException(entrada);
            operacion.analizarTexto();

            System.out.println("Deseas probar de nuevo? (s/n): ");
            opcion = scanner.nextLine();
        } while (opcion.equalsIgnoreCase("s"));

        System.out.println("Programa terminado.");
        scanner.close();
    }
}