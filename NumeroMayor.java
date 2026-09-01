import java.util.Scanner;

public class NumeroMayor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el primer número: ");
        int a = scanner.nextInt();

        System.out.print("Ingresa el segundo número: ");
        int b = scanner.nextInt();

        System.out.print("Ingresa el tercer número: ");
        int c = scanner.nextInt();

        if (a >= b && a >= c) {
            System.out.println("\nEl número mayor es: " + a);
        } else if (b >= a && b >= c) {
            System.out.println("\nEl número mayor es: " + b);
        } else {
            System.out.println("\nEl número mayor es: " + c);
        }

        scanner.close();
    }
}
