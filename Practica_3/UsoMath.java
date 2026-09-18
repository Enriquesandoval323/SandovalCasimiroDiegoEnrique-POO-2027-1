import java.util.Scanner;

// Programa que demuestra el uso de algunos métodos de la clase Math.

public class UsoMath {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // Solicita un número al usuario
        System.out.print("Ingresa un numero: ");

        // Valida que el dato ingresado sea un número
        if (!entrada.hasNextDouble()) {
            System.out.println("Error: Debe ingresar un numero.");
            entrada.close();
            return;
        }

        double numero = entrada.nextDouble();

        // Muestra las operaciones utilizando Math
        System.out.println("Numero al cuadrado: " + Math.pow(numero, 2));
        System.out.println("Valor absoluto: " + Math.abs(numero));

        // Valida que se pueda calcular la raíz cuadrada
        if (numero >= 0) {
            System.out.println("Raiz cuadrada: " + Math.sqrt(numero));
        } else {
            System.out.println("No se puede calcular la raiz cuadrada de un numero negativo.");
        }

        entrada.close();
    }
}