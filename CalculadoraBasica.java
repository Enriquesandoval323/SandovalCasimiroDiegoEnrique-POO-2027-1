import java.util.Scanner;

public class CalculadoraBasica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el primer numero: ");
        double numero1 = scanner.nextDouble();

        System.out.print("Ingresa el segundo numero: ");
        double numero2 = scanner.nextDouble();

        double suma = numero1 + numero2;
        double resta = numero1 - numero2;
        double multiplicacion = numero1 * numero2;

        System.out.println("\n--- Resultados ---");
        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("Multiplicacion: " + multiplicacion);

        if (numero2 != 0) {
            double division = numero1 / numero2;
            System.out.println("Division: " + division);
        } else {
            System.out.println("Division: No se puede dividir entre cero");
        }

        scanner.close();
    }
}
