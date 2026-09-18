import java.util.Scanner;

// Programa que demuestra el uso de Autoboxing

public class Autoboxing {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // Solicitar un número entero al usuario
        System.out.print("Ingresa un numero entero: ");

        // Valida que el dato que se haya ingresado sea un número entero
        if (!entrada.hasNextInt()) {
            System.out.println("Error: Debe ingresar un numero entero.");
            entrada.close();
            return;
        }

        int numero = entrada.nextInt();

        // Convierte automáticamente de un int a Integer
        Integer numeroObjeto = numero;

        System.out.println("Numero como int: " + numero);
        System.out.println("Numero como Integer: " + numeroObjeto);

        // Convierte automáticamente un Integer a int
        int otroNumero = numeroObjeto;

        System.out.println("Numero nuevamente como int: " + otroNumero);

        entrada.close();
    }
}