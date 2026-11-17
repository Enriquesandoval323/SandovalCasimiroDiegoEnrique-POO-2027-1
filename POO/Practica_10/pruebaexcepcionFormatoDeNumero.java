/*
El proposito de este programa es el de mostrar como es que funciona de manera practica la excepcion NumberFormatException.
El programa se basa, en que se le va a pedir a el usuario que ingrese dos numeros (como texto), para posteriormente realizar una suma.
Se usa NumberFormatException para poder evitar que el programa se detenga cuando el usuario ingresa letras, simbolos o espacios
en lugar de numeros validos, ya que al convertir un String a float con Float.parseFloat() Java lanza esta excepcion.

Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio 
Rivera Hernandez Luis Angel 
Salvador Casimiro Diego
*/

import java.util.Scanner;

public class pruebaexcepcionFormatoDeNumero {
    // Declaracion de los atributos, se ponen en privado para que el usuario no pueda acceder a ellos directamente
    // Se guardan como String porque justamente lo que se va a probar es la conversion de texto a numero
    private String texto1;
    private String texto2;

    // Declaracion de los dos constructores, tanto el caso base, asi como el constructor lleno
    public pruebaexcepcionFormatoDeNumero(){

    }

    public pruebaexcepcionFormatoDeNumero(String texto1, String texto2) {
        this.texto1 = texto1;
        this.texto2 = texto2;
    }

    // Metodos setters
    public void settexto1(String texto1) {
        this.texto1 = texto1;
    }

    public void settexto2(String texto2) {
        this.texto2 = texto2;
    }

    // Metodos getters
    public String gettexto1() {
        return texto1;
    }

    public String gettexto2() {
        return texto2;
    }

    // Creacion del metodo sumar, aqui es donde vamos a hacer uso de try-catch, el cual nos va a permitir atrapar la excepcion NumberFormatException
    // El metodo regresa true si la conversion fue exitosa y false si hubo error, esto nos sirve para controlar el ciclo en el main
    public boolean sumar() {
        try {
            // Float.parsefloat() lanza NumberFormatException si el texto no es un entero valido (por ejemplo "abc" o solo un espacio)
            // trim() se usa para quitar espacios al inicio y al final, ya que "  5 " seria valido una vez limpiado
            float num1 = Float.parseFloat(texto1.trim());
            float num2 = Float.parseFloat(texto2.trim());
            float resultado = num1 + num2;
            System.out.println("El resultado de esta suma es: " + resultado);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: El texto ingresado no tiene formato de numero.");
            return false;
        }
    }

    // Creacion de la clase main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Programa para probar las excepciones");

        // Ciclo con ayuda de un while, que obliga al usuario a ingresar correctamente la informacion
        // Se pide todo como texto (nextLine) para que sea el metodo sumar() quien intente la conversion
        while (true) {
            System.out.println("Ingresa el primer numero: ");
            String entrada1 = scanner.nextLine();

            System.out.println("Ingresa el segundo numero: ");
            String entrada2 = scanner.nextLine();

            // Creamos el objeto y operamos (aqui actua la NumberFormatException en el metodo)
            pruebaexcepcionFormatoDeNumero operacion = new pruebaexcepcionFormatoDeNumero(entrada1, entrada2);

            if (operacion.sumar()) {
                break; // Si ambos datos fueron validos, rompe el ciclo y termina
            }
            System.out.println("Intentalo de nuevo, por favor ingresa solo numeros.");
        }

        scanner.close();
    }
}